package com.JAPKAM.Movieverse;

import com.JAPKAM.Movieverse.entity.*;
import com.JAPKAM.Movieverse.repository.*;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
    public static final String MOVIE_1_NAME = "Movie 1";
    public static final Date TIMESLOT_ONE = new Date(2022,12,16,16,30);
    public static final String HOUSE_ONE = "HOUSE ONE";
    public static final int HOUSE_ONE_ROW_NUMBER = 20;
    public static final int HOUSE_ONE_COL_NUMBER = 20;
    public static final double MOVIE_1_PRICE = 80;

    @Test
    void should_return_movie_session_when_find_by_id_given_id() throws Exception {
        //given
        Tag tag1 = new Tag(new ObjectId().toString(), ACTION_TAG);
        List<Tag> tags1 = Arrays.asList(tag1);

        Movie movie1 = new Movie(new ObjectId().toString(), MOVIE_1_NAME, tags1,null);

        Timeslot timeslot1 = new Timeslot(new ObjectId().toString(), TIMESLOT_ONE);

        House house1 = new House(new ObjectId().toString(), HOUSE_ONE, HOUSE_ONE_ROW_NUMBER, HOUSE_ONE_COL_NUMBER);

        List<Seat> seats1 = new ArrayList<>();
        for(int i = 0 ; i < house1.getNumberOfRow(); i++){
            for(int j =0 ;j <house1.getNumberOfColumn(); j++) {
                seats1.add(new Seat(new ObjectId().toString(), i+1, j+1, SeatStatus.AVAILABLE));
            }
        }

        MovieSession movieSession1 = new MovieSession(new ObjectId().toString(), movie1, timeslot1,
                house1, MOVIE_1_PRICE, seats1);

        tagRepository.save(tag1);
        movieRepository.save(movie1);
        timeslotRepository.save(timeslot1);
        houseRepository.save(house1);
        movieSessionRepository.save(movieSession1);

        //when
        client.perform(MockMvcRequestBuilders.get("/moviesessions/{id}", movieSession1.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.movie.name").value(movie1.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.movie.tags[0].name").value(tag1.getName()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.timeslot.startDateTime").value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.house.name").value(house1.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.house.numberOfRow").value(house1.getNumberOfRow()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.house.numberOfColumn").value(house1.getNumberOfColumn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(movieSession1.getPrice()));
        //then
    }
}
