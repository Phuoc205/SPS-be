package com.smartparking.payment.entity;
import com.smartparking.payment.entity.enumeration.PaymentStatus;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long invoiceId;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status; 
    // PENDING, PAID, FAILED

    private String provider; 

    private LocalDateTime createdAt = LocalDateTime.now();
}
