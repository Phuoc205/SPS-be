package com.smartparking.dashboard.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecentActivityResponse {

    private Long id;

    private String licensePlate;

    private String action;

    private String gateName;

    private LocalDateTime time;

    private String status;

    private Double fee;
}