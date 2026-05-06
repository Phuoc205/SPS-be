package com.smartparking.parking.service;

import com.smartparking.parking.dto.response.*;
import com.smartparking.parking.entity.*;
import com.smartparking.parking.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    private ParkingSessionRepository sessionRepo;

    @Autowired
    private SlotRepository slotRepo;

    @Autowired
    private ParkingHistoryService historyService;

    @Override
    public CheckInResponse checkIn(String cardId) {

        ParkingSlot slot = slotRepo.findFirstByOccupiedFalse()
                .orElseThrow(() -> new RuntimeException("Parking full"));

        slot.setOccupied(true);
        slotRepo.save(slot);

        ParkingSession session = new ParkingSession();
        session.setCardId(cardId);
        session.setCheckInTime(LocalDateTime.now());
        session.setSlot(slot);

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
                .findByCardIdAndCheckOutTimeIsNull(cardId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        session.setCheckOutTime(LocalDateTime.now());

        ParkingSlot slot = session.getSlot();
        slot.setOccupied(false);
        slotRepo.save(slot);

        sessionRepo.save(session);

        // lưu history
        historyService.save(session);

        return new CheckOutResponse(
                session.getId(),
                slot.getSlotName(),
                "Check-out success"
        );
    }
}