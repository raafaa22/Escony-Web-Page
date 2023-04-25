package com.example.escony.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Carrito {
    @Id
    private Integer id_prenda;
    @Id
    private String email_usu;

    // Constructor sin parámetros
    public Carrito() {
        id_prenda=0;
        email_usu="";
    }

    // Constructor con parámetros
    public Carrito(Integer id_prenda,String email_usu) {
        this.id_prenda=id_prenda;
        this.email_usu=email_usu;
    }

    public String getEmail_usu() {
        return email_usu;
    }

    public void setEmail_usu(String email_usu) {
        this.email_usu = email_usu;
    }

    public Integer getId_prenda() {
        return id_prenda;
    }

    public void setId_prenda(Integer id_prenda) {
        this.id_prenda = id_prenda;
    }
}
