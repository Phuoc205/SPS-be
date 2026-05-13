package com.smartparking.payment.repository;

import com.smartparking.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByUserId(Long userId);

    List<Payment> findByStatus(String status);

    @Query("""
        SELECT COALESCE(SUM(p.amount), 0)
        FROM Payment p
        WHERE p.createdAt >= :start
        AND p.createdAt < :end
    """)
    double getRevenueBetween(
        @Param("start") LocalDateTime start,
        @Param("end") LocalDateTime end
    );

    @Query(value = """
        SELECT COALESCE(SUM(amount), 0)
        FROM payments
        WHERE DATE(payment_date) = CURRENT_DATE - INTERVAL '1 day'
    """, nativeQuery = true)
    double getYesterdayRevenue();
}
