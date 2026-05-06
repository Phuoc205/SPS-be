package com.smartparking.payment.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pricing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userType; // STUDENT, STAFF, VISITOR

    private Double pricePerHour;

    private boolean active;
}