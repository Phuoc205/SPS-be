package com.smartparking.payment.controller;

import com.smartparking.payment.entity.Pricing;
import com.smartparking.payment.service.PricingService;
import com.smartparking.user.entity.UserRole;
import com.smartparking.user.entity.VehicleType;
import com.smartparking.payment.dto.request.PricingRequest;
import com.smartparking.payment.entity.enumeration.PricingPlanType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pricing")
public class PricingController {

    @Autowired
    private PricingService pricingService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(pricingService.getAllPricings());
    }

    @GetMapping("/active")
    public ResponseEntity<?> getActivePrice(
            @RequestParam UserRole role,
            @RequestParam VehicleType vehicleType,
            @RequestParam PricingPlanType planType
    ) {
        return ResponseEntity.ok(
                pricingService.getActivePricing(role, vehicleType, planType)
        );
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PricingRequest request) {

        Pricing pricing = new Pricing();
        pricing.setUserRole(request.getUserRole());
        pricing.setVehicleType(request.getVehicleType());
        pricing.setPlanType(request.getPlanType());
        pricing.setPrice(request.getPrice());
        pricing.setActive(request.isActive());

        return ResponseEntity.ok(pricingService.createPricing(pricing));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody PricingRequest request
    ) {

        Pricing pricing = new Pricing();
        pricing.setUserRole(request.getUserRole());
        pricing.setVehicleType(request.getVehicleType());
        pricing.setPlanType(request.getPlanType());
        pricing.setPrice(request.getPrice());
        pricing.setActive(request.isActive());

        return ResponseEntity.ok(
                pricingService.updatePricing(id, pricing)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        pricingService.deletePricing(id);
        return ResponseEntity.ok("Deleted pricing successfully");
    }
}