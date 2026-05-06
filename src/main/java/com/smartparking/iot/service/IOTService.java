package com.smartparking.iot.service;

import com.smartparking.iot.dto.request.IOTRequest;

public interface IOTService {

    void updateSlotStatus(IOTRequest request);
}