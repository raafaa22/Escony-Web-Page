package com.example.escony.controller;

import com.example.escony.model.Cliente;
import com.example.escony.model.Ropa;
import com.example.escony.model.dao.RopaDAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.example.escony.model.dao.RopaDAOMap;
import com.example.escony.qualifiers.DAOJPA;
import com.example.escony.qualifiers.DAOMap;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.logging.Logger;
@Named("ctrlRopa")
@ViewScoped


public class RopaController implements Serializable {
    @Inject
    HttpServletRequest request; //acceso al objeto request de la petición actual
    //...

    private Ropa ropa;
    @Inject
    //@DAOMap
   @DAOJPA
    private RopaDAO ropaDAO;

    @Inject
    FacesContext fc;


    public RopaController() {
    }

    @PostConstruct
    public void init() {
        ropa = new Ropa();
    }

    public List<Ropa> getRopaList() {
        return ropaDAO.buscaTodos();
    }

    public Ropa getRopa() {
        return ropa;
    }

    public void recupera() {
        ropa = ropaDAO.buscaId(ropa.getId());
        if (ropa == null) {
            fc.addMessage(null, new FacesMessage("La prenda indicada no existe"));
        }
    }

    public String crea() {
        ropaDAO.creaPrenda(ropa);
        //Post-Redirect-Get
        return "ropadetalle?faces-redirect=true&id=" + ropa.getId();
    }
    public String guarda(){
        ropaDAO.guardaPrenda(ropa);
        return "ropadetalle?faces-redirect=true&id=" + ropa.getId();
    }

    public String borra(Ropa prenda) {
        ropaDAO.borraPrenda(prenda.getId());
        return "/listado_ropa";
    }

}



