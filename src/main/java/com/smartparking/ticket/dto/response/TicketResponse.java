package com.smartparking.ticket.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TicketResponse {

    private String ticketCode;
    private String status;
}