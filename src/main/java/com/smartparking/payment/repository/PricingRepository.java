package com.smartparking.payment.repository;

import com.smartparking.payment.entity.Pricing;
import com.smartparking.payment.entity.enumeration.PricingPlanType;
import com.smartparking.user.entity.UserRole;
import com.smartparking.user.entity.VehicleType;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PricingRepository extends JpaRepository<Pricing, Long> {

    Optional<Pricing> findByUserRoleAndVehicleTypeAndPlanTypeAndActiveTrue(
        UserRole userRole,
        VehicleType vehicleType,
        PricingPlanType planType
    );
}