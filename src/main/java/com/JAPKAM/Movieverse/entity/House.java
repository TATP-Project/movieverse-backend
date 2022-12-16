package com.JAPKAM.Movieverse.entity;

public class House {
    private String id;

    private String name;

    private Integer numberOfRow;

    private Integer numberOfColumn;

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
