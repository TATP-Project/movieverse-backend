package com.JAPKAM.Movieverse.exception;

public class FoodNotFoundException extends RuntimeException{
    public FoodNotFoundException(){
        super("Food Not Found");
    }
}
