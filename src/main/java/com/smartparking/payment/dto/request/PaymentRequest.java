package com.smartparking.payment.dto.request;

import lombok.Data;
import com.smartparking.payment.entity.enumeration.PaymentType;
import java.util.List;

@Data
public class PaymentRequest {

    private Long userId;

    private List<Long> sessionIds;
    // optional: nếu pay theo session (khách vãng lai)
    private Long sessionId;

    // optional: nếu pay theo invoice (chu kỳ SV/GV)
    private Long invoiceId;

    private PaymentType type;
    // SESSION / INVOICE
}