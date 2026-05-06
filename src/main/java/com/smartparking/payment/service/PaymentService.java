package com.smartparking.payment.service;

import com.smartparking.payment.dto.request.PaymentRequest;
import com.smartparking.payment.dto.response.*;

public interface PaymentService {

    PaymentResponse createPayment(PaymentRequest request);

    PayResponse pay(Long paymentId);
}