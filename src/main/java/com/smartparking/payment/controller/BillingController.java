package com.smartparking.payment.controller;

import com.smartparking.payment.service.BillingService;
import com.smartparking.payment.service.PaymentService; // Fix: Import PaymentService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections; // Fix: Import Collections
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/billing")
public class BillingController {

    @Autowired
    private BillingService billingService;

    @Autowired
    private PaymentService paymentService; // Fix: Inject PaymentService

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

    @GetMapping("/user/debt")
    public ResponseEntity<?> getCurrentDebt(@RequestParam Long userId) {
        BigDecimal totalDebt = billingService.getTotalUnpaidDebt(userId);
        return ResponseEntity.ok(Collections.singletonMap("totalDebt", totalDebt));
    }

    @GetMapping("/user/history")
    public ResponseEntity<?> getPaymentHistory(@RequestParam Long userId) {
        return ResponseEntity.ok(paymentService.getPaymentHistory(userId));
    }

    @PostMapping("/user/pay-all")
    public ResponseEntity<?> payAll(@RequestParam Long userId) {
        try {
            return ResponseEntity.ok(paymentService.payAllDebts(userId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        }
    }
}