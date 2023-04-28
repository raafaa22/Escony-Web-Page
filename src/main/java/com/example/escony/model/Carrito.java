package com.example.escony.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Carrito {
    @Id
    int idCarrito;
    int idRopa;
    String email;


    public Carrito() {
        idRopa=0;
        idCarrito=0;
        email="";
    }

    public Carrito(int idCarrito,int idRopa,String email) {
        this.idRopa=idRopa;
        this.email=email;
        this.idCarrito=idCarrito;
    }

    public int getIdRopa() {
        return idRopa;
    }

    public void setIdRopa(int idRopa) {
        this.idRopa = idRopa;
    }

    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
