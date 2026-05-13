package com.smartparking.iot.repository;

import com.smartparking.iot.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;
public interface DeviceRepository extends JpaRepository<Device, Long> {

    Optional<Device> findByDeviceCode(String code);
    List<Device> findByDeviceCodeContainingIgnoreCase(String keyword);
    long countByActive(boolean active);
}