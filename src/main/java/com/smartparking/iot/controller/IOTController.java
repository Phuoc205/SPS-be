package com.smartparking.iot.controller;

import com.smartparking.iot.dto.request.IOTRequest;
import com.smartparking.iot.service.IOTService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/iot")
public class IOTController {

    @Autowired
    private IOTService service;

    @PostMapping("/update-slot")
    public ResponseEntity<?> updateSlot(@RequestBody IOTRequest request) {
        service.updateSlotStatus(request);
        return ResponseEntity.ok("Updated");
    }
}