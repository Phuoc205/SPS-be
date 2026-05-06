package com.smartparking.payment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Data
public class PaymentSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;

    private Long slotId;

    private Double cost;
}
