package com.smartparking.parking.service;

import com.smartparking.parking.dto.response.SlotResponse;
import com.smartparking.parking.entity.ParkingSlot;

import java.util.List;

public interface SlotService {

    List<SlotResponse> getAll();

    ParkingSlot create(ParkingSlot slot);

    ParkingSlot update(Long id, ParkingSlot slot);
}