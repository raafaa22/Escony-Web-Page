package com.example.escony.model;
import java.io.Serializable;
public class RopaFavorita implements Serializable{

        private Cliente idUsuario;
        private Ropa idRopa;

        public RopaFavorita(Cliente idUsuario, Ropa idRopa) {
            this.idUsuario = idUsuario;
            this.idRopa = idRopa;
        }

    public RopaFavorita(RopaFavorita rf) {

    }

        public Cliente getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(Cliente idUsuario) {
            this.idUsuario = idUsuario;
        }

        public String getIdRopa() {
            return idRopa;
        }

        public void setIdRopa(String idRopa) {
            this.idRopa = idRopa;
        }
    }








