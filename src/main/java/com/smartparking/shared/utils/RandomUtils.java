package com.smartparking.shared.utils;

import java.util.UUID;

public class RandomUtils {

    public static String generateTicketCode() {
        return "TICKET-" + UUID.randomUUID().toString().substring(0, 8);
    }

    public static String generatePaymentCode() {
        return "PAY-" + UUID.randomUUID().toString().substring(0, 8);
    }
}