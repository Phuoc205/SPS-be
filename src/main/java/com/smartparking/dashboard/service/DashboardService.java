package com.smartparking.dashboard.service;

import com.smartparking.dashboard.dto.response.DashboardSummaryResponse;
import com.smartparking.dashboard.dto.response.RecentActivityResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.smartparking.parking.repository.SlotRepository;
import com.smartparking.iot.repository.DeviceRepository;
import com.smartparking.user.repository.UserRepository;
import com.smartparking.payment.repository.PaymentRepository;
import com.smartparking.parking.entity.ParkingSession;
import com.smartparking.parking.repository.ParkingSessionRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final SlotRepository slotRepository;
    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final ParkingSessionRepository parkingSessionRepository;

    public DashboardSummaryResponse getSummary() {

        LocalDate today = LocalDate.now();
        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.plusDays(1).atStartOfDay();

        long totalSlots = slotRepository.count();

        long occupiedSlots =
            slotRepository.countByOccupied(true);

        long availableSlots =
            slotRepository.countByOccupied(false);

        int fillPercentage =
                totalSlots == 0
                        ? 0
                        : (int) ((occupiedSlots * 100) / totalSlots);

        
        double todayRevenue = paymentRepository.getRevenueBetween(start, end);

        double yesterdayRevenue =
                paymentRepository.getYesterdayRevenue();

        double revenuePercent =
                yesterdayRevenue == 0
                        ? 100
                        : ((todayRevenue - yesterdayRevenue)
                        / yesterdayRevenue) * 100;

        long totalVehiclesToday =
                parkingSessionRepository.countTodayVehicles(start, end);

        long totalDevices =
                deviceRepository.count();

        long activeDevices =
                deviceRepository.countByActive(true);

        long offlineDevices =
                totalDevices - activeDevices;

        long newUsersToday = userRepository.countByCreatedAtBetween(start, end);

        long totalActiveUsers =
                userRepository.countActiveUsers();

        return DashboardSummaryResponse.builder()
                .slots(
                        DashboardSummaryResponse.SlotStats.builder()
                                .total(totalSlots)
                                .occupied(occupiedSlots)
                                .available(availableSlots)
                                .fillPercentage(fillPercentage)
                                .build()
                )
                .revenue(
                        DashboardSummaryResponse.RevenueStats.builder()
                                .today(todayRevenue)
                                .vsYesterdayPercent(revenuePercent)
                                .totalVehiclesToday(totalVehiclesToday)
                                .build()
                )
                .devices(
                        DashboardSummaryResponse.DeviceStats.builder()
                                .total(totalDevices)
                                .active(activeDevices)
                                .offline(offlineDevices)
                                .build()
                )
                .users(
                        DashboardSummaryResponse.UserStats.builder()
                                .newToday(newUsersToday)
                                .totalActive(totalActiveUsers)
                                .build()
                )
                .build();
    }

    public List<RecentActivityResponse> getRecentActivities() {

        List<ParkingSession> sessions =
                parkingSessionRepository
                        .findTop10ByOrderByCheckInTimeDesc();

        return sessions.stream()
            .map(session -> {

                boolean checkedOut =
                        session.getCheckOutTime() != null;

                return RecentActivityResponse.builder()
                        .id(session.getId())
                        .licensePlate(session.getLicensePlate())
                        .action(
                                checkedOut
                                        ? "CHECK_OUT"
                                        : "CHECK_IN"
                        )
                        .gateName(
                                session.getSlot() != null
                                        ? session.getSlot().getSlotName()
                                        : "Unknown Slot"
                        )
                        .time(
                                checkedOut
                                        ? session.getCheckOutTime()
                                        : session.getCheckInTime()
                        )
                        .status("SUCCESS")
                        .fee(
                                checkedOut && session.getPrice() != null
                                        ? session.getPrice().getPricePerHour()
                                        : null
                        )
                        .build();
            })
            .toList();
    }
}
