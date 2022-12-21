package com.JAPKAM.Movieverse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class UnavailableSeatException extends RuntimeException{
    public UnavailableSeatException() {
        super("Unavailable Seat");
    }
}
