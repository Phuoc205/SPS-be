package com.smartparking.parking.service;

import com.smartparking.parking.entity.ParkingHistory;
import com.smartparking.parking.entity.ParkingSession;

import java.util.List;

public interface ParkingHistoryService {

    void save(ParkingSession session);

    List<ParkingHistory> getByUser(Long userId);
}