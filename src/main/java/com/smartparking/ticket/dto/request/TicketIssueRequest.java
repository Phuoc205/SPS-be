package com.smartparking.ticket.dto.request;

import lombok.Data;

@Data
public class TicketIssueRequest {
    private String plate;

    private String type;
}