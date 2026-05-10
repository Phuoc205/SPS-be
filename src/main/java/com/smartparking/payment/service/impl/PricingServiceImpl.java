package com.smartparking.payment.service.impl;

import com.smartparking.payment.entity.Pricing;
import com.smartparking.payment.repository.PricingRepository;
import com.smartparking.payment.service.PricingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class PricingServiceImpl implements PricingService {

    @Autowired
    private PricingRepository pricingRepository;

    @Override
    public List<Pricing> getAllPricings() {
        return pricingRepository.findAll();
    }

    @Override
    public Pricing getActivePriceByUserType(String userType) {
        return pricingRepository.findByUserTypeAndActiveTrue(userType)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy cấu hình giá đang hoạt động cho: " + userType));
    }

    @Override
    public Pricing createPricing(Pricing pricing) {
        if (pricing.isActive()) {
            deactivateOldPricing(pricing.getUserType());
        }
        return pricingRepository.save(pricing);
    }

    @Override
    public Pricing updatePricing(Long id, Pricing updatedPricing) {
        Pricing existing = pricingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy cấu hình giá với ID: " + id));

        existing.setUserType(updatedPricing.getUserType());
        existing.setPricePerHour(updatedPricing.getPricePerHour());
        
        if (updatedPricing.isActive() && !existing.isActive()) {
            deactivateOldPricing(updatedPricing.getUserType());
        }
        existing.setActive(updatedPricing.isActive());

        return pricingRepository.save(existing);
    }

    @Override
    public void deletePricing(Long id) {
        pricingRepository.deleteById(id);
    }

    private void deactivateOldPricing(String userType) {
        pricingRepository.findByUserTypeAndActiveTrue(userType).ifPresent(oldPricing -> {
            oldPricing.setActive(false);
            pricingRepository.save(oldPricing);
        });
    }
}