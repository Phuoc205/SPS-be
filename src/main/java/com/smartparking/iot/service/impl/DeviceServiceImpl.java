package com.smartparking.iot.service.impl;

import com.smartparking.iot.entity.Device;
import com.smartparking.iot.repository.DeviceRepository;
import com.smartparking.iot.service.DeviceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository repo;

    @Override
    public List<Device> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Device> getById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Device create(Device device) {
        device.setActive(true);
        return repo.save(device);
    }

    @Override
    public Device update(Long id, Device deviceDetails) {

        Device device = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Device not found"));

        device.setDeviceCode(deviceDetails.getDeviceCode());
        device.setType(deviceDetails.getType());
        device.setLocation(deviceDetails.getLocation());
        device.setSlotId(deviceDetails.getSlotId());

        return repo.save(device);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}