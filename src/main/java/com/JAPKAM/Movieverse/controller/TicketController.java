package com.JAPKAM.Movieverse.controller;

import com.JAPKAM.Movieverse.entity.Ticket;
import com.JAPKAM.Movieverse.service.TicketService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public String postTicket(@RequestBody Ticket ticket){
        return ticketService.postTicket(ticket);
    }
}
