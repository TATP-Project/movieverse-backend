package com.JAPKAM.Movieverse;

import com.JAPKAM.Movieverse.controller.dto.TicketCreateRequest;
import com.JAPKAM.Movieverse.entity.*;
import com.JAPKAM.Movieverse.repository.*;
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
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class TicketControllerTest {

    public static final String FOOD_NAME = "FOOD NAME";
    public static final double FOOD_PRICE = 10;
    public static final String FOOD_TYPE = "FOOD TYPE";
    @Autowired
    MockMvc client;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    FoodRepository foodRepository;

    @Autowired
    MovieSessionRepository movieSessionRepository;

    @Autowired
    CinemaRepository cinemaRepository;

    @Autowired
    HouseRepository houseRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    TimeslotRepository timeslotRepository;

    @Autowired
    MovieRepository movieRepository;


    public static final String ACTION_TAG = "action";
    public static final String ROMANTIC_TAG = "romantic";
    public static final String MOVIE_1_NAME = "Movie 1";
    public static final String MOVIE_2_NAME = "Movie 2";
    public static final GregorianCalendar TIMESLOT_ONE = new GregorianCalendar(2022+1900, 12, 17, 14, 30);
    public static final String HOUSE_ONE = "HOUSE ONE";
    public static final int HOUSE_ONE_ROW_NUMBER = 7;
    public static final int HOUSE_ONE_COL_NUMBER = 12;
    public static final double MOVIE_1_PRICE = 80;

    public static final GregorianCalendar RELEASE_DATE1 = new GregorianCalendar(2022+1900,11,17);
    public static final int RUNNING_TIME1 = 120;
    public static final String CINEMA_1_NAME = "CINEMA_1_NAME";

    @BeforeEach
    public void clearDB() {
        ticketRepository.deleteAll();
    }
    @Test
    void should_return_ticket_id_when_perform_post_given_ticket() throws Exception {
        //given
        Tag tag1 = new Tag(new ObjectId().toString(), ACTION_TAG);
        Tag tag2 = new Tag(new ObjectId().toString(), ROMANTIC_TAG);
        List<Tag> tags1 = Arrays.asList(tag1);
        tagRepository.saveAll(Arrays.asList(tag1, tag2));

        Timeslot timeslot1 = new Timeslot(new ObjectId().toString(), TIMESLOT_ONE);
        timeslotRepository.saveAll(Arrays.asList(timeslot1));

        House house1 = new House(new ObjectId().toString(), HOUSE_ONE, HOUSE_ONE_ROW_NUMBER, HOUSE_ONE_COL_NUMBER);
        houseRepository.saveAll(Arrays.asList(house1));

        List<Seat> seats1 = new ArrayList<>();
        for(int i = 0 ; i < house1.getNumberOfRow(); i++){
            for(int j =0 ;j <house1.getNumberOfColumn(); j++) {
                seats1.add(new Seat(new ObjectId().toString(), i+1, j+1, SeatStatus.AVAILABLE));
            }
        }

        Movie movie1 = new Movie(new ObjectId().toString(), MOVIE_1_NAME, tags1,null, RELEASE_DATE1,RUNNING_TIME1,Language.ENGLISH,Language.CHINESE);
        movieRepository.saveAll(Arrays.asList(movie1));

        String district1 = DistrictName.KOWLOON.toString();

        Cinema cinema1 = new Cinema(new ObjectId().toString(), CINEMA_1_NAME, Arrays.asList(house1),district1);
        cinemaRepository.saveAll(Arrays.asList(cinema1));

        MovieSession movieSession1 = new MovieSession(new ObjectId().toString(),timeslot1, cinema1, movie1,
                house1,MOVIE_1_PRICE,seats1);
        movieSessionRepository.saveAll(Arrays.asList(movieSession1));

        Food food = new Food(new ObjectId().toString(), FOOD_NAME, FOOD_PRICE, null, FOOD_TYPE);
        foodRepository.save(food);


        Seat selectedSeat = new Seat(new ObjectId().toString(), 1, 1, SeatStatus.SOLD);
        List<Seat> selectedSeats = Arrays.asList(selectedSeat);
        TicketCreateRequest ticketCreateRequest = new TicketCreateRequest();
        ticketCreateRequest.setId(new ObjectId().toString());
        ticketCreateRequest.setMovieSessionId(movieSession1.getId());
        ticketCreateRequest.setSeats(selectedSeats);
        ticketCreateRequest.setFood(Arrays.asList(food.getId()));

        String  ticketCreateRequestString = new ObjectMapper()
                .writeValueAsString(ticketCreateRequest);

        //when & then
        client.perform(MockMvcRequestBuilders.post("/tickets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ticketCreateRequestString))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isString());

        List<Ticket> tickets = ticketRepository.findAll();
        assertThat(tickets, hasSize(1));
        assertThat(tickets.get(0).getMovieSessionId(), equalTo(ticketCreateRequest.getMovieSessionId()));
        assertThat(tickets.get(0).getSeats().get(0).getId(), equalTo(ticketCreateRequest.getSeats().get(0).getId()));
        assertThat(tickets.get(0).getSeats().get(0).getStatus(), equalTo(SeatStatus.SOLD));
        assertThat(tickets.get(0).getFoods().get(0).getId(), equalTo(ticketCreateRequest.getFood().get(0)));
    }
}
