package com.JAPKAM.Movieverse.controller;

import com.JAPKAM.Movieverse.entity.MovieSession;
import com.JAPKAM.Movieverse.entity.Seat;
import com.JAPKAM.Movieverse.service.MovieSessionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie-sessions")
public class MovieSessionController {

    private MovieSessionService movieSessionService;

    public MovieSessionController(MovieSessionService movieSessionService) {
        this.movieSessionService = movieSessionService;
    }

    @GetMapping
    public List<MovieSession> getAllMovieSessions() {
        return movieSessionService.findAll();
    }

    @GetMapping(params = "movieId")
    public List<MovieSession> getAllMovieSessionsByMovieId(@RequestParam String movieId) {
        return movieSessionService.findByMovieId(movieId);
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
    public List<Seat> updateSeat(@PathVariable String id, @RequestBody List<Seat> seats){
        return movieSessionService.updateSeatStatus(id, seats);
    }

}
