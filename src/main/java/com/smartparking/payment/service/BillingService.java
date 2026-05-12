package com.smartparking.payment.service;
import java.math.BigDecimal; 
public interface BillingService {
    double calculateAmount(Long sessionId);
    BigDecimal getTotalUnpaidDebt(Long userId); 
}