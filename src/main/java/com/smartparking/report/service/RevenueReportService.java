package com.smartparking.report.service;

import com.smartparking.payment.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RevenueReportService {

    private final InvoiceRepository invoiceRepository;

    public Double getTotalRevenue() {
        return invoiceRepository.getTotalRevenue();
    }

    public Long getTotalUsage() {
        return invoiceRepository.getTotalPaidInvoices();
    }
}