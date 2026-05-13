package com.smartparking.payment.entity;

import com.smartparking.user.entity.VehicleType;
import com.smartparking.user.entity.UserRole;
import jakarta.persistence.*;
import com.smartparking.payment.entity.enumeration.PricingPlanType;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pricing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @Enumerated(EnumType.STRING)
    private PricingPlanType planType; // HOURLY / DAILY / WEEKLY / MONTHLY

    private Double price;

    private Integer durationValue; 
    // ví dụ:
    // HOURLY -> 1
    // DAILY -> 1
    // WEEKLY -> 1
    // MONTHLY -> 1

    private boolean active;
}