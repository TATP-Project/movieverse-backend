package com.JAPKAM.Movieverse.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
public class House {
    @MongoId(FieldType.OBJECT_ID)
    private String id;

    private String name;

    private Integer numberOfRow;

    private Integer numberOfColumn;

    public House(String id, String name, Integer numberOfRow, Integer numberOfColumn) {
        this.id = id;
        this.name = name;
        this.numberOfRow = numberOfRow;
        this.numberOfColumn = numberOfColumn;
    }

    public String getId() {
        return id;
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

    public Integer getNumberOfRow() {
        return numberOfRow;
    }

    public void setNumberOfRow(Integer numberOfRow) {
        this.numberOfRow = numberOfRow;
    }

    public Integer getNumberOfColumn() {
        return numberOfColumn;
    }

    public void setNumberOfColumn(Integer numberOfColumn) {
        this.numberOfColumn = numberOfColumn;
    }
}
