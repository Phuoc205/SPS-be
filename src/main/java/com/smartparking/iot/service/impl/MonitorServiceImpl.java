package com.smartparking.iot.service.impl;

import com.smartparking.iot.service.MonitorService;
import com.smartparking.parking.repository.SlotRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MonitorServiceImpl implements MonitorService {

    @Autowired
    private SlotRepository slotRepo;

    @Override
    public List<Map<String, Object>> getRealtimeSlots() {

        return slotRepo.findAll().stream().map(slot -> {
            Map<String, Object> m = new HashMap<>();
            m.put("slotName", slot.getSlotName());
            m.put("occupied", slot.isOccupied());
            return m;
        }).toList();
    }
}