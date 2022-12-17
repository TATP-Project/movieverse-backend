package com.JAPKAM.Movieverse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TimeslotNotFoundException extends RuntimeException{
    public TimeslotNotFoundException() {
        super("Timeslot Not Found");
    }
}
