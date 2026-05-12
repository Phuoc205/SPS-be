package com.smartparking.payment.repository;

import com.smartparking.payment.entity.Payment;
import com.smartparking.payment.entity.enumeration.PaymentStatus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByUserId(Long userId);

    List<Payment> findByStatus(String status);

    List<Payment> findByUserIdAndStatus(Long userId, PaymentStatus status);
}
