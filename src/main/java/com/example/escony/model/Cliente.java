package com.example.escony.model;


import jakarta.validation.constraints.Size;

public class Cliente{
    private String email;
    @Size(min=3,max=15, message="La longitud del nombre debe estar entre {min} y {max} caracteres")
    private String nombre;
    @Size(min=8,max=20, message="La longitud de la contraseña debe de ser entre {min} y {max} caracteres")
    private String password;
    private String direccion;
    public Cliente(){

    }

    public Cliente(String nombre,String password,String direccion,String email){
        this.nombre=nombre;
        this.direccion=direccion;
        this.email=email;
        this.password=password;
    }

    public Cliente(Cliente cliente) {
        this.nombre=cliente.nombre;
        this.email=cliente.email;
        this.password=cliente.password;
        this.direccion=cliente.direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}