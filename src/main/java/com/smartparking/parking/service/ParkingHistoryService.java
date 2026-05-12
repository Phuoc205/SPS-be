package com.smartparking.parking.service;

import com.smartparking.parking.dto.response.ParkingHistoryResponse;
import com.smartparking.parking.entity.ParkingSession;

import java.util.List;

public interface ParkingHistoryService {

    void save(ParkingSession session);

    List<ParkingHistoryResponse> getByUser(Long userId);
}