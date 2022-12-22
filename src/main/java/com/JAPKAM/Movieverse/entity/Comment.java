package com.JAPKAM.Movieverse.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
public class Comment {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String iconLink;
    private String content;

    public Comment(String id, String iconLink, String content) {
        this.id = id;
        this.iconLink = iconLink;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
