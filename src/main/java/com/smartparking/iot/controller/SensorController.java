package com.smartparking.iot.controller;

import com.smartparking.iot.entity.Sensor;
import com.smartparking.iot.service.SensorService;
import com.smartparking.parking.entity.ParkingSlot;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sensors")
@RequiredArgsConstructor
public class SensorController {

    private final SensorService sensorService;

    @PostMapping
    public ResponseEntity<Sensor> create(@RequestBody Sensor sensor) {
        return ResponseEntity.ok(sensorService.create(sensor));
    }

    @PostMapping("/{id}/assign-slot")
    public ResponseEntity<Sensor> assignSlot(
            @PathVariable Long id,
            @RequestBody ParkingSlot slot
    ) {
        return ResponseEntity.ok(sensorService.assignSlot(id, slot));
    }
}