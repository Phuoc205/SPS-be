package com.smartparking.parking.repository;

import com.smartparking.parking.entity.ParkingSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkingSessionRepository extends JpaRepository<ParkingSession, Long> {

    Optional<ParkingSession> findByCardIdAndCheckOutTimeIsNull(String cardId);
}