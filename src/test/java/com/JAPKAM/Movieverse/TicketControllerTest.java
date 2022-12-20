package com.JAPKAM.Movieverse;

import com.JAPKAM.Movieverse.entity.Seat;
import com.JAPKAM.Movieverse.entity.SeatStatus;
import com.JAPKAM.Movieverse.entity.Ticket;
import com.JAPKAM.Movieverse.repository.MovieRepository;
import com.JAPKAM.Movieverse.repository.TicketRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class TicketControllerTest {

    @Autowired
    MockMvc client;

    @Autowired
    TicketRepository ticketRepository;

    @BeforeEach
    public void clearDB() {
        ticketRepository.deleteAll();
    }
    @Test
    void should_return_ticket_id_when_perform_post_given_ticket() throws Exception {
        //given
        String ticketId=new ObjectId().toString();
        String movieSessionId=new ObjectId().toString();
        List<Seat> seats = new ArrayList<>();
        seats.add(new Seat(new ObjectId().toString(), 1, 1, SeatStatus.SOLD));
        Ticket requestTicket= new Ticket(ticketId,movieSessionId,seats,null);
        String newTicketJson = new ObjectMapper()
                .writeValueAsString(requestTicket);

        //when & then
        client.perform(MockMvcRequestBuilders.post("/tickets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newTicketJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string(ticketId));

        List<Ticket> tickets = ticketRepository.findAll();
        assertThat(tickets, hasSize(1));
        assertThat(tickets.get(0).getId(), equalTo(ticketId));
//        assertThat(tickets.get(0).getSeats(), equalTo(requestTicket.getSeats()));
        assertThat(tickets.get(0).getMovieSessionId(), equalTo(movieSessionId));
        assertThat(tickets.get(0).getFoods(), equalTo(null));
    }
}
