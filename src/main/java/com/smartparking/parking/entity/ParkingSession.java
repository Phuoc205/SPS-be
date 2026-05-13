package com.smartparking.parking.entity;

import jakarta.persistence.*;
import lombok.*;
import com.smartparking.user.entity.Vehicle;
import com.smartparking.user.entity.User;
import com.smartparking.ticket.entity.Ticket;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Vehicle vehicle;

    private LocalDateTime checkInTime;

    private LocalDateTime checkOutTime;

    private String licensePlate;

    @ManyToOne
    private ParkingSlot slot;

    @ManyToOne
    private Ticket ticket;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private SessionStatus status = SessionStatus.ACTIVE;
}