package com.JAPKAM.Movieverse.entity;

public enum DistrictName {
    KOWLOON("Kowloon"), HONG_KONG("Hong Kong"), NEW_TERRITORIES("New Territories");
    private final String toString;
    DistrictName(String toString) {
        this.toString = toString;
    }

    public String toString(){
        return toString;
    }
}
