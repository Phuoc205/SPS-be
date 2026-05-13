package com.smartparking.parking.service;

import com.smartparking.parking.dto.response.*;
import java.util.List;

public interface ParkingService {

    CheckInResponse checkIn(String cardId);

    CheckOutResponse checkOut(String cardId);

    List<ParkingHistoryResponse> getHistoryByUser(Long userId);
}