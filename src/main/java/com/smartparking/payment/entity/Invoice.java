package com.smartparking.payment.entity;
import com.smartparking.payment.entity.enumeration.InvoiceStatus;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Data
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;
    // GENERATED, PAID, OVERDUE

    private LocalDateTime fromDate;
    private LocalDateTime toDate;

    private String type;
    // STUDENT, STAFF, VISITOR
}
