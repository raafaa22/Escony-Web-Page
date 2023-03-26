package com.example.escony.model;
public class Ropa{
    private String nombre;
    private String talla;
    private Double precio;
    private Integer id;

    public Ropa(String nombre, String talla, Double precio,Integer id) {
        this.nombre = nombre;
        this.talla = talla;
        this.precio = precio;
        this.id=id;
    }
    public Ropa(Ropa ropa){
        this.nombre = ropa.nombre;
        this.talla = ropa.talla;
        this.precio = ropa.precio;
        this.id=ropa.id;
    }
    public Ropa(){

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
