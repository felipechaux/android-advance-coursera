package com.example.petagram.pojo;

public class Mascota {

    private int id;
    private String nombre;
    private String urlImage;
    private int huesitos;

    public Mascota() {
    }

    public Mascota(String nombre, String urlImage, int huesitos) {
        this.nombre = nombre;
        this.urlImage = urlImage;
        this.huesitos = huesitos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public int getHuesitos() {
        return huesitos;
    }

    public void setHuesitos(int huesitos) {
        this.huesitos = huesitos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
