package com.JAPKAM.Movieverse.service;

import com.JAPKAM.Movieverse.entity.MovieSession;
import com.JAPKAM.Movieverse.repository.MovieSessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieSessionService {

    private MovieSessionRepository movieSessionRepository;

    public MovieSessionService(MovieSessionRepository movieSessionRepository){
        this.movieSessionRepository = movieSessionRepository;
    }

    public List<MovieSession> findAll() {
        return movieSessionRepository.findAll();
    }
}
