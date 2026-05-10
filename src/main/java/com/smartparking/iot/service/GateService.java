package com.smartparking.iot.service;
import com.smartparking.iot.entity.Gate;
import com.smartparking.iot.entity.GateControlLog;
import com.smartparking.iot.repository.GateControlLogRepository;
import com.smartparking.iot.repository.GateRepository;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class GateService {

    private final GateControlLogRepository logRepo;
    private final GateRepository gateRepo;

    public List<Gate> getGates() {
        return gateRepo.findAll();
    }

    public void openGate(Long gateId, Long staffId, String reason) {
        control(gateId, "OPEN", staffId, reason);
    }

    public void closeGate(Long gateId, Long staffId, String reason) {
        control(gateId, "CLOSE", staffId, reason);
    }

    private void control(Long gateId, String action, Long staffId, String reason) {

        if (!action.equals("OPEN") && !action.equals("CLOSE")) {
            throw new RuntimeException("Invalid action");
        }

        // 1. IoT call (ESP32 / MQTT)
        sendToDevice(gateId, action);

        // 2. log
        GateControlLog log = new GateControlLog();
        log.setGateId(gateId);
        log.setStaffId(staffId);
        log.setAction(action);
        log.setReason(reason);
        log.setCreatedAt(LocalDateTime.now());

        logRepo.save(log);
    }

    private void sendToDevice(Long gateId, String action) {
        System.out.println("SEND TO GATE " + gateId + ": " + action);
    }

    public List<GateControlLog> getLogs() {
        return logRepo.findAll();
    }
}