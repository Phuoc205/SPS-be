package com.smartparking.iot.repository;
import com.smartparking.iot.entity.GateControlLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GateControlLogRepository
        extends JpaRepository<GateControlLog, Long> {
}
