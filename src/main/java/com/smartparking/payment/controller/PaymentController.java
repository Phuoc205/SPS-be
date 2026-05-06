package com.smartparking.payment.controller;

import com.smartparking.payment.dto.request.PaymentRequest;
import com.smartparking.payment.dto.response.*;
import com.smartparking.payment.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @PostMapping
    public ResponseEntity<PaymentResponse> create(@RequestBody PaymentRequest request) {
        return ResponseEntity.ok(service.createPayment(request));
    }

    @PostMapping("/{id}/pay")
    public ResponseEntity<PayResponse> pay(@PathVariable Long id) {
        return ResponseEntity.ok(service.pay(id));
    }
}