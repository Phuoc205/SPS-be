package com.smartparking.parking.controller;

import com.smartparking.parking.entity.ParkingSlot;
import com.smartparking.parking.service.SlotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/slots")
public class SlotController {

    @Autowired
    private SlotService service;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ParkingSlot slot) {
        return ResponseEntity.ok(service.create(slot));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody ParkingSlot slot) {
        return ResponseEntity.ok(service.update(id, slot));
    }
}