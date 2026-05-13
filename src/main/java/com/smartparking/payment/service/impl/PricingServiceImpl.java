package com.smartparking.payment.service.impl;

import com.smartparking.payment.entity.Pricing;
import com.smartparking.payment.repository.PricingRepository;
import com.smartparking.payment.service.PricingService;
import com.smartparking.user.entity.UserRole;
import com.smartparking.user.entity.VehicleType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.smartparking.payment.entity.enumeration.PricingPlanType;

@Service
public class PricingServiceImpl implements PricingService {

    @Autowired
    private PricingRepository pricingRepository;

    @Override
    public Pricing getActivePricing(
            UserRole userRole,
            VehicleType vehicleType,
            PricingPlanType planType
    ) {
        return pricingRepository
                .findByUserRoleAndVehicleTypeAndPlanTypeAndActiveTrue(
                        userRole,
                        vehicleType,
                        planType
                )
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy pricing")
                );
    }

    @Override
    public Pricing createPricing(Pricing pricing) {

        // đảm bảo chỉ 1 active per (role, vehicle, plan)
        pricingRepository
                .findByUserRoleAndVehicleTypeAndPlanTypeAndActiveTrue(
                        pricing.getUserRole(),
                        pricing.getVehicleType(),
                        pricing.getPlanType()
                )
                .ifPresent(old -> {
                    old.setActive(false);
                    pricingRepository.save(old);
                });

        pricing.setActive(true);
        return pricingRepository.save(pricing);
    }

    @Override
    public Pricing updatePricing(Long id, Pricing req) {

        Pricing existing = pricingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy pricing"));

        existing.setUserRole(req.getUserRole());
        existing.setVehicleType(req.getVehicleType());
        existing.setPlanType(req.getPlanType());
        existing.setPrice(req.getPrice());
        existing.setDurationValue(req.getDurationValue());
        existing.setActive(req.isActive());

        return pricingRepository.save(existing);
    }

    @Override
    public List<Pricing> getAllPricings() {
        return pricingRepository.findAll();
    }

    @Override
    public void deletePricing(Long id) {
        pricingRepository.deleteById(id);
    }
}