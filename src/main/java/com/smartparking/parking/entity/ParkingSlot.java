package com.smartparking.parking.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String slotName;

    private boolean occupied;

    @ManyToOne
    @JoinColumn(name = "lot_id")
    private ParkingLot lot;
}