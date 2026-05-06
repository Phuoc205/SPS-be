package com.smartparking.payment.repository;

import com.smartparking.payment.entity.PaymentSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentSessionRepository extends JpaRepository<PaymentSession, Long> {

    List<PaymentSession> findByUserId(Long userId);

    List<PaymentSession> findByUserIdAndCheckOutTimeIsNull(Long userId);
}