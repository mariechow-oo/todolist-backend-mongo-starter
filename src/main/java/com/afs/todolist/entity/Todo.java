package com.afs.todolist.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
public class Todo {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String text;
    private Boolean done;

    public Todo() {
    }

    public Todo(String id, String text, Boolean done) {
        this.id = id;
        this.text = text;
        this.done = done;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
