package com.smartparking.parking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CheckOutResponse {
    private Long sessionId;
    private String slotName;
    private String message;
}
