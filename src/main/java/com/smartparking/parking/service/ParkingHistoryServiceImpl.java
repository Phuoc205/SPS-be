package com.smartparking.parking.service;

import com.smartparking.parking.entity.ParkingHistory;
import com.smartparking.parking.entity.ParkingSession;
import com.smartparking.parking.repository.ParkingHistoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingHistoryServiceImpl implements ParkingHistoryService {

    @Autowired
    private ParkingHistoryRepository repo;

    @Override
    public void save(ParkingSession session) {

        ParkingHistory h = new ParkingHistory();

        h.setUserId(session.getUserId());
        h.setSlotName(session.getSlot().getSlotName());
        h.setCheckInTime(session.getCheckInTime());
        h.setCheckOutTime(session.getCheckOutTime());

        repo.save(h);
    }

    @Override
    public List<ParkingHistory> getByUser(Long userId) {
        return repo.findByUserId(userId);
    }
}
