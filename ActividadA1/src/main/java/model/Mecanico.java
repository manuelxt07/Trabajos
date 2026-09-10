package model;


public class Mecanico extends Individuo {

    private String especialidad;
    private String codigoInterno;


    public Mecanico(String nombre, Taller ownedByTaller, String especialidad, String codigoInterno) {
        super(nombre, ownedByTaller);
        this.especialidad = especialidad;
        this.codigoInterno = codigoInterno;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getCodigoInterno() {
        return codigoInterno;
    }

    public void setCodigoInterno(String codigoInterno) {
        this.codigoInterno = codigoInterno;
    }

    @Override
        public String toString() {
            return "Mecanico{" +
                    "nombre='" + getNombre() + '\'' +
                    ", especialidad='" + especialidad + '\'' +
                    ", codigoInterno='" + codigoInterno + '\'' +
                    '}';
    }
}

