package com.example.escony.controller;

import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.SecurityContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;


import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
@ViewScoped
@Named
public class RopaLoginController implements Serializable {
    private static final Logger logger = Logger.getLogger(RopaLoginController.class.getName());

    @Inject
    FacesContext fc;

    //SecurityContext and ExternalContext needed for programatic authentication
    @Inject
    SecurityContext sc;

    @Inject
    ExternalContext ec;

    @Inject
    HttpServletRequest request; //needed for logout
    //Sample logout action for Form/CustomForm Authentication
    public String logout() throws ServletException {
        request.logout();
        request.getSession().invalidate();
        //ec.invalidateSession();
        return "/index?faces-redirect=true";

    }

}
