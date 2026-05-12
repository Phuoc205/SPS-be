package com.smartparking.parking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParkingHistoryResponse {

    private Long sessionId;

    private String slotName;

    private String status;

    private String checkInTime;

    private String checkOutTime;

    private long durationHours;

    private double amount;
}
