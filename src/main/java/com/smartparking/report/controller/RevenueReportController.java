package com.smartparking.report.controller;

import com.smartparking.report.service.RevenueReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class RevenueReportController {

    private final RevenueReportService service;

    @GetMapping("/revenue")
    public Double getRevenue() {
        return service.getTotalRevenue();
    }

    @GetMapping("/usage")
    public Long getUsage() {
        return service.getTotalUsage();
    }
}