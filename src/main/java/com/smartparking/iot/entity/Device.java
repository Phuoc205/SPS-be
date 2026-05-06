package com.smartparking.iot.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceCode; // ESP32-01, SENSOR-A1

    private String type; // SENSOR, GATE, CAMERA

    private String location; // A1, B2

    private boolean active;

    // mapping với slot (optional)
    private Long slotId;
}