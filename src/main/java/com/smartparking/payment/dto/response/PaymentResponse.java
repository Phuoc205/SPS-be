package com.smartparking.payment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentResponse {

    private Long paymentId;
    private Long userId;
    private Double amount;
    private String status;
}