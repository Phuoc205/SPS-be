package com.smartparking.parking.repository;

import com.smartparking.parking.entity.ParkingHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingHistoryRepository extends JpaRepository<ParkingHistory, Long> {

    List<ParkingHistory> findByUserId(Long userId);
}