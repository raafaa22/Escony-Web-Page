package com.example.escony.model;

public class RopaFavorita {

        private Cliente idUsuario;
        private Ropa idRopa;

        public RopaFavorita(Cliente idUsuario, Ropa idRopa) {
            this.idUsuario = idUsuario;
            this.idRopa = idRopa;
        }

        public Cliente getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(Cliente idUsuario) {
            this.idUsuario = idUsuario;
        }

        public Ropa getIdRopa() {
            return idRopa;
        }

        public void setIdRopa(Ropa idRopa) {
            this.idRopa = idRopa;
        }
    }








