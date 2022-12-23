package com.JAPKAM.Movieverse.entity;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document
public class Ticket {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String movieSessionId;

    private List<Seat> seats;
    @DBRef
    private List<Food> foods;

    public Ticket(String id, String movieSessionId, List<Seat> seats, List<Food> foods) {
        this.id = id;
        this.movieSessionId = movieSessionId;
        this.seats = seats;
        this.foods = foods;
    }

    public Ticket() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }
}
