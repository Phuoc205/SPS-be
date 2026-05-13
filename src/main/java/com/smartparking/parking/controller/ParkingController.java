package com.smartparking.parking.controller;

import com.smartparking.parking.dto.request.*;
import com.smartparking.parking.dto.response.*;
import com.smartparking.parking.service.ParkingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking")
public class ParkingController {

    @Autowired
    private ParkingService service;

    @PostMapping("/check-in")
    public ResponseEntity<CheckInResponse> checkIn(@RequestBody CheckInRequest request) {
        return ResponseEntity.ok(service.checkIn(request.getCardId()));
    }

    @PostMapping("/check-out")
    public ResponseEntity<CheckOutResponse> checkOut(@RequestBody CheckOutRequest request) {
        return ResponseEntity.ok(service.checkOut(request.getCardId()));
    }

    @GetMapping("/history/user/{userId}")
    public ResponseEntity<List<ParkingHistoryResponse>> getHistoryByUser(
            @PathVariable Long userId) {
        return ResponseEntity.ok(service.getHistoryByUser(userId));
    }
}