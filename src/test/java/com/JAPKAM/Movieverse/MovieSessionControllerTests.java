package com.JAPKAM.Movieverse;

import com.JAPKAM.Movieverse.entity.*;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Autowired
    CinemaRepository cinemaRepository;

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
    public static final GregorianCalendar TIMESLOT_THREE = new GregorianCalendar(2022+1900,12,17,20,30);
    public static final String HOUSE_ONE = "HOUSE ONE";
    public static final int HOUSE_ONE_ROW_NUMBER = 10;
    public static final int HOUSE_ONE_COL_NUMBER = 10;
    public static final String HOUSE_TWO = "HOUSE TWO";
    public static final int HOUSE_TWO_ROW_NUMBER = 10;
    public static final int HOUSE_TWO_COL_NUMBER = 10;
    public static final double MOVIE_1_PRICE = 80;
    public static final double MOVIE_2_PRICE = 90;

    public static final GregorianCalendar RELEASE_DATE1 = new GregorianCalendar(2022+1900,11,17);
    public static final GregorianCalendar RELEASE_DATE2 = new GregorianCalendar(2022+1900,10,17);
    public static final int RUNNING_TIME1 = 120;
    public static final int RUNNING_TIME2 = 120;
    public static final String CINEMA_1_NAME = "CINEMA_1_NAME";
    public static final String CINEMA_2_NAME = "CINEMA_2_NAME";
    @Test
    void should_return_all_movie_sessions_when_find_all_given_movie_sessions() throws Exception {
        //given
        Tag tag1 = new Tag(new ObjectId().toString(), ACTION_TAG);
        Tag tag2 = new Tag(new ObjectId().toString(), ROMANTIC_TAG);
        List<Tag> tags1 = Arrays.asList(tag1);
        List<Tag> tags2 = Arrays.asList(tag2);
        tagRepository.saveAll(Arrays.asList(tag1, tag2));

        Timeslot timeslot1 = new Timeslot(new ObjectId().toString(), TIMESLOT_ONE);
        Timeslot timeslot2 = new Timeslot(new ObjectId().toString(), TIMESLOT_TWO);
        timeslotRepository.saveAll(Arrays.asList(timeslot1, timeslot2));

        House house1 = new House(new ObjectId().toString(), HOUSE_ONE, HOUSE_ONE_ROW_NUMBER, HOUSE_ONE_COL_NUMBER);
        House house2 = new House(new ObjectId().toString(), HOUSE_TWO, HOUSE_TWO_ROW_NUMBER, HOUSE_TWO_COL_NUMBER);
        houseRepository.saveAll(Arrays.asList(house1, house2));

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
        Movie movie1 = new Movie(new ObjectId().toString(), MOVIE_1_NAME, tags1,null, RELEASE_DATE1,RUNNING_TIME1,Language.ENGLISH,Language.CHINESE);
        Movie movie2 = new Movie(new ObjectId().toString(), MOVIE_2_NAME, tags2,null, RELEASE_DATE2,RUNNING_TIME2,Language.CHINESE,Language.CHINESE);
        movieRepository.saveAll(Arrays.asList(movie1, movie2));

        String district1 = DistrictName.KOWLOON.toString();
        String district2 = DistrictName.HONG_KONG.toString();

        Cinema cinema1 = new Cinema(new ObjectId().toString(), CINEMA_1_NAME, Arrays.asList(house1),district1);
        Cinema cinema2 = new Cinema(new ObjectId().toString(), CINEMA_2_NAME, Arrays.asList(house2),district2);
        cinemaRepository.saveAll(Arrays.asList(cinema1, cinema2));

        MovieSession movieSession1 = new MovieSession(new ObjectId().toString(),timeslot1, cinema1, movie1,
                house1,MOVIE_1_PRICE,seats1);
        MovieSession movieSession2 = new MovieSession(new ObjectId().toString(), timeslot2, cinema2, movie2,
                house2, MOVIE_2_PRICE, seats2);
        movieSessionRepository.saveAll(Arrays.asList(movieSession1, movieSession2));

        
        //when
        client.perform(MockMvcRequestBuilders.get("/movie-sessions"))
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
                        "$[*].cinema.name", containsInAnyOrder(cinema1.getName(), cinema2.getName())
                ))
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "$[*].movie.name", containsInAnyOrder(movie1.getName(), movie2.getName())
                ))
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
    void should_return_404_when_perform_get_by_id_given_id_not_exist() throws Exception {
        // given
        // when
        // then
        client.perform(MockMvcRequestBuilders.get("/movie-sessions/{id}", new ObjectId().toString()))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MovieSessionNotFoundException))
                .andExpect(result -> assertEquals("Movie Session Not Found", result.getResolvedException().getMessage()));;
    }
//    @Test
//    void should_return_list_of_seats_when_perform_get_given_movie_session() throws Exception {
//        // given
//        Timeslot timeslot1 = new Timeslot(new ObjectId().toString(), TIMESLOT_ONE);
//        House house1 = new House(new ObjectId().toString(), HOUSE_ONE, 1, 1);
//
//        List<Seat> seats1 = new ArrayList<>();
//                seats1.add(new Seat(new ObjectId().toString(), 1, 1, SeatStatus.AVAILABLE));
//
//        String id = new ObjectId().toString();
//        MovieSession movieSession1 = new MovieSession(id, timeslot1,
//                house1, MOVIE_1_PRICE, seats1);
//        movieSessionRepository.save(movieSession1);
//        // when
//        client.perform(MockMvcRequestBuilders.get("/moviesessions/{id}/seats", id))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].row").value(1))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].column").value(1))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].status").value("AVAILABLE"));
//        // then
//
//    }
    @Test
    void should_update_seats_when_perform_put_given_movie_session() throws Exception {
        // given
        List<Tag> tags1 = Arrays.asList(new Tag(new ObjectId().toString(), ACTION_TAG));
        Timeslot timeslot1 = new Timeslot(new ObjectId().toString(), TIMESLOT_ONE);
        House house1 = new House(new ObjectId().toString(), HOUSE_ONE, 1, 1);

        List<Seat> seats1 = new ArrayList<>();
        String seatId = new ObjectId().toString();
        seats1.add(new Seat(seatId, 1, 1, SeatStatus.AVAILABLE));

        String id = new ObjectId().toString();
        String district1 = DistrictName.KOWLOON.toString();
        Cinema cinema1 = new Cinema(new ObjectId().toString(), CINEMA_1_NAME, Arrays.asList(house1),district1);
        Movie movie1 = new Movie(new ObjectId().toString(), MOVIE_1_NAME, tags1,null, RELEASE_DATE1,RUNNING_TIME1,Language.ENGLISH,Language.CHINESE);
        MovieSession movieSession1 = new MovieSession(id, timeslot1,cinema1,movie1,
                house1, MOVIE_1_PRICE, seats1);
        movieSessionRepository.save(movieSession1);
        Seat newSeat1 = new Seat(seatId, 2, 2, SeatStatus.RESERVED);

        String newSeatsJson = new ObjectMapper().writeValueAsString(Arrays.asList(newSeat1));
        // when & then
        client.perform(MockMvcRequestBuilders.put("/movie-sessions/{id}/seats", id).contentType(MediaType.APPLICATION_JSON).content(newSeatsJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].row").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].column").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].status").value("RESERVED"));

    }

    @Test
    void should_return_movie_sessions_of_movie_when_find_movie_session_by_movie_id_given_movie_id() throws Exception {
        //given
        Tag tag1 = new Tag(new ObjectId().toString(), ACTION_TAG);
        List<Tag> tags1 = Arrays.asList(tag1);
        tagRepository.save(tag1);

        Timeslot timeslot1 = new Timeslot(new ObjectId().toString(), TIMESLOT_ONE);
        Timeslot timeslot2 = new Timeslot(new ObjectId().toString(), TIMESLOT_TWO);
        Timeslot timeslot3 = new Timeslot(new ObjectId().toString(), TIMESLOT_THREE);
        timeslotRepository.saveAll(Arrays.asList(timeslot1, timeslot2));

        House house1 = new House(new ObjectId().toString(), HOUSE_ONE, HOUSE_ONE_ROW_NUMBER, HOUSE_ONE_COL_NUMBER);
        House house2 = new House(new ObjectId().toString(), HOUSE_TWO, HOUSE_TWO_ROW_NUMBER, HOUSE_TWO_COL_NUMBER);
        houseRepository.saveAll(Arrays.asList(house1, house2));

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
        Movie movie1 = new Movie(new ObjectId().toString(), MOVIE_1_NAME, tags1,null, RELEASE_DATE1,RUNNING_TIME1,Language.ENGLISH,Language.CHINESE);
        Movie movie2 = new Movie(new ObjectId().toString(), MOVIE_2_NAME, tags1,null, RELEASE_DATE1,RUNNING_TIME1,Language.ENGLISH,Language.CHINESE);
        movieRepository.saveAll(Arrays.asList(movie1));

        String district1 = DistrictName.KOWLOON.toString();
        String district2 = DistrictName.HONG_KONG.toString();

        Cinema cinema1 = new Cinema(new ObjectId().toString(), CINEMA_1_NAME, Arrays.asList(house1),district1);
        Cinema cinema2 = new Cinema(new ObjectId().toString(), CINEMA_2_NAME, Arrays.asList(house2),district2);
        cinemaRepository.saveAll(Arrays.asList(cinema1, cinema2));

        MovieSession movieSession1 = new MovieSession(new ObjectId().toString(),timeslot1, cinema1, movie1,
                house1,MOVIE_1_PRICE,seats1);
        MovieSession movieSession2 = new MovieSession(new ObjectId().toString(), timeslot2, cinema2, movie1,
                house2, MOVIE_1_PRICE, seats2);
        MovieSession movieSession3 = new MovieSession(new ObjectId().toString(), timeslot3, cinema2, movie2,
                house2, MOVIE_2_PRICE, seats2);
        movieSessionRepository.saveAll(Arrays.asList(movieSession1, movieSession2, movieSession3));
        //when
        //then
        client.perform(MockMvcRequestBuilders.get("/movie-sessions?movieId={movieId}", movie1.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].cinema.name", containsInAnyOrder(
                        cinema1.getName(), cinema2.getName()
                )))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].movie.name", containsInAnyOrder(
                        movie1.getName(), movie1.getName()
                )))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].house.name", containsInAnyOrder(
                        house1.getName(), house2.getName()
                )))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].price", containsInAnyOrder(
                        MOVIE_1_PRICE, MOVIE_1_PRICE
                )));
    }
    
}
