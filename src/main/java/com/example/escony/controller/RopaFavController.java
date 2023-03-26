package com.example.escony.controller;
import com.example.escony.model.RopaFavorita;
import com.example.escony.model.dao.RopaFavDAO;

import java.io.Serializable;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
@Named("ctrlRopafav")
@ViewScoped


public class RopaFavController implements Serializable{
    @Inject
    private RopaFavorita ropafav;
    @Inject
    private RopaFavDAO ropaFavDAO;
    private Map<Integer, List<Integer>> ropaFavoritaMap;

    public RopaFavController() {
        ropaFavoritaMap = new HashMap<>();
    }

    public void agregarRopaFavorita(int idUsuario, int idRopa) {
        // Si el usuario ya tiene una lista de ropa favorita, agregamos el ID de la prenda a esa lista
        if (ropaFavoritaMap.containsKey(idUsuario)) {
            List<Integer> listaRopaFavorita = ropaFavoritaMap.get(idUsuario);
            listaRopaFavorita.add(idRopa);
        } else { // Si el usuario no tiene lista de ropa favorita, creamos una nueva lista y agregamos el ID de la prenda
            List<Integer> listaRopaFavorita = new ArrayList<>();
            listaRopaFavorita.add(idRopa);
            ropaFavoritaMap.put(idUsuario, listaRopaFavorita);
        }
    }

    public void eliminarRopaFavorita(int idUsuario, int idRopa) {
        // Verificamos si el usuario tiene una lista de ropa favorita
        if (ropaFavoritaMap.containsKey(idUsuario)) {
            List<Integer> listaRopaFavorita = ropaFavoritaMap.get(idUsuario);
            // Buscamos el ID de la prenda en la lista y lo eliminamos si lo encontramos
            for (int i = 0; i < listaRopaFavorita.size(); i++) {
                if (listaRopaFavorita.get(i) == idRopa) {
                    listaRopaFavorita.remove(i);
                    break;
                }
            }
        }
    }

    public List<Integer> obtenerRopaFavorita(int idUsuario) {
        // Devolvemos la lista de ropa favorita del usuario, o una lista vacía si no tiene ropa favorita
        if (ropaFavoritaMap.containsKey(idUsuario)) {
            return ropaFavoritaMap.get(idUsuario);
        } else {
            return new ArrayList<>();
        }
    }

    @PostConstruct
    public void init() {
        ropafav = new RopaFavorita();
    }
    public List<RopaFavorita> getPrendasFav() {
        return ropaFavDAO.buscaTodasFav();
    }

    public RopaFavorita getRopafav() {
        return ropafav;
    }
}
