package com.smartparking.parking.entity;

import jakarta.persistence.*;
import lombok.*;
import com.smartparking.payment.entity.Pricing;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String cardId;

    private LocalDateTime checkInTime;

    private LocalDateTime checkOutTime;

    private String licensePlate;

    @ManyToOne
    private Pricing price;

    @ManyToOne
    private ParkingSlot slot;
}