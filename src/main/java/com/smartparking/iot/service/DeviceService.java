package com.smartparking.iot.service;

import com.smartparking.iot.entity.Device;
import com.smartparking.iot.repository.DeviceRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository repo;

    public List<Device> getAll() {
        return repo.findAll();
    }

    public Device getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Device not found"));
    }

    public Device create(Device device) {
        device.setActive(true);
        return repo.save(device);
    }

    public Device update(Long id, Device req) {

        Device device = getById(id);

        device.setDeviceCode(req.getDeviceCode());
        device.setLocation(req.getLocation());
        device.setActive(req.isActive());

        return repo.save(device);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<Device> search(String keyword) {
        return repo.findAll().stream()
                .filter(d -> d.getDeviceCode() != null &&
                        d.getDeviceCode().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }
}