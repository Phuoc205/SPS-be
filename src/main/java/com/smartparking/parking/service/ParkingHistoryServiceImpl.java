package com.smartparking.parking.service;

import com.smartparking.parking.dto.response.ParkingHistoryResponse;
import com.smartparking.parking.entity.ParkingHistory;
import com.smartparking.parking.entity.ParkingSession;
import com.smartparking.parking.repository.ParkingHistoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.time.Duration;

@Service
public class ParkingHistoryServiceImpl implements ParkingHistoryService {

    @Autowired
    private ParkingHistoryRepository repo;

    @Override
    public void save(ParkingSession session) {

        ParkingHistory h = new ParkingHistory();

        h.setSessionId(session.getId());

        h.setUserId(session.getUserId());

        h.setSlotName(session.getSlot().getSlotName());

        h.setCheckInTime(session.getCheckInTime());

        h.setCheckOutTime(session.getCheckOutTime());

        long hours = Duration.between(
                session.getCheckInTime(),
                session.getCheckOutTime()
        ).toHours();

        if (hours <= 0) {
            hours = 1;
        }

        double amount =
                hours * session.getPrice().getPricePerHour();

        h.setAmount(amount);

        repo.save(h);
    }


    @Override
    public List<ParkingHistoryResponse> getByUser(Long userId) {

        return repo.findByUserId(userId)
                .stream()
                .map(h -> {

                    long hours = Duration.between(
                            h.getCheckInTime(),
                            h.getCheckOutTime()
                    ).toHours();

                    if (hours <= 0) {
                        hours = 1;
                    }

                    return new ParkingHistoryResponse(
                            h.getSessionId(),
                            h.getSlotName(),
                            h.getCheckOutTime() == null
                                    ? "ACTIVE"
                                    : "FINISHED",
                            h.getCheckInTime().toString(),
                            h.getCheckOutTime() != null
                                    ? h.getCheckOutTime().toString()
                                    : null,
                            hours,
                            h.getAmount() != null
                                    ? h.getAmount()
                                    : 0
                    );
                })
                .collect(Collectors.toList());
    }
}
