package com.JAPKAM.Movieverse.controller;

import com.JAPKAM.Movieverse.entity.Ticket;
import com.JAPKAM.Movieverse.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public String postTicket(@RequestBody Ticket ticket){
        return ticketService.postTicket(ticket);
    }
}
