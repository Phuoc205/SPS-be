package com.smartparking.parking.controller;

import com.smartparking.payment.repository.InvoiceRepository;
import com.smartparking.payment.entity.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@CrossOrigin // Cho phép React gọi API
public class InvoiceController {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Invoice>> getByUser(@PathVariable Long userId) {
        // Lấy dữ liệu thật từ bảng invoice trong DB
        List<Invoice> invoices = invoiceRepository.findByUserId(userId);
        return ResponseEntity.ok(invoices);
    }
}