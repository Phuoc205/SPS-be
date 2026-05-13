package com.smartparking.parking.service;

import com.smartparking.parking.dto.response.*;
import com.smartparking.parking.entity.*;
import com.smartparking.parking.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.time.Duration;
import java.util.List;

@Service
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    private ParkingSessionRepository sessionRepo;

    @Autowired
    private SlotRepository slotRepo;

    @Override
    public CheckInResponse checkIn(String cardId) {

        ParkingSlot slot = slotRepo.findFirstByOccupiedFalse()
                .orElseThrow(() -> new RuntimeException("Parking full"));

        slot.setOccupied(true);
        slotRepo.save(slot);

        ParkingSession session = new ParkingSession();
        session.setCheckInTime(LocalDateTime.now());
        session.setSlot(slot);
        session.setStatus(SessionStatus.ACTIVE); // ✅ set status

        sessionRepo.save(session);

        return new CheckInResponse(
                session.getId(),
                slot.getSlotName(),
                "Check-in success"
        );
    }

    @Override
    public CheckOutResponse checkOut(String cardId) {

        ParkingSession session = sessionRepo
                .findByUser_CardIdAndCheckOutTimeIsNull(cardId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        session.setCheckOutTime(LocalDateTime.now());
        session.setStatus(SessionStatus.FINISHED); // ✅ set status, bỏ historyService.save()

        ParkingSlot slot = session.getSlot();
        slot.setOccupied(false);
        slotRepo.save(slot);

        sessionRepo.save(session);

        return new CheckOutResponse(
                session.getId(),
                slot.getSlotName(),
                "Check-out success"
        );
    }

    @Override
    public List<ParkingHistoryResponse> getHistoryByUser(Long userId) {

        return sessionRepo.findByUser_Id(userId)
                .stream()
                .map(session -> new ParkingHistoryResponse(
                        session.getId(),
                        session.getSlot() != null ? session.getSlot().getSlotName() : "-",
                        session.getStatus() != null ? session.getStatus().name() : "ACTIVE",
                        session.getCheckInTime() != null ? session.getCheckInTime().toString() : null,
                        session.getCheckOutTime() != null ? session.getCheckOutTime().toString() : null,
                        calculateDurationHours(session),
                        session.getTotalAmount() != null ? session.getTotalAmount() : 0
                ))
                .collect(Collectors.toList());
    }

    // ✅ Chuyển helper từ ParkingHistoryServiceImpl sang đây
    private long calculateDurationHours(ParkingSession session) {

        if (session.getCheckInTime() == null) return 0;

        LocalDateTime endTime = session.getCheckOutTime() != null
                ? session.getCheckOutTime()
                : LocalDateTime.now();

        long hours = Duration.between(session.getCheckInTime(), endTime).toHours();

        return Math.max(hours, 1);
    }
}