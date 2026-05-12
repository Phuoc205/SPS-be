package com.smartparking.payment.controller;
import com.smartparking.payment.entity.enumeration.PaymentStatus; // Đường dẫn có thể hơi khác tùy cấu trúc của bạn
import com.smartparking.payment.entity.Invoice;
import com.smartparking.payment.entity.Payment;
import com.smartparking.payment.entity.enumeration.InvoiceStatus;
import com.smartparking.payment.repository.InvoiceRepository;
import com.smartparking.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin
public class PaymentController {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @PostMapping("/{invoiceId}/pay")
    public ResponseEntity<?> processPayment(@PathVariable Long invoiceId) {
        // 1. Tìm hóa đơn trong DB
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn"));

        if (invoice.getStatus() == InvoiceStatus.PAID) {
            return ResponseEntity.badRequest().body("Hóa đơn này đã được thanh toán trước đó.");
        }

        // 2. Cập nhật trạng thái hóa đơn thành PAID (Gạch nợ)
        invoice.setStatus(InvoiceStatus.PAID);
        invoiceRepository.save(invoice);

        // 3. Tạo bản ghi lịch sử thanh toán mới (Lưu vào bảng payment)
        Payment payment = new Payment();
        payment.setInvoiceId(invoiceId);
        payment.setUserId(invoice.getUserId());
        payment.setAmount(invoice.getTotalAmount());
        payment.setCreatedAt(LocalDateTime.now());
        payment.setProvider("BKPAY");
        payment.setStatus(PaymentStatus.PAID); // Hoặc PaymentStatus.SUCCESS tùy cách bạn đặt tên trong Enum
        
        paymentRepository.save(payment);

        return ResponseEntity.ok("Thanh toán thành công");
    }
}