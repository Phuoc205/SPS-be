package com.smartparking.parking.service;

import com.smartparking.parking.dto.response.SlotResponse;
import com.smartparking.parking.entity.ParkingSlot;
import com.smartparking.parking.repository.SlotRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlotServiceImpl implements SlotService {

    @Autowired
    private SlotRepository repo;

    @Override
    public List<SlotResponse> getAll() {

        return repo.findAll()
                .stream()
                .map(slot -> new SlotResponse(
                        slot.getId(),
                        slot.getSlotName(),
                        slot.isOccupied(),

                        slot.getLot() != null
                                ? slot.getLot().getId()
                                : null,

                        slot.getLot() != null
                                ? slot.getLot().getName()
                                : null,

                        slot.getLot() != null
                                ? slot.getLot().getLocation()
                                : null
                ))
                .toList();
    }

    @Override
    public ParkingSlot create(ParkingSlot slot) {
        slot.setOccupied(false);
        return repo.save(slot);
    }

    @Override
    public ParkingSlot update(Long id, ParkingSlot slotDetails) {

        ParkingSlot slot = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        slot.setSlotName(slotDetails.getSlotName());
        slot.setOccupied(slotDetails.isOccupied());

        return repo.save(slot);
    }
}