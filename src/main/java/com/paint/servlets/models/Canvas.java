package com.paint.servlets.models;

public class Canvas {
    private final int id;
    private String name;
    private final String[] content;

    Canvas(int id, String name, String[] content){
        this.id = id;
        this.content = content;
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getContent() {
        return content;
    }
}
