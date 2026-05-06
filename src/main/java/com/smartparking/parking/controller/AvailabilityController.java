package com.smartparking.parking.controller;

import com.smartparking.parking.service.ParkingAvailabilityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/availability")
public class AvailabilityController {

    @Autowired
    private ParkingAvailabilityService service;

    @GetMapping
    public ResponseEntity<?> getAvailability() {
        return ResponseEntity.ok(service.getAvailability());
    }
}