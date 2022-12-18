package com.JAPKAM.Movieverse.service;

import com.JAPKAM.Movieverse.entity.Cinema;
import com.JAPKAM.Movieverse.entity.Movie;
import com.JAPKAM.Movieverse.exception.CinemaNotFoundException;
import com.JAPKAM.Movieverse.repository.CinemaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaService {

    private CinemaRepository cinemaRepository;

    public CinemaService(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    public List<Cinema> findAll() {
        return cinemaRepository.findAll();
    }

    public Cinema findById(String id) {
        return  cinemaRepository.findById(id).orElseThrow(CinemaNotFoundException::new);
    }

    public List<Movie> findMoviesByCinemaId(String id) {
        return findById(id).getMovies();
    }

//    public List<Movie> findAllMovies(String id) {
//        return findById(id).getMovies();
//    }
}
