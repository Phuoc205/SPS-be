package com.smartparking.payment.service.impl;

import com.smartparking.payment.dto.request.PaymentRequest;
import com.smartparking.payment.dto.response.*;
import com.smartparking.payment.entity.Payment;
import com.smartparking.payment.entity.enumeration.PaymentStatus;
import com.smartparking.payment.repository.PaymentRepository;
import com.smartparking.payment.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository repo;

    @Autowired
    private BillingService billingService;

    @Override
    public PaymentResponse createPayment(PaymentRequest request) {

        double amount = billingService.calculateAmount(request.getSessionId());

        Payment payment = new Payment();
        payment.setUserId(request.getUserId());
        payment.setAmount(amount);
        payment.setStatus(PaymentStatus.PENDING);


        repo.save(payment);

        return new PaymentResponse(
            payment.getId(),
            payment.getUserId(),
            payment.getAmount(),
            payment.getStatus().name()
    );
    }

    @Override
    public PayResponse pay(Long paymentId) {

        Payment payment = repo.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        payment.setStatus(PaymentStatus.PENDING);

        repo.save(payment);

        return new PayResponse(
                payment.getId(),
                payment.getStatus().name()
        );
    }
}