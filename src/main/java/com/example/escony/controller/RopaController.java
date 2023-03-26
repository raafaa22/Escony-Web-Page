package com.example.escony.controller;

import com.example.escony.model.Ropa;
import com.example.escony.model.dao.RopaDAO;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;


import java.util.logging.Logger;
@Named("ctrlRopa")
@ViewScoped


public class RopaController {
    @Inject
    private Ropa ropa;
    private RopaDAO ropaDAO;
    private List<Ropa> ropaList;

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
