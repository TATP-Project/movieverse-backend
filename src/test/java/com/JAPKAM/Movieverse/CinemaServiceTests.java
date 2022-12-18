package com.JAPKAM.Movieverse;

import com.JAPKAM.Movieverse.entity.*;
import com.JAPKAM.Movieverse.repository.CinemaRepository;
import com.JAPKAM.Movieverse.service.CinemaService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CinemaServiceTests {

    public static final String CINEMA_1_NAME = "Cinema 1";
    public static final String CINEMA_2_NAME = "Cinema 2";
    @Mock
    CinemaRepository cinemaRepository;

    @InjectMocks
    CinemaService cinemaService;

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

    @Test
    void should_return_all_cinema_when_find_all_given_cinemas() {
        //given
        List<Tag> tags1 = Arrays.asList(new Tag(new ObjectId().toString(), ACTION_TAG));
        List<Tag> tags2 = Arrays.asList(new Tag(new ObjectId().toString(), ROMANTIC_TAG));

        Movie movie1 = new Movie(new ObjectId().toString(), MOVIE_1_NAME, tags1,null);
        Movie movie2 = new Movie(new ObjectId().toString(), MOVIE_2_NAME, tags2,null);

        House house1 = new House(new ObjectId().toString(), HOUSE_ONE, HOUSE_ONE_ROW_NUMBER, HOUSE_ONE_COL_NUMBER);
        House house2 = new House(new ObjectId().toString(), HOUSE_TWO, HOUSE_TWO_ROW_NUMBER, HOUSE_TWO_COL_NUMBER);

        Cinema cinema1 = new Cinema(new ObjectId().toString(), CINEMA_1_NAME, Arrays.asList(house1),
                Arrays.asList(movie1));
        Cinema cinema2 = new Cinema(new ObjectId().toString(), CINEMA_2_NAME, Arrays.asList(house2),
                Arrays.asList(movie2));

        when(cinemaRepository.findAll()).thenReturn(Arrays.asList(cinema1, cinema2));
        //when
        List<Cinema> returnedCinemas = cinemaService.findAll();

        //then
        assertThat(returnedCinemas, hasSize(2));
        assertThat(returnedCinemas.get(0), equalTo(cinema1));
        assertThat(returnedCinemas.get(1),equalTo(cinema2));
        verify(cinemaRepository).findAll();
    }

    @Test
    void should_return_cinema_1_when_find_by_id_given_id() {
        //given
        List<Tag> tags1 = Arrays.asList(new Tag(new ObjectId().toString(), ACTION_TAG));

        Movie movie1 = new Movie(new ObjectId().toString(), MOVIE_1_NAME, tags1,null);


        House house1 = new House(new ObjectId().toString(), HOUSE_ONE, HOUSE_ONE_ROW_NUMBER, HOUSE_ONE_COL_NUMBER);


        Cinema cinema1 = new Cinema(new ObjectId().toString(), CINEMA_1_NAME, Arrays.asList(house1),
                Arrays.asList(movie1));

        when(cinemaRepository.findById(cinema1.getId())).thenReturn(Optional.of(cinema1));
        //when

        Cinema returnedCinema = cinemaService.findById(cinema1.getId());

        //then
        assertThat(returnedCinema, equalTo(cinema1));
        verify(cinemaRepository).findById(cinema1.getId());
    }
}
