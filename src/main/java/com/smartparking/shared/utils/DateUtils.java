package com.smartparking.shared.utils;

import java.time.Duration;
import java.time.LocalDateTime;

public class DateUtils {

    public static long calculateHours(LocalDateTime start, LocalDateTime end) {
        return Duration.between(start, end).toHours();
    }
}