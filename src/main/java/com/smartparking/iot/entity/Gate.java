package com.smartparking.iot.entity;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Gate extends Device {

    @Enumerated(EnumType.STRING)
    private GateType type;

    private boolean opened;
}
