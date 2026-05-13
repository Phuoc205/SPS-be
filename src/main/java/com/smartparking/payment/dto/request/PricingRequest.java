package com.smartparking.payment.dto.request;
import lombok.Data;
import com.smartparking.user.entity.UserRole;
import com.smartparking.user.entity.VehicleType;
import com.smartparking.payment.entity.enumeration.PricingPlanType;

@Data
public class PricingRequest {

    private UserRole userRole;

    private VehicleType vehicleType;

    private PricingPlanType planType;

    private Double price;

    private boolean active;
}
