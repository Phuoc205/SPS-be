package com.smartparking.payment.dto.response;

import com.smartparking.user.entity.UserRole;
import com.smartparking.user.entity.VehicleType;
import com.smartparking.payment.entity.enumeration.PricingPlanType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PricingResponse {

    private Long id;
    private UserRole userRole;
    private VehicleType vehicleType;
    private PricingPlanType planType;
    private Double price;
    private boolean active;
}
