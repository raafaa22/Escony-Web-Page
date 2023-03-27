package com.example.escony.model.dao;


import com.example.escony.model.Cliente;
import com.example.escony.model.Ropa;
import com.example.escony.model.RopaFavorita;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class RopaDAO{
    private Map<Integer, Ropa> ropa =null;
    private  Integer id = 1;
    private final Logger logger = Logger.getLogger(RopaDAO.class.getName());
    private EntityManager em;
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

    public Ropa buscaId(Integer id) {
       Ropa r=null;
        try {
            r=em.find(Ropa.class, id);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);

        }
        return r;
    }
}




