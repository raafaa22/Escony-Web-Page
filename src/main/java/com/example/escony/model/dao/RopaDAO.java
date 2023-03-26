package com.example.escony.model.dao;


import com.example.escony.model.Cliente;
import com.example.escony.model.Ropa;
import com.example.escony.model.RopaFavorita;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RopaDAO{
    private Map<Integer, Ropa> ropa =null;
    private  Integer id = 1;
    public RopaDAO() {
        if (ropa == null) {
            ropa = new HashMap<>();
            ropa.put(id, new Ropa("Camiseta","XXl",2.99,id++));
        }
    }
    public boolean creaPrenda(Ropa r) {
        Ropa nr=new Ropa(r);
        nr.setId(id);
        ropa.put(id, nr);
        id++;
        return true;
    }


    public boolean guardaPrenda(Ropa r) {
        boolean result=false;
        if (ropa.containsKey(r.getId())) {
            Ropa nr=new Ropa(r);
            ropa.replace(r.getId(),nr);
            result=true;
        }
        return result;
    }


    public boolean borraPrenda(Integer idC) {
        boolean result=false;
        if (ropa.containsKey(idC)) {
            ropa.remove(idC);
            result = true;
        }
        return result;
    }

    public List<Ropa> buscaTodos() {
        return ropa.values().stream().collect(Collectors.toList());
    }
}




