package com.smartparking.parking.repository;

import com.smartparking.parking.entity.ParkingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.time.LocalDateTime;
import java.util.List;

public interface ParkingSessionRepository extends JpaRepository<ParkingSession, Long> {

    Optional<ParkingSession> findByCardIdAndCheckOutTimeIsNull(String cardId);
    @Query("""
        SELECT COUNT(p)
        FROM ParkingSession p
        WHERE p.checkInTime >= :start
        AND p.checkInTime < :end
    """)
    long countTodayVehicles(
        @Param("start") LocalDateTime start,
        @Param("end") LocalDateTime end
    );
    List<ParkingSession> findTop10ByOrderByCheckInTimeDesc();
}