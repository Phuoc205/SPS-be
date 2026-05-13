package com.smartparking.payment.service;

import com.smartparking.payment.entity.Pricing;
import com.smartparking.payment.entity.enumeration.PricingPlanType;
import com.smartparking.user.entity.UserRole;
import com.smartparking.user.entity.VehicleType;

import java.util.List;
public interface PricingService {

    public List<Pricing> getAllPricings();
    public Pricing getActivePricing(
            UserRole userRole,
            VehicleType vehicleType,
            PricingPlanType planType
    );
    public Pricing createPricing(Pricing pricing);
    public Pricing updatePricing(Long id, Pricing updatedPricing);
    public void deletePricing(Long id);
}