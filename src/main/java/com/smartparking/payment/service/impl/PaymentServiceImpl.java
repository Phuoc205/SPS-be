package com.smartparking.payment.service.impl;

import com.smartparking.payment.dto.request.PaymentRequest;
import com.smartparking.payment.dto.response.*;
import com.smartparking.payment.entity.Payment;
import com.smartparking.payment.entity.enumeration.PaymentStatus;
import com.smartparking.payment.repository.PaymentRepository;
import com.smartparking.payment.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository repo;

    @Autowired
    private BillingService billingService;

    @Autowired
    private com.smartparking.payment.repository.InvoiceRepository invoiceRepository;

    @Override
    public PaymentResponse createPayment(PaymentRequest request) {
        
        double totalAmount = 0;
        
        // Gom nhóm tính tiền theo chu kỳ
        if (request.getSessionIds() != null) {
            for (Long sessionId : request.getSessionIds()) {
                totalAmount += billingService.calculateAmount(sessionId);
            }
        }

        Payment payment = new Payment();
        payment.setUserId(request.getUserId());
        payment.setAmount(totalAmount);
        payment.setStatus(PaymentStatus.PENDING); // Chờ BKPay xác nhận
        payment.setCreatedAt(LocalDateTime.now()); // Lưu thời gian tạo

        repo.save(payment);

        return new PaymentResponse(
            payment.getId(),
            payment.getUserId(),
            payment.getAmount(),
            payment.getStatus().name(),
            payment.getCreatedAt()
        );
    }

    @Override
    public PayResponse pay(Long paymentId) {
        Payment payment = repo.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giao dịch thanh toán"));
        
        payment.setStatus(PaymentStatus.PAID); 

        repo.save(payment);

        return new PayResponse(
                payment.getId(),
                payment.getStatus().name()
        );
    }

    @Override
    public List<PaymentResponse> getPaymentHistory(Long userId) {
        List<Payment> successfulPayments = repo.findByUserIdAndStatus(userId, PaymentStatus.PAID);
        
        return successfulPayments.stream().map(payment -> new PaymentResponse(
                payment.getId(),
                payment.getUserId(),
                payment.getAmount(),
                payment.getStatus().name(),
                payment.getCreatedAt() // Trả về thêm thời gian
        )).collect(Collectors.toList());
    }

    @Override
    public PayResponse payAllDebts(Long userId) {
        // 1. Tìm tất cả hoá đơn đang nợ
        List<com.smartparking.payment.entity.Invoice> unpaidInvoices = 
            invoiceRepository.findByUserIdAndStatus(userId, com.smartparking.payment.entity.enumeration.InvoiceStatus.GENERATED);

        if (unpaidInvoices.isEmpty()) {
            throw new RuntimeException("Không có dư nợ để thanh toán!");
        }

        double totalAmount = unpaidInvoices.stream()
            .mapToDouble(com.smartparking.payment.entity.Invoice::getTotalAmount)
            .sum();

        // 2. Đổi trạng thái toàn bộ hoá đơn nợ thành PAID
        for (com.smartparking.payment.entity.Invoice inv : unpaidInvoices) {
            inv.setStatus(com.smartparking.payment.entity.enumeration.InvoiceStatus.PAID);
            invoiceRepository.save(inv);
        }

        // 3. Tạo một bản ghi Lịch sử giao dịch (Payment) mới
        Payment payment = new Payment();
        payment.setUserId(userId);
        payment.setAmount(totalAmount);
        payment.setStatus(PaymentStatus.PAID); // Đánh dấu là đã thanh toán thành công
        payment.setCreatedAt(LocalDateTime.now()); // Lưu lại giờ thanh toán thành công
        payment = repo.save(payment);

        return new PayResponse(payment.getId(), payment.getStatus().name());
    }
}