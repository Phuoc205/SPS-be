package com.smartparking.parking.controller;

import com.smartparking.parking.entity.ParkingLot;
import com.smartparking.parking.service.ParkingLotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parking-lots")
public class ParkingLotController {

    @Autowired
    private ParkingLotService service;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ParkingLot lot) {
        return ResponseEntity.ok(service.create(lot));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}