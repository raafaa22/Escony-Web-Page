package com.example.escony.controller;

import com.example.escony.model.Carrito;
import com.example.escony.model.Ropa;
import com.example.escony.model.dao.CarritoDAO;

import com.example.escony.qualifiers.DAOJPA;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;

import java.io.Serializable;
import java.util.List;

@Named(value="ctrlCarrito")
@ViewScoped
public class CarritoController implements Serializable {


    HttpServletRequest request; 
    private Carrito carrito;
    @Inject
    //@DAOMap
    @DAOJPA
    private CarritoDAO carritoDAO;

    @Inject
    FacesContext fc;
    public CarritoController() {
    }

    @PostConstruct
    public void init() {
        carrito = new Carrito();
    }
    public List<Carrito> getCarritoList(){
        return carritoDAO.buscaTodos();
    }

    public List<Ropa> getPrendasCliente(String email) {
        return carritoDAO.buscaPorEmail(email);
    }
    public List<Carrito> getCarritosPrenda(int idPrenda){
        return carritoDAO.buscaPorIdPrenda(idPrenda);
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public Carrito recupera() {
        carrito = carritoDAO.buscaId(carrito.getIdCarrito()) ;
        if (carrito == null) {
            fc.addMessage(null, new FacesMessage("El carrito esta vacio"));
        }
        return carrito;
    }

    public String crea(String email,int id) {
        Carrito carro=new Carrito(id+100,id,email);
        carritoDAO.creaCarrito(carro);
        //Post-Redirect-Get
        return "";
    }
    public String guarda(){
        carritoDAO.guardaCarrito(carrito);
        return "ropadetalle?faces-redirect=true&id=" + carrito.getIdCarrito();
    }

    public String borra(int idP,String email) {
        Carrito borrar=carritoDAO.buscaPorPrendaEmail(idP,email);
            carritoDAO.borraCarrito(borrar.getIdCarrito());
        return "";
    }


}
