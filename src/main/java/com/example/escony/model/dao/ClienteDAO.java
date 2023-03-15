package com.example.escony.model.dao;
import com.example.escony.model.Cliente;
import java.util.List;
public interface ClienteDAO extends GenericDAO<Cliente,Integer>{
    //Declare here specific methods for EntityDAO
    public Cliente buscaByNIF(String nif);
}
