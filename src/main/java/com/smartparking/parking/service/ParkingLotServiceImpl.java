package com.smartparking.parking.service;

import com.smartparking.parking.entity.ParkingLot;
import com.smartparking.parking.entity.ParkingSlot;
import com.smartparking.parking.repository.ParkingLotRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {

    @Autowired
    private ParkingLotRepository repo;

    @Override
    public List<ParkingLot> getAll() {
        return repo.findAll();
    }

    @Override
    public ParkingLot create(ParkingLot lot) {

        List<ParkingSlot> slots = new ArrayList<>();

        for (int i = 1; i <= lot.getCapacity(); i++) {

            ParkingSlot slot = new ParkingSlot();

            slot.setSlotName("A" + i);
            slot.setOccupied(false);
            slot.setLot(lot);

            slots.add(slot);
        }

        lot.setSlots(slots);

        return repo.save(lot);
    }

    @Override
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Parking lot không tồn tại với id = " + id);
        }
        repo.deleteById(id);
    }
}