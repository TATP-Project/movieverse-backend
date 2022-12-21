package com.JAPKAM.Movieverse.controller;

import com.JAPKAM.Movieverse.controller.dto.TicketCreateRequest;
import com.JAPKAM.Movieverse.controller.mapper.TicketMapper;
import com.JAPKAM.Movieverse.entity.Food;
import com.JAPKAM.Movieverse.entity.Ticket;
import com.JAPKAM.Movieverse.service.FoodService;
import com.JAPKAM.Movieverse.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private TicketService ticketService;
    private FoodService foodService;
    private TicketMapper ticketMapper;

    public TicketController(TicketService ticketService, FoodService foodService, TicketMapper ticketMapper) {
        this.ticketService = ticketService;
        this.foodService = foodService;
        this.ticketMapper = ticketMapper;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public String postTicket(@RequestBody TicketCreateRequest ticketCreateRequest){
        Ticket ticket = ticketMapper.toEntity(ticketCreateRequest);
        List<Food> foods = ticketCreateRequest.getFood().stream()
                .map((foodId) -> foodService.findById(foodId))
                .collect(Collectors.toList());
        ticket.setFoods(foods);
        return ticketService.postTicket(ticket);
    }
}
