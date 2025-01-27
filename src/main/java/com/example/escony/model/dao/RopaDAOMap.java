package com.example.escony.model.dao;

import com.example.escony.model.Ropa;
import com.example.escony.qualifiers.DAOMap;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@DAOMap
@ApplicationScoped
public class RopaDAOMap implements RopaDAO, Serializable {
    private Map<Integer, Ropa> ropa = null;
    private Integer id = 1;
    private final Logger logger = Logger.getLogger(RopaDAO.class.getName());

    public RopaDAOMap() {
        ropa = new HashMap<>();
        ropa.put(id, new Ropa("Camiseta", "XXl", 2.99, id));
        id++;
        ropa.put(id, new Ropa("Pantalones anchos", "M", 15.99, id));
        id++;
        ropa.put(id, new Ropa("Abrigo", "XS", 49.99, id));
    }


    public boolean borraPrenda(Integer idC) {
        boolean result = false;
        if (ropa.containsKey(idC)) {
            ropa.remove(idC);
            result = true;
        }
        return result;
    }

    @Override
    public Ropa buscaId(Integer id) {
        Ropa r = null;
        r = ropa.get(id);
        return r;
    }

    @Override
    public boolean creaPrenda(Ropa r) {
        Ropa nr = new Ropa(r);
        nr.setId(r.getId());
        ropa.put(r.getId(), nr);
        return true;
    }

    public List<Ropa> buscaTodos() {

        return ropa.values().stream().collect(Collectors.toList());
    }

    @Override
    public boolean guardaPrenda(Ropa r) {
        boolean result = false;
        if (ropa.containsKey(r.getId())) {
            Ropa nr = new Ropa(r);
            ropa.replace(r.getId(), nr);
            result = true;
        }
        return result;
    }
    public boolean anadeCarrito(Ropa r){
        return false;
    }
    public List<Ropa> buscaPorNombre(String nombre){
        return null;
    }


}


