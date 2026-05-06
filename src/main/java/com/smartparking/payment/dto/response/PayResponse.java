package com.smartparking.payment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PayResponse {
    private Long paymentId;
    private String status;
}