package com.example.escony.model.dao;

import java.util.List;



public interface RopaDAO <Ropa,Integer>{
    public Ropa buscaId(Integer id);
    public List<Ropa> buscaTodos();
    public boolean guardaPrenda(Ropa r);
    public boolean creaPrenda(Ropa r);
}




