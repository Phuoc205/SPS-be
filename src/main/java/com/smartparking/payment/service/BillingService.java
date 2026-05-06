package com.smartparking.payment.service;

public interface BillingService {

    double calculateAmount(Long sessionId);
}