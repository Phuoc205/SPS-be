package com.smartparking.payment.repository;

import com.smartparking.payment.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findByUserId(Long userId);

    List<Invoice> findByStatus(String status);
}