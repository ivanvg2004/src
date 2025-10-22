package com.paint.servlets.models;

/**
 * Esta clase representa el Modelo (POJO) de un Usuario.
 * Solo contiene los datos y sus getters.
 */
public class User {
    private String name;
    private String user;
    private String password; // ¡Recuerda que esto debería ser un hash!

    public User(String name, String user, String password) {
        this.name = name;
        this.user = user;
        this.password = password;
    }

    // Getters
    public String getName() { return name; }
    public String getUser() { return user; }
    public String getPassword() { return password; }
}