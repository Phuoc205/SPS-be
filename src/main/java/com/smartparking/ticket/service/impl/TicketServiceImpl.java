package com.smartparking.ticket.service.impl;

import com.smartparking.ticket.dto.request.*;
import com.smartparking.ticket.dto.response.*;
import com.smartparking.ticket.entity.Ticket;
import com.smartparking.ticket.entity.TicketType;
import com.smartparking.ticket.repository.TicketRepository;
import com.smartparking.ticket.service.TicketService;
import com.smartparking.user.entity.Vehicle;
import com.smartparking.user.repository.VehicleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository repo;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public TicketResponse issue(TicketIssueRequest request) {

        Vehicle vehicle = vehicleRepository
                .findByLicensePlate(request.getPlate())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        TicketType type;
        try {
            type = TicketType.valueOf(request.getType().toUpperCase());
        } catch (Exception e) {
            throw new RuntimeException("Invalid ticket type: " + request.getType());
        }

        LocalDateTime now = LocalDateTime.now();

        Ticket ticket = new Ticket();
        ticket.setTicketCode(UUID.randomUUID().toString());
        ticket.setVehicle(vehicle);
        ticket.setUser(vehicle.getUser());
        ticket.setType(type);
        ticket.setIssuedAt(now);
        ticket.setExpiredAt(calculateExpiredAt(type, now));
        ticket.setActive(true);

        repo.save(ticket);

        return new TicketResponse(ticket.getTicketCode(), "ISSUED");
    }

    private LocalDateTime calculateExpiredAt(TicketType type, LocalDateTime now) {

        return switch (type) {

            case TEMPORARY -> now.plusHours(8);

            case SUBSCRIPTION -> now.plusMonths(1);
        };
    }

    @Override
    public TicketResponse validate(TicketRequest request) {

        Ticket ticket = repo.findByTicketCode(request.getTicketCode())
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        if (!ticket.isActive()) {
            return new TicketResponse(ticket.getTicketCode(), "INACTIVE");
        }

        if (isExpired(ticket)) {
            return new TicketResponse(ticket.getTicketCode(), "EXPIRED");
        }

        if (request.getLicensePlate() != null) {

            boolean match = ticket.getVehicle() != null
                    && ticket.getVehicle().getLicensePlate() != null
                    && ticket.getVehicle().getLicensePlate()
                            .equalsIgnoreCase(request.getLicensePlate());

            if (!match) {
                return new TicketResponse(ticket.getTicketCode(), "INVALID_PLATE");
            }
        }

        return new TicketResponse(ticket.getTicketCode(), "VALID");
    }

    private boolean isExpired(Ticket ticket) {

        return ticket.getExpiredAt() != null
                && ticket.getExpiredAt().isBefore(LocalDateTime.now());
    }
}