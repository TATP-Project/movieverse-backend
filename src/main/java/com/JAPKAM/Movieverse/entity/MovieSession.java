package com.JAPKAM.Movieverse.entity;

import org.springframework.data.mongodb.core.mapping.*;

import java.util.List;

@Document
public class MovieSession {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    @DBRef
    private Timeslot timeslot;
    @DBRef
    private House house;
    private double price;

    private List<Seat> seats;

    public MovieSession(String id, Timeslot timeslot, House house, double price, List<Seat> seats) {
        this.id = id;
        this.timeslot = timeslot;
        this.house = house;
        this.price = price;
        this.seats = seats;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Timeslot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
