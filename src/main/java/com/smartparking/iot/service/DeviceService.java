package com.smartparking.iot.service;

import com.smartparking.iot.entity.Device;

import java.util.List;
import java.util.Optional;

public interface DeviceService {

    List<Device> getAll();

    Optional<Device> getById(Long id);

    Device create(Device device);

    Device update(Long id, Device device);

    void delete(Long id);

    List<Device> getByKeyword(String keyword);
}