package com.smartparking.payment.controller;

import com.smartparking.payment.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/billing")
public class BillingController {

    @Autowired
    private BillingService billingService;

    /**
     * API xem trước số tiền cần thanh toán của một phiên đỗ xe
     * Method: GET
     * URL: /api/billing/calculate/{sessionId}
     */
    @GetMapping("/calculate/{sessionId}")
    public ResponseEntity<?> calculateBillingAmount(@PathVariable Long sessionId) {
        try {
            double amount = billingService.calculateAmount(sessionId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("sessionId", sessionId);
            response.put("totalAmount", amount);
            response.put("currency", "VND"); 
            response.put("message", "Tính toán chi phí thành công");

            return ResponseEntity.ok(response);
            
        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}