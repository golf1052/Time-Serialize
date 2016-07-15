package com.golf1052.time.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.javatic.mongo.jacksonCodec.objectId.Id;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class Comment {
    private String _id;
    private String comment;
    private OffsetDateTime createdAt;

    public Comment() {
    }

    public Comment(String comment) {
        this.comment = comment;
        createdAt = OffsetDateTime.now(ZoneOffset.UTC);
    }

    @Id
    public String get_id() {
        return _id;
    }

    @Id
    public void set_id(String _id) {
        this._id = _id;
    }

    @JsonProperty("comment")
    public String getComment() {
        return comment;
    }

    @JsonProperty("comment")
    public void setComment(String comment) {
        this.comment = comment;
    }

    @JsonProperty("created_at")
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
