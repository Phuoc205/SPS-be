package com.smartparking.iot.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "gate_control_log")
public class GateControlLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gate_id")
    private Long gateId;

    @Column(name = "staff_id")
    private Long staffId;

    private String action;
    private String reason;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}