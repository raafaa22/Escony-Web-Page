package com.example.escony.model.dao;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import com.example.escony.qualifiers.DAOJPA;
import com.example.escony.model.Cliente;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped  //Elegible for Dependency Injection
@DAOJPA
@Transactional
public class ClienteDAOJPA implements ClienteDAO, Serializable {
    private final Logger logger = Logger.getLogger(ClienteDAOJPA.class.getName());

    private EntityManager em;

    public ClienteDAOJPA() {
    }

    @Override
    public Cliente buscaEmail(String email) {
        Cliente c=null;
        try {
            c=em.find(Cliente.class, email);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);

        }
        return c;
    }
    @Override
    public List<Cliente> buscaTodos() {
        List<Cliente> lc = null;
        try {
            Query q = em.createQuery("Select c from Cliente c", Cliente.class);
            lc = (List<Cliente>)q.getResultList();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return lc;
    }


    /** Sample nativeQuery method*/

    @Override
    public boolean creaCliente(Cliente c) {
        boolean creado = false;
        try {
            em.persist(c);
            creado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return creado;
    }

    @Override
    public boolean guardaCliente(Cliente c) {
        boolean guardado = false;
        try {
            c = em.merge(c);
            guardado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return guardado;
    }

    @Override
    public boolean borraCliente(String email) {
        boolean borrado = false;
        try {
            Cliente c = null;
            c = em.find(Cliente.class, email);
            em.remove(c);
            borrado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return borrado;
    }
}
