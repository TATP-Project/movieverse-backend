package com.JAPKAM.Movieverse;

import com.JAPKAM.Movieverse.entity.*;
import com.JAPKAM.Movieverse.repository.MovieRepository;
import com.JAPKAM.Movieverse.service.MovieService;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.io.IOException;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class MovieServiceTest {
    @Mock
    MovieRepository movieRepository;

    @InjectMocks
    MovieService movieService;

    public static final String ACTION_TAG = "action";
    public static final String ROMANTIC_TAG = "romantic";
    public static final String MOVIE_1_NAME = "Movie 1";
    public static final String MOVIE_2_NAME = "Movie 2";
    public static final GregorianCalendar RELEASE_DATE1 = new GregorianCalendar(2022+1900,11,17);
    public static final GregorianCalendar RELEASE_DATE2 = new GregorianCalendar(2022+1900,10,17);
    public static final int RUNNING_TIME1 = 120;
    public static final int RUNNING_TIME2 = 100;

    public static final String HOUSE_ONE = "HOUSE ONE";
    public static final int HOUSE_ONE_ROW_NUMBER = 20;
    public static final int HOUSE_ONE_COL_NUMBER = 20;
    public static final GregorianCalendar TIMESLOT_ONE = new GregorianCalendar(2022+1900, 12, 17, 14, 30);
    public static final GregorianCalendar TIMESLOT_TWO = new GregorianCalendar(2022+1900,12,17,17,30);
    @Test
    void should_return_all_movie_when_find_all_given_movies() throws IOException {
        //given
        List<Tag> tags1 = Arrays.asList(new Tag(new ObjectId().toString(), ACTION_TAG));
        List<Tag> tags2 = Arrays.asList(new Tag(new ObjectId().toString(), ROMANTIC_TAG));
        Movie movie1 = new Movie(new ObjectId().toString(), MOVIE_1_NAME, tags1,null, RELEASE_DATE1,RUNNING_TIME1,Language.ENGLISH,Language.CHINESE);
        Movie movie2 = new Movie(new ObjectId().toString(), MOVIE_2_NAME, tags2,null, RELEASE_DATE2,RUNNING_TIME2,Language.CHINESE,Language.CHINESE);

        when(movieRepository.findAll()).thenReturn(Arrays.asList(movie1,movie2));
        //when
        List<Movie> result = movieService.findAll();
        //then
        assertThat(result,hasSize(2));
        assertThat(result.get(0), equalTo(movie1));
        assertThat(result.get(1), equalTo(movie2));
        verify(movieRepository).findAll();
    }

    @Test
    void should_return_movie_when_find_by_id_given_movie() {
        //given
        List<Tag> tags1 = Arrays.asList(new Tag(new ObjectId().toString(), ACTION_TAG));
        String id = new ObjectId().toString();
        Movie movie1 = new Movie(id, MOVIE_1_NAME, tags1,null, RELEASE_DATE1,RUNNING_TIME1,Language.ENGLISH,Language.CHINESE);
        given(movieRepository.findById(id)).willReturn(Optional.of(movie1));
        //when
        Movie result = movieService.findById(id);
        //then
        verify(movieRepository).findById(id);
        assertThat(result,equalTo(movie1));
    }


}
