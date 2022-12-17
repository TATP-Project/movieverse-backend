package com.JAPKAM.Movieverse.controller;

import com.JAPKAM.Movieverse.entity.Movie;
import com.JAPKAM.Movieverse.service.MovieService;
import com.mongodb.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> getAll() {
        return movieService.findAll();
    }

    @GetMapping("/{id}")
    public Movie getById(@NonNull @PathVariable String id){
        Movie findMovie = movieService.findById(id);
        return movieService.findById(id);
    }
}
