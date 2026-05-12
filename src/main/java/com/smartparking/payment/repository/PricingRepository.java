package com.smartparking.payment.repository;

import com.smartparking.payment.entity.Pricing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PricingRepository extends JpaRepository<Pricing, Long> {

    List<Pricing> findByVehicleType(String vehicleType);

    Optional<Pricing> findByVehicleTypeAndType(String vehicleType, String type);
}