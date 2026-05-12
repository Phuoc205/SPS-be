package com.smartparking.payment.service.impl;

import com.smartparking.payment.entity.Pricing;
import com.smartparking.payment.repository.PricingRepository;
import com.smartparking.payment.service.PricingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PricingServiceImpl implements PricingService {

    @Autowired
    private PricingRepository pricingRepository;

    @Override
    public List<Pricing> getAllPricings() {
        return pricingRepository.findAll();
    }

    @Override
    public Pricing getActivePriceByUserType(String vehicleType) {
        return pricingRepository.findByVehicleType(vehicleType.toUpperCase())
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "Không tìm thấy cấu hình giá cho loại xe: " + vehicleType));
    }

    @Override
    public Pricing createPricing(Pricing pricing) {
        String vehicleType = pricing.getVehicleType() != null
                ? pricing.getVehicleType().toUpperCase() : null;
        String type = pricing.getType() != null
                ? pricing.getType().toUpperCase() : null;

        pricing.setVehicleType(vehicleType);
        pricing.setType(type);

        // Chặn tạo trùng: mỗi (vehicleType + type) chỉ được 1 bản ghi
        pricingRepository.findByVehicleTypeAndType(vehicleType, type)
                .ifPresent(existing -> {
                    throw new RuntimeException(
                        "DUPLICATE: Đã tồn tại cấu hình giá cho "
                        + vehicleType + " - " + type
                        + " (ID: " + existing.getId() + ")"
                    );
                });

        // DAILY / MONTHLY không cần khung giờ → xoá nếu FE gửi lên
        if (!"HOURLY".equals(type)) {
            pricing.setWeekdayFromHour(null);
            pricing.setWeekdayToHour(null);
            pricing.setWeekendFromHour(null);
            pricing.setWeekendToHour(null);
        }

        return pricingRepository.save(pricing);
    }

    @Override
    public Pricing updatePricing(Long id, Pricing updatedPricing) {
        Pricing existing = pricingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Không tìm thấy cấu hình giá với ID: " + id));

        if (updatedPricing.getVehicleType() != null)
            existing.setVehicleType(updatedPricing.getVehicleType().toUpperCase());
        if (updatedPricing.getType() != null)
            existing.setType(updatedPricing.getType().toUpperCase());

        // Cập nhật giá
        if (updatedPricing.getPricePerHour() != null)
            existing.setPricePerHour(updatedPricing.getPricePerHour());
        if (updatedPricing.getPricePerDay() != null)
            existing.setPricePerDay(updatedPricing.getPricePerDay());
        if (updatedPricing.getPricePerMonth() != null)
            existing.setPricePerMonth(updatedPricing.getPricePerMonth());

        // Cập nhật khung giờ (chỉ áp dụng nếu type = HOURLY)
        if ("HOURLY".equals(existing.getType())) {
            if (updatedPricing.getWeekdayFromHour() != null)
                existing.setWeekdayFromHour(updatedPricing.getWeekdayFromHour());
            if (updatedPricing.getWeekdayToHour() != null)
                existing.setWeekdayToHour(updatedPricing.getWeekdayToHour());
            if (updatedPricing.getWeekendFromHour() != null)
                existing.setWeekendFromHour(updatedPricing.getWeekendFromHour());
            if (updatedPricing.getWeekendToHour() != null)
                existing.setWeekendToHour(updatedPricing.getWeekendToHour());
        } else {
            // DAILY / MONTHLY: xoá khung giờ nếu có
            existing.setWeekdayFromHour(null);
            existing.setWeekdayToHour(null);
            existing.setWeekendFromHour(null);
            existing.setWeekendToHour(null);
        }

        return pricingRepository.save(existing);
    }

    @Override
    public void deletePricing(Long id) {
        if (!pricingRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy cấu hình giá với ID: " + id);
        }
        pricingRepository.deleteById(id);
    }
}