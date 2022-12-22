package com.JAPKAM.Movieverse.controller.dto;

import com.JAPKAM.Movieverse.entity.Seat;

import java.util.List;

public class TicketCreateRequest {
    private String movieSessionId;
    private List<Seat> seats;
    private List<String> food;

    public TicketCreateRequest() {
    }

    public String getMovieSessionId() {
        return movieSessionId;
    }

    public void setMovieSessionId(String movieSessionId) {
        this.movieSessionId = movieSessionId;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public List<String> getFood() {
        return food;
    }

    public void setFood(List<String> food) {
        this.food = food;
    }
}
