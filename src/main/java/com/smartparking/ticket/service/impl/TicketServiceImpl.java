package com.smartparking.ticket.service.impl;

import com.smartparking.ticket.dto.request.*;
import com.smartparking.ticket.dto.response.*;
import com.smartparking.ticket.entity.Ticket;
import com.smartparking.ticket.repository.TicketRepository;
import com.smartparking.ticket.service.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository repo;

    @Override
    public TicketResponse issue(String plate) {

        Ticket ticket = new Ticket();
        ticket.setTicketCode(UUID.randomUUID().toString());
        ticket.setVehiclePlate(plate);
        ticket.setUsed(false);

        repo.save(ticket);

        return new TicketResponse(ticket.getTicketCode(), "ISSUED");
    }

    @Override
    public TicketResponse validate(TicketRequest request) {

        Ticket ticket = repo.findByTicketCode(request.getTicketCode())
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        if (ticket.isUsed()) {
            return new TicketResponse(ticket.getTicketCode(), "ALREADY_USED");
        }

        ticket.setUsed(true);
        ticket.setUsedAt(java.time.LocalDateTime.now());

        repo.save(ticket);

        return new TicketResponse(ticket.getTicketCode(), "VALID");
    }
}