package com.smartparking.payment.service.impl;

import com.smartparking.payment.repository.PricingRepository;
import com.smartparking.payment.service.PricingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PricingServiceImpl implements PricingService {

    @Autowired
    private PricingRepository repo;

    @Override
    public double getPricePerHour(String userType) {
        return repo.findByUserTypeAndActiveTrue(userType)
                .map(p -> p.getPricePerHour())
                .orElse(5000.0); // default
    }
}