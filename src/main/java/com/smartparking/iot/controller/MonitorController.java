package com.smartparking.iot.controller;

import com.smartparking.iot.service.MonitorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/monitor")
public class MonitorController {

    @Autowired
    private MonitorService service;

    @GetMapping("/slots")
    public ResponseEntity<?> monitor() {
        return ResponseEntity.ok(service.getRealtimeSlots());
    }
}