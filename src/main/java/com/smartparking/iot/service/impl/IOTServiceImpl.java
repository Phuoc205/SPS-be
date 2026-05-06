package com.smartparking.iot.service.impl;

import com.smartparking.iot.dto.request.IOTRequest;
import com.smartparking.iot.service.IOTService;
import com.smartparking.parking.entity.ParkingSlot;
import com.smartparking.parking.repository.SlotRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IOTServiceImpl implements IOTService {

    @Autowired
    private SlotRepository slotRepo;

    @Override
    public void updateSlotStatus(IOTRequest request) {

        ParkingSlot slot = slotRepo.findAll().stream()
                .filter(s -> s.getSlotName().equals(request.getSlotName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        slot.setOccupied(request.isOccupied());

        slotRepo.save(slot);
    }
}