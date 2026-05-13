package com.smartparking.dashboard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartparking.dashboard.service.DashboardService;
import com.smartparking.dashboard.dto.response.DashboardSummaryResponse;
import com.smartparking.dashboard.dto.response.RecentActivityResponse;
import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/summary")
    public DashboardSummaryResponse getSummary() {
        return dashboardService.getSummary();
    }

    @GetMapping("/recent-activities")
    public List<RecentActivityResponse> getRecentActivities() {
        return dashboardService.getRecentActivities();
    }
}
