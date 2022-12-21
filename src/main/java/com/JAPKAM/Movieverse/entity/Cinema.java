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

    private String district;

    public String getId() {
        return id;
    }

    public Cinema(String id, String name, List<House> houses, String district) {
        this.id = id;
        this.name = name;
        this.houses = houses;
        this.district = district;
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
