package com.paint.servlets.models;

public class Canvas {
    private String name;
    private final String[] content;

    public Canvas(String name, String[] content){
        this.name = name;
        this.content = content;
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
