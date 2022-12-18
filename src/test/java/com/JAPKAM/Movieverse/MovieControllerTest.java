package com.JAPKAM.Movieverse;

import com.JAPKAM.Movieverse.entity.*;
import com.JAPKAM.Movieverse.exception.MovieNotFoundException;
import com.JAPKAM.Movieverse.repository.MovieRepository;
import org.bson.types.Binary;
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
import java.util.GregorianCalendar;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {

    @Autowired
    MockMvc client;

    @Autowired
    MovieRepository movieRepository;

    @BeforeEach
    public void clearDB() {
        movieRepository.deleteAll();
    }
    public static final String ACTION_TAG = "action";
    public static final String ROMANTIC_TAG = "romantic";
    public static final String MOVIE_1_NAME = "Movie 1";
    public static final String MOVIE_2_NAME = "Movie 2";
    public static final String HOUSE_ONE = "HOUSE ONE";
    public static final int HOUSE_ONE_ROW_NUMBER = 20;
    public static final int HOUSE_ONE_COL_NUMBER = 20;
    public static final String HOUSE_TWO = "HOUSE TWO";
    public static final int HOUSE_TWO_ROW_NUMBER = 5;
    public static final int HOUSE_TWO_COL_NUMBER = 10;
    public static final String HOUSE_THREE = "HOUSE_THREE";
    public static final int HOUSE_THREE_ROW_NUMBER = 4;
    public static final int HOUSE_THREE_COL_NUMBER = 10;
    public static final GregorianCalendar TIMESLOT_ONE = new GregorianCalendar(2022+1900, 12, 17, 14, 30);
    public static final GregorianCalendar TIMESLOT_TWO = new GregorianCalendar(2022+1900,12,17,17,30);
    public static final GregorianCalendar TIMESLOT_THREE = new GregorianCalendar(2022+1900,12,17,19,30);
    public static final double MOVIE_1_PRICE = 80;
    public static final double MOVIE_2_PRICE = 90;
    public static final GregorianCalendar RELEASE_DATE1 = new GregorianCalendar(2022+1900,11,17);
    public static final GregorianCalendar RELEASE_DATE2 = new GregorianCalendar(2022+1900,10,17);
    public static final int RUNNING_TIME1 = 120;
    public static final int RUNNING_TIME2 = 100;
    @Test
    void should_get_all_movies_when_perform_get_given_2_movies() throws Exception {
        //given
        List<Tag> tags1 = Arrays.asList(new Tag(new ObjectId().toString(), ACTION_TAG));
        List<Tag> tags2 = Arrays.asList(new Tag(new ObjectId().toString(), ROMANTIC_TAG));

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
        MovieSession movieSession1 = new MovieSession(new ObjectId().toString(),timeslot1,house1,MOVIE_1_PRICE,seats1);
        MovieSession movieSession2 = new MovieSession(new ObjectId().toString(), timeslot2,
                house2, MOVIE_2_PRICE, seats2);
        Binary image1 = new Binary(new byte[1]);
        Binary image2 = new Binary(new byte[1]);
        Movie movie1 = new Movie(new ObjectId().toString(), MOVIE_1_NAME, tags1,image1, Arrays.asList(movieSession1), RELEASE_DATE1,RUNNING_TIME1, Language.ENGLISH,Language.CHINESE);
        Movie movie2 = new Movie(new ObjectId().toString(), MOVIE_2_NAME, tags2,image2,Arrays.asList(movieSession2), RELEASE_DATE2,RUNNING_TIME2,Language.CHINESE,Language.CHINESE);
        movieRepository.save(movie1);
        movieRepository.save(movie2);
        //when & then

        client.perform(MockMvcRequestBuilders.get("/movies"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(2)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(MOVIE_1_NAME))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value(MOVIE_2_NAME));
    }

    @Test
    void should_get_movie_when_perform_get_by_id_given_a_id() throws Exception {
        //given
        List<Tag> tags1 = Arrays.asList(new Tag(new ObjectId().toString(), ACTION_TAG));


        Timeslot timeslot1 = new Timeslot(new ObjectId().toString(), TIMESLOT_ONE);

        House house1 = new House(new ObjectId().toString(), HOUSE_ONE, HOUSE_ONE_ROW_NUMBER, HOUSE_ONE_COL_NUMBER);

        List<Seat> seats1 = new ArrayList<>();
        for(int i = 0 ; i < house1.getNumberOfRow(); i++){
            for(int j =0 ;j <house1.getNumberOfColumn(); j++) {
                seats1.add(new Seat(new ObjectId().toString(), i+1, j+1, SeatStatus.AVAILABLE));
            }
        }

        MovieSession movieSession1 = new MovieSession(new ObjectId().toString(),timeslot1,house1,MOVIE_1_PRICE,seats1);
        Binary image1 = new Binary(new byte[1]);
        String id = new ObjectId().toString();
        Movie movie1 = new Movie(id, MOVIE_1_NAME, tags1,image1,Arrays.asList(movieSession1), RELEASE_DATE1,
                RUNNING_TIME1,Language.ENGLISH,Language.CHINESE);
        movieRepository.save(movie1);
        //when & then
        client.perform(MockMvcRequestBuilders.get("/movies/{id}",movie1.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(MOVIE_1_NAME));
    }

    @Test
    void should_return_movie_not_found_exception_when_get_by_id_given_id_not_exist() throws Exception {
        //given
        //when
        //then
        client.perform(MockMvcRequestBuilders.get("/movies/{id}", new ObjectId().toString()))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MovieNotFoundException))
                .andExpect(result -> assertEquals("Movie Not Found", result.getResolvedException().getMessage()));
    }

//    @Test
//    void should_return_movie_session_2_and_3_when_find_all_movie_session_given_movie() throws Exception{
//        //given
//        List<Tag> tags1 = Arrays.asList(new Tag(new ObjectId().toString(), ACTION_TAG));
//        List<Tag> tags2 = Arrays.asList(new Tag(new ObjectId().toString(), ROMANTIC_TAG));
//
//        Timeslot timeslot1 = new Timeslot(new ObjectId().toString(), TIMESLOT_ONE);
//        Timeslot timeslot2 = new Timeslot(new ObjectId().toString(), TIMESLOT_TWO);
//        Timeslot timeslot3 = new Timeslot(new ObjectId().toString(), TIMESLOT_THREE);
//
//        House house1 = new House(new ObjectId().toString(), HOUSE_ONE, HOUSE_ONE_ROW_NUMBER, HOUSE_ONE_COL_NUMBER);
//        House house2 = new House(new ObjectId().toString(), HOUSE_TWO, HOUSE_TWO_ROW_NUMBER, HOUSE_TWO_COL_NUMBER);
//        House house3 = new House(new ObjectId().toString(), HOUSE_THREE, HOUSE_THREE_ROW_NUMBER, HOUSE_THREE_COL_NUMBER);
//        List<Seat> seats1 = new ArrayList<>();
//        for(int i = 0 ; i < house1.getNumberOfRow(); i++){
//            for(int j =0 ;j <house1.getNumberOfColumn(); j++) {
//                seats1.add(new Seat(new ObjectId().toString(), i+1, j+1, SeatStatus.AVAILABLE));
//            }
//        }
//
//        List<Seat> seats2 = new ArrayList<>();
//        for(int i = 0 ; i < house2.getNumberOfRow(); i++){
//            for(int j =0 ;j <house2.getNumberOfColumn(); j++) {
//                seats2.add(new Seat(new ObjectId().toString(), i+1, j+1, SeatStatus.AVAILABLE));
//            }
//        }
//        List<Seat> seats3 = new ArrayList<>();
//        for(int i = 0 ; i < house3.getNumberOfRow(); i++){
//            for(int j =0 ;j <house3.getNumberOfColumn(); j++) {
//                seats2.add(new Seat(new ObjectId().toString(), i+1, j+1, SeatStatus.AVAILABLE));
//            }
//        }
//        MovieSession movieSession1 = new MovieSession(new ObjectId().toString(),timeslot1,house1,MOVIE_1_PRICE,seats1);
//        MovieSession movieSession2 = new MovieSession(new ObjectId().toString(), timeslot2,
//                house2, MOVIE_2_PRICE, seats2);
//        MovieSession movieSession3 = new MovieSession(new ObjectId().toString(), timeslot3,
//                house3, MOVIE_2_PRICE, seats3);
//        Binary image1 = new Binary(new byte[1]);
//        Binary image2 = new Binary(new byte[1]);
//        Movie movie1 = new Movie(new ObjectId().toString(), MOVIE_1_NAME, tags1,image1, Arrays.asList(movieSession1), RELEASE_DATE1,RUNNING_TIME1, Language.ENGLISH,Language.CHINESE);
//        Movie movie2 = new Movie(new ObjectId().toString(), MOVIE_2_NAME, tags2,image2,Arrays.asList(movieSession2, movieSession3), RELEASE_DATE2,RUNNING_TIME2,Language.CHINESE,Language.CHINESE);
//        movieRepository.save(movie1);
//        movieRepository.save(movie2);
//
//        //when
//        //then
//        client.perform(MockMvcRequestBuilders.get("/movies/{id}/moviessessions",movie2.getId()))
//                .andExpect(MockMvcResultMatchers.status().isOk())
////                .andExpect(MockMvcResultMatchers.jsonPath("$.", hasSize(2)))
//                .andExpect(MockMvcResultMatchers.jsonPath(
//                        "$[*].house.name", containsInAnyOrder(house2.getName(),house3.getName()))
//                )
//                .andExpect(MockMvcResultMatchers.jsonPath(
//                        "$[*].house.numberOfRow", containsInAnyOrder(house2.getNumberOfRow(),house3.getNumberOfRow()))
//                )
//                .andExpect(MockMvcResultMatchers.jsonPath(
//                        "$[*].house.numberOfColumn", containsInAnyOrder(house2.getNumberOfColumn(),house3.getNumberOfColumn()))
//                )
//                .andExpect(MockMvcResultMatchers.jsonPath(
//                        "$[*].timeslot.id", containsInAnyOrder(timeslot2.getId(),timeslot3.getId()))
//                )
//                .andExpect(MockMvcResultMatchers.jsonPath(
//                        "$[*].price", containsInAnyOrder(movieSession2.getPrice(), movieSession3.getPrice()))
//                );
//    }
}
