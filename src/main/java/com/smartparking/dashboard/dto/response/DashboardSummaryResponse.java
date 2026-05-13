package com.smartparking.dashboard.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardSummaryResponse {

    private SlotStats slots;
    private RevenueStats revenue;
    private DeviceStats devices;
    private UserStats users;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SlotStats {
        private long total;
        private long occupied;
        private long available;
        private int fillPercentage;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RevenueStats {
        private double today;
        private double vsYesterdayPercent;
        private long totalVehiclesToday;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeviceStats {
        private long total;
        private long active;
        private long offline;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserStats {
        private long newToday;
        private long totalActive;
    }
}