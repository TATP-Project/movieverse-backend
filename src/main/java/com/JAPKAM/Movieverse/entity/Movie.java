package com.JAPKAM.Movieverse.entity;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.*;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Document
public class Movie {
    @MongoId(FieldType.OBJECT_ID)
    private String id;

    private String name;

    @DBRef
    private List<Tag> tags;

    private Binary image;

    private GregorianCalendar releaseDate;
    private int runningTime;
    private Language language;
    private Language subtitle;

    public Movie(String id, String name, List<Tag> tags, Binary image,
                 GregorianCalendar releaseDate, int runningTime, Language language, Language subtitle) {
        this.id = id;
        this.name = name;
        this.tags = tags;
        this.image = image;
        this.releaseDate = releaseDate;
        this.runningTime = runningTime;
        this.language = language;
        this.subtitle = subtitle;
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

    public GregorianCalendar getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(GregorianCalendar releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(Language subtitle) {
        this.subtitle = subtitle;
    }
}
