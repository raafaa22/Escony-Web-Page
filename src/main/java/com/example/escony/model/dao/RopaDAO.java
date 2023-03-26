package com.example.escony.model.dao;

import com.example.escony.model.Cliente;
import com.example.escony.model.Ropa;
import com.example.escony.model.RopaFavorita;

public interface RopaDAO  extends GenericDAO<Ropa,Integer>{

    public Ropa getRopaLIst(String id);
}




