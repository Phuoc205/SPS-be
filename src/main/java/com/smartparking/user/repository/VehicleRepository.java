package com.smartparking.user.repository;
import com.smartparking.user.entity.Vehicle;
import org.springframework.data.jpa.repository.*;

import java.util.*;
public interface VehicleRepository
        extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findByLicensePlate(
            String licensePlate
    );
}
