package com.example.escony.model.dao;

import com.example.escony.model.Cliente;
import com.example.escony.qualifiers.DAOMap;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@DAOMap
@ApplicationScoped
public class  ClienteDAOMap {
    private Map<String, Cliente> clientes;

    public void ClienteDAO() {
            clientes = new HashMap<>();
            clientes.put("rafa@email.com", new Cliente("Rafa", "Serrano", "Fuente del Pinillo", "rafa@email.com"));
            clientes.put("lydia@email.com", new Cliente("lydia", "Munoz", "Mi casa", "lydia@email.com"));
            clientes.put("erre@email.com", new Cliente("alvaro", "Herre", "Arjona", "erre@email.com"));
            clientes.put("chiviri@email.com", new Cliente("eden", "sierra", "pueblo", "chiviri@email.com"));
            clientes.put("gusilu@email.com", new Cliente("elena", "carmon", "Museo", "gusilu@email.com"));

    }

    public Cliente buscaEmail(String email) {
        return new Cliente(clientes.get(email));
    }

    public List<Cliente> buscaTodos() {
        return clientes.values().stream().collect(Collectors.toList());
    }

    public int numLibros() {
        return clientes.size();
    }

    public boolean creaCliente(Cliente c) {
        Cliente nc = new Cliente(c);
        clientes.put(c.getEmail(), nc);
        return true;
    }

    public boolean guardaCliente(Cliente c) {
        boolean result = false;
        if (clientes.containsKey(c.getEmail())) {
            Cliente nc = new Cliente(c);
            clientes.replace(c.getEmail(), nc);
            result = true;
        }
        return result;
    }


    public boolean borraCliente(String email) {
        boolean result = false;
        if (clientes.containsKey(email)) {
            clientes.remove(email);
            result = true;
        }
        return result;
    }
}



