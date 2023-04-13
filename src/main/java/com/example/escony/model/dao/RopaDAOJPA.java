package com.example.escony.model.dao;

import com.example.escony.model.Ropa;
import com.example.escony.qualifiers.DAOJPA;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped  //Elegible for Dependency Injection
@DAOJPA
@Transactional
public class RopaDAOJPA implements RopaDAO, Serializable {
    private final Logger logger = Logger.getLogger(ClienteDAOJPA.class.getName());

    @PersistenceContext(unitName = "datos") //Only for JEE full application servers
    //Requires to enable Persistence-unit in persistence.xml
    private EntityManager em;

    public RopaDAOJPA() {
    }
    @Override
    public Ropa buscaId(Integer id){
        Ropa c=null;
        try {
            c=em.find(Ropa.class, id);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);

        }
        return c;
    }
    @Override
    public List<Ropa> buscaTodos() {
        List<Ropa> lc = null;
        try {
            Query q = em.createQuery("Select c from Cliente c", Ropa.class);
            lc = (List<Ropa>)q.getResultList();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return lc;
    }


    /** Sample nativeQuery method*/

    @Override
    public boolean creaPrenda(Ropa r) {
        boolean creado = false;
        try {
            em.persist(r);
            creado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return creado;
    }

    @Override
    public boolean guardaPrenda(Ropa r){
        boolean guardado = false;
        try {
            r = em.merge(r);
            guardado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return guardado;
    }

    @Override
    public boolean borraPrenda(Integer idC){
        boolean borrado = false;
        try {
            Ropa c = null;
            c = em.find(Ropa.class, idC);
            em.remove(c);
            borrado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return borrado;
    }
}
