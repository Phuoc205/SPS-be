package com.smartparking.iot.dto.request;
import lombok.Data;

@Data
public class GateControlRequest {
    private String reason;
    private Long staffId;
    private String action;
}
