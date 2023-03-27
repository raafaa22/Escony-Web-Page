package com.example.escony.controller;

import com.example.escony.model.Cliente;
import com.example.escony.model.Ropa;
import com.example.escony.model.dao.RopaDAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;


import java.util.logging.Logger;
@Named("ctrlRopa")
@ViewScoped


public class RopaController implements Serializable{

    private Ropa ropa;
    @Inject
    private RopaDAO ropaDAO;
    private List<Ropa> ropaList;
    private final Logger logger = Logger.getLogger(RopaDAO.class.getName());


    public RopaController() {
        this.ropaList = new ArrayList<>();
    }

    public void agregarRopa(Ropa ropa) {
        ropaList.add(ropa);
    }

    public void actualizarRopa(int index, Ropa ropa) {
        ropaList.set(index, ropa);
    }

    public void eliminarRopa(int index) {
        ropaList.remove(index);
    }

    public List<Ropa> getRopaList() {
        return ropaList;
    }
    public void recupera() {
        logger.info("Recuperando articulo "+ropa.getId());
        ropa = ropaDAO. buscaId( ropa.getId());
    }
    public String crea() {
        ropa.setId(0);
        ropaDAO.creaPrenda(ropa);
        return "ropaalta?faces-redirect=true&id=" + ropa.getId();
    }

    @PostConstruct
    public void init() {
        //init  model-view
        ropa = new Ropa();
    }
    public List<Ropa> getRopaTodos() {
        return ropaDAO.buscaTodos();
    }

 public Ropa getRopa() {
        return ropa;
    }
}
