package com.smartparking.ticket.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import com.smartparking.user.entity.Vehicle;
import com.smartparking.user.entity.User;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ticketCode;

    @Enumerated(EnumType.STRING)
    private TicketType type;

    @ManyToOne
    private User user;

    @ManyToOne
    private Vehicle vehicle;

    private LocalDateTime issuedAt;

    private LocalDateTime expiredAt;

    private boolean active;
}