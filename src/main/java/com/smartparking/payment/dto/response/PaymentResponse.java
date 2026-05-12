package com.smartparking.payment.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentResponse {

    private Long paymentId;
    private Long userId;
    private Double amount;
    private String status;
    private LocalDateTime createdAt;

    public PaymentResponse(Long id, Long userId, double amount, String status, LocalDateTime createdAt) {
        this.paymentId = id;
        this.userId = userId;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Nhớ tạo thêm Getter và Setter cho createdAt
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}