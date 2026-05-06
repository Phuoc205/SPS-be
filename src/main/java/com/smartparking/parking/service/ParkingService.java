package com.smartparking.parking.service;

import com.smartparking.parking.dto.response.*;

public interface ParkingService {

    CheckInResponse checkIn(String cardId);

    CheckOutResponse checkOut(String cardId);
}