package com.smartparking.iot.service;

import com.smartparking.iot.entity.Device;
import com.smartparking.iot.entity.Sensor;
import com.smartparking.iot.repository.DeviceRepository;
import com.smartparking.parking.entity.ParkingSlot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SensorService {

    private final DeviceRepository repo;

    public Sensor create(Sensor sensor) {
        return repo.save(sensor);
    }

    public Sensor assignSlot(Long sensorId, ParkingSlot slot) {

        Device device = repo.findById(sensorId)
                .orElseThrow(() -> new RuntimeException("Sensor not found"));

        if (!(device instanceof Sensor sensor)) {
            throw new RuntimeException("Not a sensor device");
        }

        sensor.setSlot(slot);

        return repo.save(sensor);
    }
}