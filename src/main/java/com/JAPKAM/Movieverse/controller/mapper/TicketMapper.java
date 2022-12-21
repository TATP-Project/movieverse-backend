package com.JAPKAM.Movieverse.controller.mapper;

import com.JAPKAM.Movieverse.controller.dto.TicketCreateRequest;
import com.JAPKAM.Movieverse.entity.Food;
import com.JAPKAM.Movieverse.entity.Ticket;
import com.JAPKAM.Movieverse.service.FoodService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TicketMapper {

    public Ticket toEntity(TicketCreateRequest ticketCreateRequest) {
        Ticket ticket = new Ticket();
        BeanUtils.copyProperties(ticketCreateRequest, ticket);
        return ticket;
    }

}
