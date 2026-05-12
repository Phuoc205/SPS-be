package com.smartparking.payment.service.impl;

import com.smartparking.parking.entity.ParkingSession;
import com.smartparking.parking.repository.ParkingSessionRepository;
import com.smartparking.payment.entity.Invoice;
import com.smartparking.payment.entity.enumeration.InvoiceStatus;
import com.smartparking.payment.entity.Pricing;
import com.smartparking.payment.repository.InvoiceRepository;
import com.smartparking.payment.service.BillingService;
import com.smartparking.payment.service.PricingService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceImpl implements BillingService {

    @Autowired
    private PricingService pricingService;

    @Autowired
    private ParkingSessionRepository sessionRepository; 

    @Autowired
    private InvoiceRepository invoiceRepository; 

    @Override
    public double calculateAmount(Long sessionId) {
        ParkingSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phiên đỗ xe với ID: " + sessionId));

        LocalDateTime checkIn = session.getCheckInTime();
        LocalDateTime checkOut = (session.getCheckOutTime() != null) ? session.getCheckOutTime() : LocalDateTime.now();

        long minutes = Duration.between(checkIn, checkOut).toMinutes();

        double hours = Math.ceil(minutes / 60.0);
        
        if (hours == 0) {
            hours = 1; 
        }

        String userType = "STUDENT"; 

        Pricing activePricing = pricingService.getActivePriceByUserType(userType);
        double pricePerHour = activePricing.getPricePerHour();

        return hours * pricePerHour;
    }

    @Override
    public BigDecimal getTotalUnpaidDebt(Long userId) {
        List<Invoice> unpaidInvoices = invoiceRepository.findByUserIdAndStatus(userId, InvoiceStatus.GENERATED);
        double total = unpaidInvoices.stream()
                .mapToDouble(Invoice::getTotalAmount) 
                .sum();
        return BigDecimal.valueOf(total);
    }
}