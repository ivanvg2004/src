package com.paint.servlets.models;

import java.util.Date;

public class Canvas {
    private final  int id;
    private String name;
    private String content;
    private final Date createdAt;

    public Canvas(int id, String name, String content){
        this.id = id;
        this.name = name;
        this.content = content;
        this.createdAt = new Date();
    }

    public Date getCreatedAt() { return createdAt; }

    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }
}
