package com.smartparking.payment.repository;

import com.smartparking.payment.entity.Invoice;
import com.smartparking.payment.entity.enumeration.InvoiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findByUserId(Long userId);

    List<Invoice> findByStatus(String status);

    List<Invoice> findByUserIdAndStatus(Long userId, InvoiceStatus status);

    
    @Query("""
        SELECT COALESCE(SUM(i.totalAmount), 0)
        FROM Invoice i
        WHERE i.status = com.smartparking.payment.entity.enumeration.InvoiceStatus.PAID
    """)
    Double getTotalRevenue();

    @Query("""
        SELECT COUNT(i)
        FROM Invoice i
        WHERE i.status = com.smartparking.payment.entity.enumeration.InvoiceStatus.PAID
    """)
    Long getTotalPaidInvoices();
}