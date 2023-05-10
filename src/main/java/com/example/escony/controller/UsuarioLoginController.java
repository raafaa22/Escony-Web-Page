package com.example.escony.controller;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@ViewScoped
@Named(value = "usuLoginCtrl")
public class UsuarioLoginController implements Serializable {
    private static final Logger logger = Logger.getLogger(UsuarioLoginController.class.getName());

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
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        String view="";

        //Prepare data for programatic authentication
        AuthenticationParameters ap = new AuthenticationParameters();
        Credential credentials= new UsernamePasswordCredential(email,password);

        ap.credential(credentials).newAuthentication(true);

        HttpServletResponse response=(HttpServletResponse)ec.getResponse();

        //Programatic authentication
        if (sc.authenticate(request, response, ap) == AuthenticationStatus.SUCCESS) {
            view="index?faces-redirect=true";
            logger.log(Level.INFO,"Usuario autenticado");
        } else {
            fc.addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de autenticación", ""));
            logger.log(Level.WARNING,"Error de autenticación");
        }

        return view;
    }
    public String logout() throws ServletException {
        request.logout();
        request.getSession().invalidate();
        //ec.invalidateSession();
        return "/index?faces-redirect=true";

    }

}
