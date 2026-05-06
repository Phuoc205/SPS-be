package com.smartparking.payment.service.impl;

import com.smartparking.payment.service.BillingService;
import com.smartparking.payment.service.PricingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceImpl implements BillingService {

    @Autowired
    private PricingService pricingService;

    @Override
    public double calculateAmount(Long sessionId) {

        // 👉 giả lập: 2 giờ
        double hours = 2;

        double price = pricingService.getPricePerHour("STUDENT");

        return hours * price;
    }
}