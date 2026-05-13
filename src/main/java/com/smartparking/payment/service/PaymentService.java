package com.smartparking.payment.service;

import com.smartparking.payment.dto.request.PaymentRequest;
import com.smartparking.payment.dto.response.*;
import com.smartparking.payment.entity.Payment;
import java.util.List;
public interface PaymentService {

    PaymentResponse createPayment(PaymentRequest request);

    PayResponse pay(Long paymentId);
    List<Payment> getPaymentsByUserId(Long userId);
}