package com.smartparking.payment.controller;

import com.smartparking.payment.entity.Pricing;
import com.smartparking.payment.service.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pricing")
@CrossOrigin(origins = "*")
public class PricingController {

    @Autowired
    private PricingService pricingService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(pricingService.getAllPricings());
    }

    @GetMapping("/active/{vehicleType}")
    public ResponseEntity<?> getActivePrice(@PathVariable String vehicleType) {
        try {
            return ResponseEntity.ok(pricingService.getActivePriceByUserType(vehicleType));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Pricing pricing) {
        try {
            return ResponseEntity.ok(pricingService.createPricing(pricing));
        } catch (RuntimeException e) {
            // Trả 409 Conflict nếu trùng cấu hình
            if (e.getMessage() != null && e.getMessage().startsWith("DUPLICATE")) {
                return ResponseEntity.status(409).body(e.getMessage());
            }
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Pricing pricing) {
        try {
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
            return ResponseEntity.badRequest().body("Lỗi khi xóa: " + e.getMessage());
        }
    }
}