package com.JAPKAM.Movieverse.service;

import com.JAPKAM.Movieverse.entity.Ticket;
import com.JAPKAM.Movieverse.repository.TicketRepository;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    private TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public String postTicket(Ticket ticket) {
        return ticketRepository.save(ticket).getId();
    }
}
