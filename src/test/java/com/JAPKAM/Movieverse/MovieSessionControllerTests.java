package com.JAPKAM.Movieverse;

import com.JAPKAM.Movieverse.entity.*;
import com.JAPKAM.Movieverse.exception.MovieNotFoundException;
import com.JAPKAM.Movieverse.exception.MovieSessionNotFoundException;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieSessionControllerTests {
    @Autowired
    MockMvc client;

    @Autowired
    MovieSessionRepository movieSessionRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    HouseRepository houseRepository;

    @Autowired
    TimeslotRepository timeslotRepository;

    @BeforeEach
    public void clearDB(){
        movieSessionRepository.deleteAll();
    }
    public static final String ACTION_TAG = "action";
    public static final String ROMANTIC_TAG = "romantic";
    public static final String MOVIE_1_NAME = "Movie 1";
    public static final String MOVIE_2_NAME = "Movie 2";
    public static final GregorianCalendar TIMESLOT_ONE = new GregorianCalendar(2022+1900, 12, 17, 14, 30);
    public static final GregorianCalendar TIMESLOT_TWO = new GregorianCalendar(2022+1900,12,17,17,30);
    public static final Date TIMESLOT_THREE = new Date(2022,12,18,18,30);
    public static final String HOUSE_ONE = "HOUSE ONE";
    public static final int HOUSE_ONE_ROW_NUMBER = 20;
    public static final int HOUSE_ONE_COL_NUMBER = 20;
    public static final String HOUSE_TWO = "HOUSE TWO";
    public static final int HOUSE_TWO_ROW_NUMBER = 5;
    public static final int HOUSE_TWO_COL_NUMBER = 10;
    public static final double MOVIE_1_PRICE = 80;
    public static final double MOVIE_2_PRICE = 90;

    @Test
    void should_return_all_movie_sessions_when_find_all_given_movie_sessions() throws Exception {
        //given
        Timeslot timeslot1 = new Timeslot(new ObjectId().toString(), TIMESLOT_ONE);
        Timeslot timeslot2 = new Timeslot(new ObjectId().toString(), TIMESLOT_TWO);

        House house1 = new House(new ObjectId().toString(), HOUSE_ONE, HOUSE_ONE_ROW_NUMBER, HOUSE_ONE_COL_NUMBER);
        House house2 = new House(new ObjectId().toString(), HOUSE_TWO, HOUSE_TWO_ROW_NUMBER, HOUSE_TWO_COL_NUMBER);

        List<Seat> seats1 = new ArrayList<>();
        for(int i = 0 ; i < house1.getNumberOfRow(); i++){
            for(int j =0 ;j <house1.getNumberOfColumn(); j++) {
                seats1.add(new Seat(new ObjectId().toString(), i+1, j+1, SeatStatus.AVAILABLE));
            }
        }

        List<Seat> seats2 = new ArrayList<>();
        for(int i = 0 ; i < house2.getNumberOfRow(); i++){
            for(int j =0 ;j <house2.getNumberOfColumn(); j++) {
                seats2.add(new Seat(new ObjectId().toString(), i+1, j+1, SeatStatus.AVAILABLE));
            }
        }

        MovieSession movieSession1 = new MovieSession(new ObjectId().toString(), timeslot1,
                house1, MOVIE_1_PRICE, seats1);
        MovieSession movieSession2 = new MovieSession(new ObjectId().toString(), timeslot2,
                house2, MOVIE_2_PRICE, seats2);

        timeslotRepository.saveAll(Arrays.asList(timeslot1, timeslot2));
        houseRepository.saveAll(Arrays.asList(house1, house2));
        movieSessionRepository.saveAll(Arrays.asList(movieSession1,movieSession2));
        
        //when
        client.perform(MockMvcRequestBuilders.get("/moviesessions"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
//                .andExpect(MockMvcResultMatchers.jsonPath(
//                        "$[*].timeslot.startDateTime", containsInAnyOrder(
//                                timeslot1.getStartDateTime().toString(), timeslot2.getStartDateTime().toString()
//                        )
//                ))
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "$[*].house.name", containsInAnyOrder(house1.getName(), house2.getName()
                )))
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "$[*].house.numberOfRow", containsInAnyOrder(house1.getNumberOfRow(), house2.getNumberOfRow()
                )))
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "$[*].house.numberOfColumn", containsInAnyOrder(house1.getNumberOfColumn(), house2.getNumberOfColumn()
                        )))
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "$[*].price", containsInAnyOrder(MOVIE_1_PRICE, MOVIE_2_PRICE)
                ))
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "$[0].seats", hasSize(house1.getNumberOfRow()*house1.getNumberOfColumn())))
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "$[1].seats", hasSize(house2.getNumberOfRow()*house2.getNumberOfColumn())));
        
        //then
    }
    
    @Test
    void should_return_movie_session_when_find_by_id_given_id() throws Exception {
        //given

        Timeslot timeslot1 = new Timeslot(new ObjectId().toString(), TIMESLOT_ONE);

        House house1 = new House(new ObjectId().toString(), HOUSE_ONE, HOUSE_ONE_ROW_NUMBER, HOUSE_ONE_COL_NUMBER);

        List<Seat> seats1 = new ArrayList<>();
        for(int i = 0 ; i < house1.getNumberOfRow(); i++){
            for(int j =0 ;j <house1.getNumberOfColumn(); j++) {
                seats1.add(new Seat(new ObjectId().toString(), i+1, j+1, SeatStatus.AVAILABLE));
            }
        }

        MovieSession movieSession1 = new MovieSession(new ObjectId().toString(), timeslot1,
                house1, MOVIE_1_PRICE, seats1);

        timeslotRepository.save(timeslot1);
        houseRepository.save(house1);
        movieSessionRepository.save(movieSession1);

        //when
        client.perform(MockMvcRequestBuilders.get("/moviesessions/{id}", movieSession1.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.timeslot.startDateTime").value(timeslot1.getStartDateTime()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.house.name").value(house1.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.house.numberOfRow").value(house1.getNumberOfRow()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.house.numberOfColumn").value(house1.getNumberOfColumn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(movieSession1.getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.seats", hasSize(house1.getNumberOfRow()*house1.getNumberOfColumn())));
        //then
    }

    @Test
    void should_return_404_when_perform_get_by_id_given_id_not_exist() throws Exception {
        // given
        // when
        // then
        client.perform(MockMvcRequestBuilders.get("/moviesessions/{id}", new ObjectId().toString()))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MovieSessionNotFoundException))
                .andExpect(result -> assertEquals("Movie Session Not Found", result.getResolvedException().getMessage()));;
    }
    @Test
    void should_return_list_of_seats_when_perform_get_given_movie_session() throws Exception {
        // given
        Timeslot timeslot1 = new Timeslot(new ObjectId().toString(), TIMESLOT_ONE);
        House house1 = new House(new ObjectId().toString(), HOUSE_ONE, 1, 1);

        List<Seat> seats1 = new ArrayList<>();
                seats1.add(new Seat(new ObjectId().toString(), 1, 1, SeatStatus.AVAILABLE));

        String id = new ObjectId().toString();
        MovieSession movieSession1 = new MovieSession(id, timeslot1,
                house1, MOVIE_1_PRICE, seats1);
        movieSessionRepository.save(movieSession1);
        // when
        client.perform(MockMvcRequestBuilders.get("/moviesessions/{id}/seats", id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].row").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].column").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].status").value("AVAILABLE"));
        // then


    }
    @Test
    void should_update_seat_when_perform_put_given_movie_session() throws Exception {
        // given
        Timeslot timeslot1 = new Timeslot(new ObjectId().toString(), TIMESLOT_ONE);
        House house1 = new House(new ObjectId().toString(), HOUSE_ONE, 1, 1);

        List<Seat> seats1 = new ArrayList<>();
        String seatId = new ObjectId().toString();
        seats1.add(new Seat(seatId, 1, 1, SeatStatus.AVAILABLE));

        String id = new ObjectId().toString();
        MovieSession movieSession1 = new MovieSession(id, timeslot1,
                house1, MOVIE_1_PRICE, seats1);
        movieSessionRepository.save(movieSession1);
        Seat newSeat = new Seat(seatId, 2, 2, SeatStatus.RESERVED);
        String newSeatJson = new ObjectMapper().writeValueAsString(newSeat);
        // when & then
        client.perform(MockMvcRequestBuilders.put("/moviesessions/{id}/seats", id).contentType(MediaType.APPLICATION_JSON).content(newSeatJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.row").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.column").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("RESERVED"));

    }
    
}
