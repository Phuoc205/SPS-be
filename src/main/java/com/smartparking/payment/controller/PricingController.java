package com.smartparking.payment.controller;

import com.smartparking.payment.entity.Pricing;
import com.smartparking.payment.service.PricingService;
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

    @GetMapping("/active/{userType}")
    public ResponseEntity<?> getActivePrice(@PathVariable String userType) {
        try {
            return ResponseEntity.ok(pricingService.getActivePriceByUserType(userType.toUpperCase()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Pricing pricing) {
        if(pricing.getUserType() != null) {
            pricing.setUserType(pricing.getUserType().toUpperCase());
        }
        return ResponseEntity.ok(pricingService.createPricing(pricing));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Pricing pricing) {
        try {
            if(pricing.getUserType() != null) {
                pricing.setUserType(pricing.getUserType().toUpperCase());
            }
            return ResponseEntity.ok(pricingService.updatePricing(id, pricing));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            pricingService.deletePricing(id);
            return ResponseEntity.ok("Đã xóa cấu hình giá thành công!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Lỗi khi xóa cấu hình giá: " + e.getMessage());
        }
    }
}