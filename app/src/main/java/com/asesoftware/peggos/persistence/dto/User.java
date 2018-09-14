package com.asesoftware.peggos.persistence.dto;

public class User {

    private int id;
    private String nombre;

    public User(String nombre) {
        this.nombre = nombre;
    }

    public User() {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.toUpperCase();
    }


}
