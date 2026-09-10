package model;


import java.util.ArrayList;
import java.util.List;

    public class Cliente extends Individuo {

        private String identificacion;
        private String telefono;
        private String direccion;
        private List<Bicicleta> listaBicicletas;

        public Cliente(String nombre, Taller ownedByTaller, String identificacion, String telefono, String direccion, List<Bicicleta> listaBicicletas) {
            super(nombre, ownedByTaller);
            this.identificacion = identificacion;
            this.telefono = telefono;
            this.direccion = direccion;
            this.listaBicicletas = listaBicicletas;
        }


        public String getIdentificacion() {
            return identificacion;
        }

        public void setIdentificacion(String identificacion) {
            this.identificacion = identificacion;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        public List<Bicicleta> getListaBicicletas() {
            return listaBicicletas;
        }

        @Override
        public String toString() {
            return "Cliente{" +
                    "nombre='" + getNombre() + '\'' +
                    ", identificacion='" + identificacion + '\'' +
                    ", telefono='" + telefono + '\'' +
                    ", direccion='" + direccion + '\'' +
                    ", bicicletas=" + listaBicicletas.size() +
                    '}';
        }



        public void agregarBicicleta(Bicicleta bicicleta) {
            if (bicicleta != null && !listaBicicletas.contains(bicicleta)) {
                listaBicicletas.add(bicicleta);
            }
        }

        public void eliminarBicicleta(Bicicleta bicicleta) {
            listaBicicletas.remove(bicicleta);
        }


    }

