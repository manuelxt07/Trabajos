package model;

    public abstract class Individuo {

        private String nombre;
        private Taller ownedByTaller;

        public Individuo(String nombre, Taller ownedByTaller) {
            this.nombre = nombre;
            this.ownedByTaller = ownedByTaller;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public Taller getOwnedByTaller() {
            return ownedByTaller;
        }

        public void setOwnedByTaller(Taller ownedByTaller) {
            this.ownedByTaller = ownedByTaller;
        }

        @Override
        public String toString() {
            return "Individuo{" +
                    "nombre='" + nombre + '\'' +
                    ", ownedByTaller=" + ownedByTaller +
                    '}';
        }
    }

