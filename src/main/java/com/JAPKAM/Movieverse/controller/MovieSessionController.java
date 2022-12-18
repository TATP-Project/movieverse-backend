package com.JAPKAM.Movieverse.controller;

import com.JAPKAM.Movieverse.entity.Movie;
import com.JAPKAM.Movieverse.entity.MovieSession;
import com.JAPKAM.Movieverse.entity.Seat;
import com.JAPKAM.Movieverse.service.MovieSessionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moviesessions")
public class MovieSessionController {

    private MovieSessionService movieSessionService;

    public MovieSessionController(MovieSessionService movieSessionService) {
        this.movieSessionService = movieSessionService;
    }

    @GetMapping
    public List<MovieSession> findAllMovieSessions() {
        return movieSessionService.findAll();
    }

    @GetMapping("/{id}")
    public MovieSession getMovieById(@PathVariable String id) {
        return movieSessionService.findById(id);
    }

    @GetMapping("/{id}/seats")
    public List<Seat> getSeats(@PathVariable String id) {
        return movieSessionService.getSeats(id);
    }

    @PutMapping("/{id}/seats")
    public Seat updateSeat(@PathVariable String id, @RequestBody Seat seat){
        return movieSessionService.updateSeat(id, seat);
    }
}
