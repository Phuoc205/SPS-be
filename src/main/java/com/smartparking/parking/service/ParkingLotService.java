package com.smartparking.parking.service;

import com.smartparking.parking.entity.ParkingLot;

import java.util.List;

public interface ParkingLotService {

    List<ParkingLot> getAll();

    ParkingLot create(ParkingLot lot);

    void delete(Long id);
}