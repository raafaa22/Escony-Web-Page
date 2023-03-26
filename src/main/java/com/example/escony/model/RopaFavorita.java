package com.example.escony.model;
import java.io.Serializable;
public class RopaFavorita {

        private String emailUsu;
        private String idRopa;

        public RopaFavorita() {
            this.emailUsu = emailUsu;
            this.idRopa = idRopa;
        }

    public RopaFavorita(RopaFavorita rf) {

    }

    public String getIdUsuario() {
        return emailUsu;
    }

        public void setIdUsuario( String idUsuario) {
            this.emailUsu = idUsuario;
        }

        public String getIdRopa() {
            return idRopa;
        }

        public void setIdRopa(String idRopa) {
            this.idRopa = idRopa;
        }
}








