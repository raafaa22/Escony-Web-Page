package com.example.escony.model;
import java.io.Serializable;
public class RopaFavorita {

        private String emailUsu;
        private Integer idRopa;

        public RopaFavorita(){
            emailUsu="";
            idRopa=0;
        }

        public RopaFavorita(String emailUsu,Integer idRopa) {
            this.emailUsu = emailUsu;
            this.idRopa = idRopa;
        }

    public RopaFavorita(RopaFavorita rf) {
        this.emailUsu =rf.emailUsu;
        this.idRopa =rf.idRopa;
    }

    public String getIdUsuario() {
        return emailUsu;
    }

        public void setIdUsuario( String idUsuario) {
            this.emailUsu = idUsuario;
        }

        public Integer getIdRopa() {
            return idRopa;
        }

        public void setIdRopa(Integer idRopa) {
            this.idRopa = idRopa;
        }
}








