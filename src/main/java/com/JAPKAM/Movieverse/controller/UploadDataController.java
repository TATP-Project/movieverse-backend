package com.JAPKAM.Movieverse.controller;

import com.JAPKAM.Movieverse.entity.*;
import com.JAPKAM.Movieverse.repository.*;
import com.JAPKAM.Movieverse.service.CinemaService;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/testdata")
public class UploadDataController {

    public static final String SUPERHERO = "superhero";
    public static final String HOUSE_THREE = "HOUSE_THREE";
    public static final String MOVIE_3_NAME = "The Batman";
    public static final double MOVIE_3_PRICE = 100;
    private CinemaRepository cinemaRepository;
    private HouseRepository houseRepository;
    private MovieRepository movieRepository;
    private MovieSessionRepository movieSessionRepository;
    private TagRepository tagRepository;
    private TimeslotRepository timeslotRepository;

    public UploadDataController(CinemaRepository cinemaRepository, HouseRepository houseRepository, MovieRepository movieRepository, MovieSessionRepository movieSessionRepository, TagRepository tagRepository, TimeslotRepository timeslotRepository) {
        this.cinemaRepository = cinemaRepository;
        this.houseRepository = houseRepository;
        this.movieRepository = movieRepository;
        this.movieSessionRepository = movieSessionRepository;
        this.tagRepository = tagRepository;
        this.timeslotRepository = timeslotRepository;
    }

    public static final String ACTION_TAG = "action";
    public static final String ROMANTIC_TAG = "romantic";
    public static final String MOVIE_1_NAME = "Movie 1";
    public static final String MOVIE_2_NAME = "Movie 2";
    public static final GregorianCalendar TIMESLOT_ONE = new GregorianCalendar(2022+1900, 12, 17, 14, 30);
    public static final GregorianCalendar TIMESLOT_TWO = new GregorianCalendar(2022+1900,12,17,17,30);
    public static final GregorianCalendar TIMESLOT_THREE = new GregorianCalendar(2022+1900,12,18,17,30);
    public static final GregorianCalendar TIMESLOT_FOUR = new GregorianCalendar(2022+1900,12,19,17,30);
    public static final GregorianCalendar TIMESLOT_FIVE = new GregorianCalendar(2022+1900,12,20,17,30);
    public static final GregorianCalendar TIMESLOT_SIX = new GregorianCalendar(2022+1900,12,21,17,30);
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
    @PostMapping
    void createTestData() {
        //given
        Tag tag1 = new Tag(new ObjectId().toString(), ACTION_TAG);
        Tag tag2 = new Tag(new ObjectId().toString(), ROMANTIC_TAG);
        Tag tag3 = new Tag(new ObjectId().toString(), SUPERHERO);
        List<Tag> tags1 = Arrays.asList(tag1);
        List<Tag> tags2 = Arrays.asList(tag2);
        List<Tag> tags3 = Arrays.asList(tag3);
        tagRepository.saveAll(Arrays.asList(tag1, tag2, tag3));

        Timeslot timeslot1 = new Timeslot(new ObjectId().toString(), TIMESLOT_ONE);
        Timeslot timeslot2 = new Timeslot(new ObjectId().toString(), TIMESLOT_TWO);
        Timeslot timeslot3 = new Timeslot(new ObjectId().toString(), TIMESLOT_THREE);
        Timeslot timeslot4 = new Timeslot(new ObjectId().toString(), TIMESLOT_FOUR);
        Timeslot timeslot5 = new Timeslot(new ObjectId().toString(), TIMESLOT_FIVE);
        Timeslot timeslot6 = new Timeslot(new ObjectId().toString(), TIMESLOT_SIX);
        timeslotRepository.saveAll(Arrays.asList(timeslot1, timeslot2, timeslot3, timeslot4, timeslot5, timeslot6));

        House house1 = new House(new ObjectId().toString(), HOUSE_ONE, HOUSE_ONE_ROW_NUMBER, HOUSE_ONE_COL_NUMBER);
        House house2 = new House(new ObjectId().toString(), HOUSE_TWO, HOUSE_TWO_ROW_NUMBER, HOUSE_TWO_COL_NUMBER);
        House house3 = new House(new ObjectId().toString(), HOUSE_THREE, HOUSE_TWO_ROW_NUMBER, HOUSE_TWO_COL_NUMBER);
        houseRepository.saveAll(Arrays.asList(house1, house2, house3));

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

        List<Seat> seats3 = new ArrayList<>();
        for(int i = 0 ; i < house3.getNumberOfRow(); i++){
            for(int j =0 ;j <house3.getNumberOfColumn(); j++) {
                seats2.add(new Seat(new ObjectId().toString(), i+1, j+1, SeatStatus.AVAILABLE));
            }
        }
        Movie movie1 = new Movie(new ObjectId().toString(), MOVIE_1_NAME, tags1,null, RELEASE_DATE1,RUNNING_TIME1,Language.ENGLISH,Language.CHINESE);
        Movie movie2 = new Movie(new ObjectId().toString(), MOVIE_2_NAME, tags2,null, RELEASE_DATE2,RUNNING_TIME2,Language.CHINESE,Language.CHINESE);
        Movie movie3 = new Movie(new ObjectId().toString(), MOVIE_3_NAME, tags3,null, RELEASE_DATE2,RUNNING_TIME2,Language.CHINESE,Language.CHINESE);
        movieRepository.saveAll(Arrays.asList(movie1, movie2, movie3));

        String district1 = DistrictName.KOWLOON.toString();
        String district2 = DistrictName.HONG_KONG.toString();

        Cinema cinema1 = new Cinema(new ObjectId().toString(), CINEMA_1_NAME, Arrays.asList(house1),district1);
        Cinema cinema2 = new Cinema(new ObjectId().toString(), CINEMA_2_NAME, Arrays.asList(house2, house3),district2);
        cinemaRepository.saveAll(Arrays.asList(cinema1, cinema2));

        MovieSession movieSession1 = new MovieSession(new ObjectId().toString(),timeslot1, cinema1, movie1,
                house1,MOVIE_1_PRICE,seats1);
        MovieSession movieSession2 = new MovieSession(new ObjectId().toString(), timeslot2, cinema2, movie2,
                house2, MOVIE_2_PRICE, seats2);
        MovieSession movieSession3 = new MovieSession(new ObjectId().toString(), timeslot3, cinema2, movie2,
                house2, MOVIE_2_PRICE, seats2);
        MovieSession movieSession4 = new MovieSession(new ObjectId().toString(), timeslot4, cinema2, movie2,
                house3, MOVIE_2_PRICE, seats3);
        MovieSession movieSession5 = new MovieSession(new ObjectId().toString(), timeslot5, cinema2, movie3,
                house2, MOVIE_3_PRICE, seats2);
        MovieSession movieSession6 = new MovieSession(new ObjectId().toString(), timeslot6, cinema2, movie3,
                house3, MOVIE_3_PRICE, seats2);
        movieSessionRepository.saveAll(Arrays.asList(movieSession1, movieSession2, movieSession3
                , movieSession4, movieSession5, movieSession6));

    }
}
