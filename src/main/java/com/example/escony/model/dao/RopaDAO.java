package com.example.escony.model.dao;

import com.example.escony.model.Ropa;

import java.util.List;



public interface RopaDAO{
    public Ropa buscaId(Integer id);
    public boolean borraPrenda(Integer idC);

    public boolean creaPrenda(Ropa r);

    public List<Ropa> buscaTodos();
    public boolean guardaPrenda(Ropa r);
    public boolean anadeCarrito(Ropa r);
}




