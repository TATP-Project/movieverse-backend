package com.JAPKAM.Movieverse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidSeatException extends RuntimeException{
    public InvalidSeatException() {
        super("Invalid seat");
    }
}
