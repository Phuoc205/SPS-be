package com.smartparking.parking.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SlotResponse {

    private Long id;

    private String slotName;

    private boolean occupied;

    private Long lotId;

    private String lotName;

    private String lotLocation;
}