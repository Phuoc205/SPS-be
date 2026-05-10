package com.smartparking.iot.repository;
import com.smartparking.iot.entity.Gate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface GateRepository extends JpaRepository<Gate, Long> {}
