package com.example.escony.controller;

import com.example.escony.model.Carrito;
import com.example.escony.model.Cliente;
import com.example.escony.model.Ropa;
import com.example.escony.model.dao.ClienteDAO;

import com.example.escony.model.dao.ClienteDAOMap;
import com.example.escony.qualifiers.DAOJPA;
import com.example.escony.qualifiers.DAOMap;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Named(value = "clienteCtrl")
@ViewScoped
public class ClienteController implements Serializable{

    @Inject
    //@DAOMap
   @DAOJPA
    private ClienteDAO clienteDAO;


    @Inject
    FacesContext fc;

    //View-Model

    private Cliente cliente;

    public ClienteController() {
    }

    @PostConstruct
    public void init() {

        cliente = new Cliente();
    }
    public List<Cliente> getClientes() {
        return clienteDAO.buscaTodos();
    }

    public Cliente getCliente() {
        return cliente;
    }

    //ACTIONS for visualiza, crea, edit and borra views
    /**
     * Get client from id param
     */
    public void recupera() {
        cliente = clienteDAO.buscaEmail(cliente.getEmail());
        if (cliente == null) {
            fc.addMessage(null, new FacesMessage("El cliente indicado no existe"));
        }
    }

    /**
     * Create a new Client from model data
     */
    public String crea() {
        cliente.setEmail(cliente.getEmail());
        cliente.setRol("USUARIO");
        clienteDAO.creaCliente(cliente);
        //Post-Redirect-Get
        return "listado_clientes?faces-redirect=true&id=" + cliente.getEmail();
    }

  
    /**
     * Update current model client to DAO
     */
    public String guarda() {
//      Programatic validation
//        if (valida(c.getDni())==false) {
//            fc.addMessage("formCliente:idDNI", new FacesMessage("La letra no coincide con el DNI"));
//            return ""; //Stay on view to correct error
//        }
        clienteDAO.guardaCliente(cliente);
        return "listado_clientes?faces-redirect=true&id=" + cliente.getEmail();
    }

    /**
     * Delete current model data client
     */
    public String borra() {
        clienteDAO.borraCliente(cliente.getEmail());
        return "";
    }

    //ACTIONS for listado.xhtml view
    public String borra(Cliente cliente) {
        clienteDAO.borraCliente(cliente.getEmail());
        return "";
    }

    //ACTIONS for listado_din.xhtml view

    /**
     * Set current client from datatable for in-line edition
     */
    public void editRow(Cliente cliente) {

        this.cliente=cliente;
    }

    /**
     * Cancel row edit
     */
    public void cancelEditRow() {
        this.cliente = new Cliente();
    }

    /**
     * Update current client
     */
    public void actualizaCliente() {

        clienteDAO.guardaCliente(cliente);

        cancelEditRow();
    }

}
