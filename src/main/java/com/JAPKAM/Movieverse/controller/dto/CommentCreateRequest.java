package com.JAPKAM.Movieverse.controller.dto;

public class CommentCreateRequest {
    private String movieId;
    private String iconLink;
    private String content;

    public CommentCreateRequest(String movieId, String iconLink, String content) {
        this.movieId = movieId;
        this.iconLink = iconLink;
        this.content = content;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getIconLink() {
        return iconLink;
    }

    public void setIconLink(String iconLink) {
        this.iconLink = iconLink;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
