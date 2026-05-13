package com.smartparking.parking.repository;

import com.smartparking.parking.entity.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SlotRepository extends JpaRepository<ParkingSlot, Long> {

    Optional<ParkingSlot> findFirstByOccupiedFalse();
    long countByOccupied(boolean occupied);
}