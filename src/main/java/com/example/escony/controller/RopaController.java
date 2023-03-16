package com.example.escony.controller;
import com.example.escony.model.Ropa;

import java.util.ArrayList;
import java.util.List;
public class RopaController {
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
}
