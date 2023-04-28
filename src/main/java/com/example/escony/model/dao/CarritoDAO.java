package com.example.escony.model.dao;

import com.example.escony.model.Carrito;
import com.example.escony.model.Ropa;

import java.util.List;

public interface CarritoDAO {
    public List<Ropa> buscaPorEmail(String email);
    public List<Carrito> buscaPorIdPrenda(int id);
    public boolean borraCarrito(int id);
    public boolean guardaCarrito(Carrito c);
    public boolean creaCarrito(Carrito c);
    public Carrito buscaId(Integer id);
    public List<Carrito> buscaTodos();
}
