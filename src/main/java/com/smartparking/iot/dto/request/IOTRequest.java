package com.smartparking.iot.dto.request;

import lombok.Data;

@Data
public class IOTRequest {

    private String deviceCode;

    private String slotName;

    private boolean occupied; // true = có xe
}