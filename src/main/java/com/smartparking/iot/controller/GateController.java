package com.smartparking.iot.controller;

import com.smartparking.iot.service.GateService;
import com.smartparking.iot.dto.request.GateControlRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gates")
@RequiredArgsConstructor
public class GateController {

    private final GateService gateService;

    @GetMapping
    public ResponseEntity<?> getGates() {
        return ResponseEntity.ok(gateService.getGates());
    }

    @PostMapping("/{id}/open")
    public ResponseEntity<?> openGate(
            @PathVariable Long id,
            @RequestBody GateControlRequest req
    ) {
        gateService.openGate(id, req.getStaffId(), req.getReason());
        return ResponseEntity.ok("GATE OPENED");
    }

    @PostMapping("/{id}/close")
    public ResponseEntity<?> closeGate(
            @PathVariable Long id,
            @RequestBody GateControlRequest req
    ) {
        gateService.closeGate(id, req.getStaffId(), req.getReason());
        return ResponseEntity.ok("GATE CLOSED");
    }

    @GetMapping("/logs")
    public ResponseEntity<?> getLogs() {
        return ResponseEntity.ok(gateService.getLogs());
    }
}