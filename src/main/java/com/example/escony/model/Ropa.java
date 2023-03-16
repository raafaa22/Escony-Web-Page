package com.example.escony.model;

public class Ropa {
    private String nombre;
    private String talla;
    private double precio;

    public Ropa(String nombre, String talla, double precio) {
        this.nombre = nombre;
        this.talla = talla;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
