package com.smartparking.iot.entity;
import jakarta.persistence.*;

import com.smartparking.parking.entity.ParkingSlot;

import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Sensor extends Device {

    @ManyToOne
    private ParkingSlot slot;
}
