package com.smartparking.iot.entity;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Gate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Gate A, Gate B
    private String type; // ENTRY / EXIT
    private String location;

    private boolean status; // OPEN / CLOSED
}
