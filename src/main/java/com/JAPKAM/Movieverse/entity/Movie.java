package com.JAPKAM.Movieverse.entity;

import org.bson.types.Binary;

import java.util.List;

public class Movie {
    private String id;

    private String name;

    private List<Tag> tags;

    private Binary image;
}
