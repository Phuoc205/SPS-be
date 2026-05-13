package com.smartparking.user.entity;
import jakarta.persistence.*;
import lombok.*;

import org.hibernate.annotations.Nationalized;

import java.time.LocalDateTime;

@Entity
@Table(name = "vehicle")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String licensePlate;

    private String brand;

    private String model;

    @Enumerated(EnumType.STRING)
    private VehicleType type;
    
    // CAR, MOTORBIKE

    private String color;

    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
