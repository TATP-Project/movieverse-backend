package com.JAPKAM.Movieverse.entity;

import org.springframework.data.mongodb.core.mapping.*;

import java.util.List;

@Document
public class Cinema {

    @MongoId(FieldType.OBJECT_ID)
    private String id;

    private String name;

    @DBRef
    private List<House> houses;

    @DBRef
    private List<Movie> movies;

    private DistrictName district;

    public String getId() {
        return id;
    }

    public Cinema(String id, String name, List<House> houses, List<Movie> movies) {
        this.id = id;
        this.name = name;
        this.houses = houses;
        this.movies = movies;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<House> getHouses() {
        return houses;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public DistrictName getDistrict() {
        return district;
    }

    public void setDistrict(DistrictName district) {
        this.district = district;
    }
}
