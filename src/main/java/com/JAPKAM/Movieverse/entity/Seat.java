package com.JAPKAM.Movieverse.entity;


public class Seat {

    private String id;
    private Integer row;
    private Integer column;
    private SeatStatus status;

    public Seat(String id, Integer row, Integer column, SeatStatus status) {
        this.id = id;
        this.row = row;
        this.column = column;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }
}
