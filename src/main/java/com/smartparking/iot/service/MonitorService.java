package com.smartparking.iot.service;

import java.util.List;
import java.util.Map;

public interface MonitorService {

    List<Map<String, Object>> getRealtimeSlots();
}