package com.smartparking.iot.controller;

import com.smartparking.iot.entity.Device;
import com.smartparking.iot.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService service;

    @GetMapping
    public ResponseEntity<List<Device>> getAll(
            @RequestParam(required = false) String keyword
    ) {
        if (keyword != null && !keyword.isEmpty()) {
            return ResponseEntity.ok(service.search(keyword));
        }
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Device> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Device> create(@RequestBody Device device) {
        return ResponseEntity.ok(service.create(device));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Device> update(
            @PathVariable Long id,
            @RequestBody Device device
    ) {
        return ResponseEntity.ok(service.update(id, device));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}