package com.JAPKAM.Movieverse;

import com.JAPKAM.Movieverse.entity.Seat;
import com.JAPKAM.Movieverse.entity.SeatStatus;
import com.JAPKAM.Movieverse.entity.Ticket;
import com.JAPKAM.Movieverse.repository.TicketRepository;
import com.JAPKAM.Movieverse.service.TicketService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class TicketServiceTest {
    @Mock
    TicketRepository ticketRepository;
    @InjectMocks
    TicketService ticketService;

    @Test
    void should_return_ticket_id_when_post_ticket_given_ticket(){
        //given
        String ticketId=new ObjectId().toString();
        String movieSessionId=new ObjectId().toString();
        List<Seat> seats = new ArrayList<>();
        seats.add(new Seat(new ObjectId().toString(), 1, 1, SeatStatus.AVAILABLE));
        Ticket requestTicketWithMovieSessionAndSeats= new Ticket(null,movieSessionId,seats,null);

        given(ticketRepository.save(requestTicketWithMovieSessionAndSeats)).willReturn(requestTicketWithMovieSessionAndSeats);
        //when
        String returnTicketId = ticketService.postTicket(requestTicketWithMovieSessionAndSeats);
        //then
        assertThat(returnTicketId, equalTo(ticketId));
    }
}
