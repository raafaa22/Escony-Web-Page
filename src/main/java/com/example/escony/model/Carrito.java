package com.example.escony.model;


import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<Ropa> prendas;
    private double precioTotal;
    private int cantidad;

    // Constructor sin parámetros
    public Carrito() {
        this.prendas = new ArrayList<>();
        this.precioTotal = 0;
        this.cantidad = 0;
    }

    // Constructor con parámetros
    public Carrito(List<Ropa> prendas, Double precioTotal, Integer cantidad) {
        this.prendas = prendas;
        this.precioTotal = precioTotal;
        this.cantidad = cantidad;
    }

    public List<Ropa> getPrendas() {
        return prendas;
    }

    public void setPrendas(List<Ropa> prendas) {
        this.prendas = prendas;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
