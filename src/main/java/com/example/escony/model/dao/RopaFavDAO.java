package com.example.escony.model.dao;

import com.example.escony.model.RopaFavorita;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@ApplicationScoped
public class RopaFavDAO {
    private Map<String, RopaFavorita> ropafav=null;
    public RopaFavDAO() {
        if (ropafav == null) {
            ropafav = new HashMap<>();
            ropafav.put("rafa@email.com", new RopaFavorita());
        }
    }
    public RopaFavorita buscaEmail(String email) {
        return new RopaFavorita(ropafav.get(email));
    }
    public  List<RopaFavorita> buscaTodas() {
        return ropafav.values().stream().collect(Collectors.toList());
    }
    public int numropafav() {
        return ropafav.size();
    }

    public boolean crearopafav(RopaFavorita rf) {
        RopaFavorita nrf=new RopaFavorita(rf);
        ropafav.put(rf.getIdUsuario(), nrf);
        return true;
    }


    public boolean guardaRopaFav(RopaFavorita rf) {
        boolean result=false;
        if (ropafav.containsKey(rf.getIdRopa())) {
            RopaFavorita nrf=new RopaFavorita(rf);
            ropafav.replace(rf.getIdRopa(),nrf);
            result=true;
        }
        return result;
    }


    public boolean borraRopafav(String email) {
        boolean result=false;
        if (ropafav.containsKey(email)) {
            ropafav.remove(email);
            result = true;
        }
        return result;
    }
    public List<RopaFavorita> buscaTodasFav(){
            return ropafav.values().stream().collect(Collectors.toList());
    }
}
