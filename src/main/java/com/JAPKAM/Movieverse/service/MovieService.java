package com.JAPKAM.Movieverse.service;

import com.JAPKAM.Movieverse.entity.Movie;
import com.JAPKAM.Movieverse.exception.MovieNotFoundException;
import com.JAPKAM.Movieverse.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MovieService {
    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie findById(String id) {
        return movieRepository.findById(id).orElseThrow(MovieNotFoundException::new);
    }
}
