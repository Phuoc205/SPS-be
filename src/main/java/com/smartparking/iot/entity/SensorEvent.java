package com.smartparking.iot.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long deviceId;
    private Long slotId;

    private boolean occupied;

    @Column(name = "created_at")
    private LocalDateTime timestamp;
}
