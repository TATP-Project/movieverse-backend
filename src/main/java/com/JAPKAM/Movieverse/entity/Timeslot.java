package com.JAPKAM.Movieverse.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Timeslot {
    private String id;
    private Date startDateTime;

    public Timeslot(String id, Date startDateTime) {
        this.id = id;
        this.startDateTime = startDateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }
}
