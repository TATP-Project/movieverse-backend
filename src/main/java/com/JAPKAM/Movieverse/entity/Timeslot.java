package com.JAPKAM.Movieverse.entity;

import java.util.Date;

public class Timeslot {
    private String id;
    private Date startDateTime;

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
