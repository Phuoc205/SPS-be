package com.smartparking.iot.service;

import com.smartparking.iot.entity.*;
import com.smartparking.iot.repository.DeviceRepository;
import com.smartparking.iot.repository.GateControlLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GateService {

    private final DeviceRepository repo;
    private final GateControlLogRepository logRepo;

    public List<Gate> getGates() {
        return repo.findAll()
                .stream()
                .filter(d -> d instanceof Gate)
                .map(d -> (Gate) d)
                .toList();
    }

    public void openGate(Long gateId, Long staffId, String reason) {
        control(gateId, true, staffId, reason);
    }

    public void closeGate(Long gateId, Long staffId, String reason) {
        control(gateId, false, staffId, reason);
    }

    private void control(Long gateId, boolean open, Long staffId, String reason) {

        Device device = repo.findById(gateId)
                .orElseThrow(() -> new RuntimeException("Device not found"));

        if (!(device instanceof Gate gate)) {
            throw new RuntimeException("Not a gate device");
        }

        gate.setOpened(open);
        repo.save(gate);

        GateControlLog log = new GateControlLog();
        log.setGateId(gateId);
        log.setStaffId(staffId);
        log.setAction(open ? "OPEN" : "CLOSE");
        log.setReason(reason);
        log.setCreatedAt(LocalDateTime.now());

        logRepo.save(log);
    }

    public List<GateControlLog> getLogs() {
        return logRepo.findAll();
    }
}