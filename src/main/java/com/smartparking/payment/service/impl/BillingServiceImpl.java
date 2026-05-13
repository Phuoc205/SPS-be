package com.smartparking.payment.service.impl;

import com.smartparking.parking.entity.ParkingSession;
import com.smartparking.parking.repository.ParkingSessionRepository;
import com.smartparking.payment.entity.Pricing;
import com.smartparking.payment.service.BillingService;
import com.smartparking.payment.service.PricingService;
import com.smartparking.user.entity.UserRole;
import com.smartparking.user.entity.VehicleType;
import com.smartparking.payment.entity.enumeration.PricingPlanType;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Duration;

@Service
public class BillingServiceImpl implements BillingService {

    @Autowired
    private PricingService pricingService;

    @Autowired
    private ParkingSessionRepository sessionRepo;

    @Override
    public double calculateAmount(Long sessionId) {

        ParkingSession session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        LocalDateTime checkIn = session.getCheckInTime();
        LocalDateTime checkOut = session.getCheckOutTime() != null
                ? session.getCheckOutTime()
                : LocalDateTime.now();

        long minutes = Duration.between(checkIn, checkOut).toMinutes();

        UserRole role = session.getUser().getRole();
        VehicleType vehicleType = session.getVehicle().getType();

        PricingPlanType planType = resolvePlanType(session);

        Pricing pricing = pricingService.getActivePricing(
                role,
                vehicleType,
                planType
        );

        return calculateByPlan(pricing, minutes);
    }

    private PricingPlanType resolvePlanType(ParkingSession session) {

        if (session.getTicket() == null) {
            return PricingPlanType.HOURLY;
        }

        return switch (session.getTicket().getType()) {

            case TEMPORARY -> PricingPlanType.HOURLY;

            case SUBSCRIPTION -> PricingPlanType.MONTHLY;
        };
    }

    private double calculateByPlan(Pricing pricing, long minutes) {

        return switch (pricing.getPlanType()) {

            case HOURLY -> {
                double hours = Math.ceil(minutes / 60.0);
                yield hours * pricing.getPrice();
            }

            case DAILY -> {
                double days = Math.ceil(minutes / (60.0 * 24));
                yield days * pricing.getPrice();
            }

            case WEEKLY -> {
                double weeks = Math.ceil(minutes / (60.0 * 24 * 7));
                yield weeks * pricing.getPrice();
            }

            case MONTHLY -> {
                double months = Math.ceil(minutes / (60.0 * 24 * 30));
                yield months * pricing.getPrice();
            }
        };
    }
}