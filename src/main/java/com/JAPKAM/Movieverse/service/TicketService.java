package com.JAPKAM.Movieverse.service;

import com.JAPKAM.Movieverse.entity.Seat;
import com.JAPKAM.Movieverse.entity.Ticket;
import com.JAPKAM.Movieverse.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    private TicketRepository ticketRepository;
    public String postTicket(Ticket ticket) {
        return ticketRepository.save(ticket).getId();
    }
}
