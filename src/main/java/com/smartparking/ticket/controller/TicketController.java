package com.smartparking.ticket.controller;

import com.smartparking.ticket.dto.request.*;
import com.smartparking.ticket.service.TicketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService service;

    @PostMapping("/issue")
    public ResponseEntity<?> issue(@RequestParam String plate) {
        return ResponseEntity.ok(service.issue(plate));
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validate(@RequestBody TicketRequest request) {
        return ResponseEntity.ok(service.validate(request));
    }
}