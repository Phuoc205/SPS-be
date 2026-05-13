package com.smartparking.payment.repository;

import com.smartparking.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByStatus(String status);

    @Query("""
        SELECT COALESCE(SUM(p.amount), 0)
        FROM Payment p
        WHERE p.createdAt >= :start
        AND p.createdAt < :end
        AND p.status = 'PAID'
    """)
    double getRevenueBetween(
        @Param("start") LocalDateTime start,
        @Param("end") LocalDateTime end
    );

    @Query(value = """
        SELECT COALESCE(SUM(amount), 0)
        FROM payment
        WHERE created_at >= CURRENT_DATE - INTERVAL '1 day'
        AND created_at < CURRENT_DATE
        AND status = 'PAID'
    """, nativeQuery = true)
    double getYesterdayRevenue();

    List<Payment> findByInvoice_UserId(Long userId);
}
