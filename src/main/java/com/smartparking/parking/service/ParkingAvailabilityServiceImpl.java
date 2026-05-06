package com.smartparking.parking.service;

import com.smartparking.parking.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ParkingAvailabilityServiceImpl implements ParkingAvailabilityService {

    @Autowired
    private SlotRepository slotRepo;

    @Override
    public Map<String, Object> getAvailability() {

        long total = slotRepo.count();
        long occupied = slotRepo.findAll()
                .stream()
                .filter(s -> s.isOccupied())
                .count();

        long available = total - occupied;

        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("occupied", occupied);
        result.put("available", available);

        return result;
    }
}