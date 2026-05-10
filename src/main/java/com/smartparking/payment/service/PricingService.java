package com.smartparking.payment.service;

import com.smartparking.payment.entity.Pricing;
import java.util.List;
public interface PricingService {

    public List<Pricing> getAllPricings();
    public Pricing getActivePriceByUserType(String userType);
    public Pricing createPricing(Pricing pricing);
    public Pricing updatePricing(Long id, Pricing updatedPricing);
    public void deletePricing(Long id);
}