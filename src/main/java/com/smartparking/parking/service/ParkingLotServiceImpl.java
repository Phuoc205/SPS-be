package com.smartparking.parking.service;

import com.smartparking.parking.entity.ParkingLot;
import com.smartparking.parking.repository.ParkingLotRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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