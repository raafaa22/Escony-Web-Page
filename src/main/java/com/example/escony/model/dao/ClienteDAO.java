package com.example.escony.model.dao;
import com.example.escony.model.Cliente;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public interface ClienteDAO<Cliente,String> {
    public Cliente buscaEmail(String email);
    public List<Cliente> buscaTodos();
    public int numLibros();
    public boolean creaCliente(Cliente c);
    public boolean guardaCliente(Cliente c);
    public boolean borraCliente(String email);
}
