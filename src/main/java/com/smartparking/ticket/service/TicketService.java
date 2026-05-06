package com.smartparking.ticket.service;

import com.smartparking.ticket.dto.request.*;
import com.smartparking.ticket.dto.response.*;

public interface TicketService {

    TicketResponse validate(TicketRequest request);

    TicketResponse issue(String plate);
}