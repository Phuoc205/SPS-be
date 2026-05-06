package com.smartparking.payment.repository;

import com.smartparking.payment.entity.Pricing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PricingRepository extends JpaRepository<Pricing, Long> {

    Optional<Pricing> findByUserTypeAndActiveTrue(String userType);
}