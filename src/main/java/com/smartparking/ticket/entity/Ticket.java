package com.smartparking.ticket.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ticketCode; // QR / random

    private String vehiclePlate;

    private LocalDateTime issuedAt = LocalDateTime.now();

    private boolean used;

    private LocalDateTime usedAt;
}