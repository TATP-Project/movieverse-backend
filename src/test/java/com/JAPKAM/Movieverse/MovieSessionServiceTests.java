package com.JAPKAM.Movieverse;

import com.JAPKAM.Movieverse.entity.*;
import com.JAPKAM.Movieverse.repository.MovieSessionRepository;
import com.JAPKAM.Movieverse.service.MovieSessionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Calendar;

@ExtendWith(SpringExtension.class)
public class MovieSessionServiceTests {
    public static final String ACTION_TAG = "action";
    public static final String ROMANTIC_TAG = "romantic";
    public static final String MOVIE_1_NAME = "Movie 1";
    public static final String MOVIE_2_NAME = "Movie 2";
    public static final GregorianCalendar TIMESLOT_ONE = new GregorianCalendar(2022+1900, 12, 17, 14, 30);
    public static final GregorianCalendar TIMESLOT_TWO = new GregorianCalendar(2022+1900,12,17,17,30);
    public static final GregorianCalendar RELEASE_DATE1 = new GregorianCalendar(2022+1900,11,17);
    public static final GregorianCalendar RELEASE_DATE2 = new GregorianCalendar(2022+1900,10,17);
    public static final String HOUSE_ONE = "HOUSE ONE";
    public static final int HOUSE_ONE_ROW_NUMBER = 20;
    public static final int HOUSE_ONE_COL_NUMBER = 20;
    public static final String HOUSE_TWO = "HOUSE TWO";
    public static final int HOUSE_TWO_ROW_NUMBER = 5;
    public static final int HOUSE_TWO_COL_NUMBER = 10;
    public static final double MOVIE_1_PRICE = 80;
    public static final double MOVIE_2_PRICE = 90;

    public static final int RUNNING_TIME1 = 120;
    public static final int RUNNING_TIME2 = 120;


    @Mock
    MovieSessionRepository movieSessionRepository;

    @InjectMocks
    MovieSessionService movieSessionService;

    @Test
    void should_return_all_movie_session_when_find_all_given_movie_sessions() throws Exception {
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
        Movie movie1 = new Movie(new ObjectId().toString(), MOVIE_1_NAME, tags1,null,Arrays.asList(movieSession1), RELEASE_DATE1,RUNNING_TIME1,Language.ENGLISH,Language.CHINESE);
        Movie movie2 = new Movie(new ObjectId().toString(), MOVIE_2_NAME, tags2,null,Arrays.asList(movieSession2), RELEASE_DATE2,RUNNING_TIME2,Language.CHINESE,Language.CHINESE);

        List<MovieSession> movieSessions = Arrays.asList(movieSession1, movieSession2);
        when(movieSessionRepository.findAll()).thenReturn(movieSessions);
        //when
        List<MovieSession> returnedMoviesSessions = movieSessionService.findAll();

        //then
        assertThat(returnedMoviesSessions, hasSize(2));
        assertThat(returnedMoviesSessions.get(0), equalTo(movieSession1));
        assertThat(returnedMoviesSessions.get(1), equalTo(movieSession2));
        verify(movieSessionRepository).findAll();
    }

    @Test
    void should_return_movie_session_1_when_find_by_id_given_movie_session_id() throws Exception {
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

        when(movieSessionRepository.findById(movieSession1.getId())).thenReturn(Optional.of(movieSession1));
        //when

        MovieSession returnedMoviesSession = movieSessionService.findById(movieSession1.getId());

        //then
        assertThat(movieSession1, equalTo(returnedMoviesSession));
        verify(movieSessionRepository).findById(movieSession1.getId());
    }

    @Test
    void should_return_all_seats_when_get_seats_given_movie_session_id() throws Exception {
        //given
        House house1 = new House(new ObjectId().toString(), HOUSE_ONE, 1, 1);

        List<Seat> seats1 = new ArrayList<>();
        seats1.add(new Seat(new ObjectId().toString(), 1, 1, SeatStatus.AVAILABLE));

        String id = new ObjectId().toString();
        MovieSession movieSession1 = new MovieSession(id, null, null,
                house1, MOVIE_1_PRICE, seats1);

        when(movieSessionRepository.findById(id)).thenReturn(Optional.of(movieSession1));
        //when

        List<Seat> returnedSeats = movieSessionService.findById(id).getSeats();

        //then
        assertThat(seats1, equalTo(returnedSeats));
        verify(movieSessionRepository).findById(movieSession1.getId());
    }
    @Test
    void should_update_seat_status_when_update_given_movie_session() throws Exception {
        // given
        House house1 = new House(new ObjectId().toString(), HOUSE_ONE, 1, 1);

        List<Seat> seats1 = new ArrayList<>();
        String seatId = new ObjectId().toString();
        seats1.add(new Seat(seatId, 1, 1, SeatStatus.AVAILABLE));

        String id = new ObjectId().toString();
        MovieSession movieSession1 = new MovieSession(id, null, null,
                house1, MOVIE_1_PRICE, seats1);
        movieSessionRepository.save(movieSession1);
        Seat newSeat = new Seat(seatId, 2, 2, SeatStatus.RESERVED);

        when(movieSessionRepository.findById(id)).thenReturn(Optional.of(movieSession1));
        // when
        Seat updatedSeat = movieSessionService.updateSeat(id,newSeat);
        // then
        verify(movieSessionRepository).findById(id);
        assertThat(updatedSeat.getRow(),equalTo(1));
        assertThat(updatedSeat.getColumn(),equalTo(1));
        assertThat(updatedSeat.getStatus(),equalTo(SeatStatus.RESERVED));

    }



}
