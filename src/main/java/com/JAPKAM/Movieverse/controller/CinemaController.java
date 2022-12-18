package com.JAPKAM.Movieverse.controller;

import com.JAPKAM.Movieverse.entity.Cinema;
import com.JAPKAM.Movieverse.entity.Movie;
import com.JAPKAM.Movieverse.service.CinemaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    private CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping
    public List<Cinema> findAllCinemas() {
        return cinemaService.findAll();
    }

    @GetMapping("/{id}/movies")
    public List<Movie> findMoviesByCinemaId(@PathVariable String id){
        return cinemaService.findMoviesByCinemaId(id);
    }

}
