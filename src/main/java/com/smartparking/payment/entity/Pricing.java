package com.smartparking.payment.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "pricing",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"vehicle_type", "type"})
    }
)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Pricing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vehicle_type", nullable = false)
    private String vehicleType; // "CAR" hoặc "BIKE"

    @Column(name = "type", nullable = false)
    private String type; // "HOURLY", "DAILY", "MONTHLY"

    @Column(name = "price_per_hour")
    private Double pricePerHour;

    @Column(name = "price_per_day")
    private Double pricePerDay;

    @Column(name = "price_per_month")
    private Double pricePerMonth;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}