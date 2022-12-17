package com.JAPKAM.Movieverse.entity;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.*;

import java.util.List;

@Document
public class Movie {
    @MongoId(FieldType.OBJECT_ID)
    private String id;

    private String name;

    @DBRef
    private List<Tag> tags;

    private Binary image;

    public Movie(String id, String name, List<Tag> tags, Binary image) {
        this.id = id;
        this.name = name;
        this.tags = tags;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Binary getImage() {
        return image;
    }

    public void setImage(Binary image) {
        this.image = image;
    }
}
