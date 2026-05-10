package com.smartparking.iot.controller;

import com.smartparking.iot.entity.Device;
import com.smartparking.iot.service.DeviceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    @Autowired
    private DeviceService service;

    @PostMapping
    public ResponseEntity<Device> create(@RequestBody Device device) {
        return ResponseEntity.ok(service.create(device));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Device> update(@PathVariable Long id, @RequestBody Device device) {
        return ResponseEntity.ok(service.update(id, device));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Device>> getAll(@RequestParam(required = false) String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            return ResponseEntity.ok(service.getByKeyword(keyword)); 
        }
        return ResponseEntity.ok(service.getAll());
    }
}