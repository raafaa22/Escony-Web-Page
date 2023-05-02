package com.example.escony.model.dao;

import com.example.escony.model.Carrito;
import com.example.escony.qualifiers.DAOJPA;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import com.example.escony.model.Ropa;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@RequestScoped  //Elegible for Dependency Injection
@DAOJPA
@Transactional
public class CarritoDAOJPA implements CarritoDAO, Serializable {
    private final Logger logger = Logger.getLogger(CarritoDAOJPA.class.getName());
    @PersistenceContext(unitName = "datos")
    private EntityManager em;
    public CarritoDAOJPA(){

    }
    @Override
    public List<Ropa> buscaPorEmail(String email) {
        List<Ropa> lr=null;
        try {
            Query q=em.createQuery("SELECT r FROM Carrito c,Ropa r WHERE c.email= :email AND r.id=c.idRopa",Carrito.class);
            q.setParameter("email",email);
            lr=(List<Ropa>) q.getResultList();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);

        }
        return lr;
    }

    @Override
    public List<Carrito> buscaPorIdPrenda(int id) {
        List<Carrito> lc=null;
        try {
            Query q=em.createQuery("SELECT c FROM Carrito c WHERE c.idRopa= :id",Carrito.class);
            q.setParameter("id",id);
            lc=(List<Carrito>) q.getResultList();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);

        }
        return lc;
    }
    public Carrito buscaPorPrendaEmail(int idP,String email) {
        Carrito carrito=null;
        try {
            Query q=em.createQuery("SELECT c FROM Carrito c WHERE c.email = :email AND c.idRopa = :idRopa AND c.idCarrito = (SELECT MIN(c2.idCarrito) FROM Carrito c2 WHERE c2.email = :email AND c2.idRopa = :idRopa)",Carrito.class);
            q.setParameter("idRopa",idP);
            q.setParameter("email",email);
            carrito=(Carrito) q.getSingleResult();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);

        }
        return carrito;
    }
    @Override
    public List<Carrito> buscaTodos() {
        List<Carrito> lc=null;
        try {
            Query q=em.createQuery("SELECT c FROM Carrito c",Carrito.class);
            lc=(List<Carrito>) q.getResultList();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);

        }
        return lc;
    }
   @Override
    public boolean guardaCarrito(Carrito c) {
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
    public boolean borraCarrito(int id) {
        boolean borrado = false;
        try {
            Carrito c = null;
            c = em.find(Carrito.class, id);
            em.remove(c);
            borrado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return borrado;
    }
    @Override
    public boolean creaCarrito(Carrito c) {
        boolean creado = false;
        int i=0;
        boolean hayEspacio=false;
        while(i<100 || !hayEspacio){
            if(em.find(Carrito.class,c.getIdCarrito()+i)==null){
                hayEspacio=true;
                int idc=c.getIdCarrito()+i;
                c.setIdCarrito(idc);
                i=100;
            }else {
                i++;
            }
        }
        try {
            em.persist(c);
            creado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return creado;
    }
    @Override
    public Carrito buscaId(Integer id){
        Carrito c=null;
        try {
            c=em.find(Carrito.class, id);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);

        }
        return c;
    }

}

