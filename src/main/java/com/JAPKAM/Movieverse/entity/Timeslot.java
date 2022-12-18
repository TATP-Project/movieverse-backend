package com.JAPKAM.Movieverse.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Document
public class Timeslot {
    private String id;
    private GregorianCalendar startDateTime;

    public Timeslot(String id, GregorianCalendar startDateTime) {
        this.id = id;
        this.startDateTime = startDateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GregorianCalendar getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(GregorianCalendar startDateTime) {
        this.startDateTime = startDateTime;
    }
}
