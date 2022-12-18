package com.JAPKAM.Movieverse.controller;

import com.JAPKAM.Movieverse.entity.Movie;
import com.JAPKAM.Movieverse.entity.MovieSession;
import com.JAPKAM.Movieverse.service.MovieSessionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class MovieSessionController {

    private MovieSessionService movieSessionService;

    public MovieSessionController(MovieSessionService movieSessionService) {
        this.movieSessionService = movieSessionService;
    }

    @GetMapping("/moviesessions")
    public List<MovieSession> findAllMovieSessions() {
        return movieSessionService.findAll();
    }

    @GetMapping("/moviesessions/{id}")
    public MovieSession getMovieById(@PathVariable String id) {
        return movieSessionService.findById(id);
    }
}
