package com.smartparking.parking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sessionId;

    private Long userId;

    private String slotName;

    private LocalDateTime checkInTime;

    private LocalDateTime checkOutTime;

    private Double amount;
}