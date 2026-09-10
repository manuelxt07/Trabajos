package model;

import java.util.ArrayList;
import java.util.List;

public class Repuesto {
    private String nombre;
    private double costo;
    private int cantidad;
    private int cantidadMinima;
    private Taller ownedByTaller;
    private List<DetalleRepuesto> repuesto;

    public Repuesto(String nombre, double costo, int cantidad, int cantidadMinima, Taller ownedByTaller) {
        this.nombre = nombre;
        this.costo = costo;
        this.cantidad = cantidad;
        this.cantidadMinima = cantidadMinima;
        this.ownedByTaller = ownedByTaller;
        this.repuesto = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidadMinima() {
        return cantidadMinima;
    }

    public void setCantidadMinima(int cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
    }

    public Taller getOwnedByTaller() {
        return ownedByTaller;
    }

    public void setOwnedByTaller(Taller ownedByTaller) {
        this.ownedByTaller = ownedByTaller;
    }

    public List<DetalleRepuesto> getRepuesto() {
        return repuesto;
    }

    public void setRepuesto(List<DetalleRepuesto> repuesto) {
        this.repuesto = repuesto;
    }

    @Override
    public String toString() {
        return "Repuesto{" +
                "nombre='" + nombre + '\'' +
                ", costo=" + costo +
                ", cantidad=" + cantidad +
                ", cantidadMinima=" + cantidadMinima +
                ", ownedByTaller=" + ownedByTaller +
                ", repuesto=" + repuesto +
                '}';
    }

    public String calcularCostoTotal () {
        double costoTotal = costo*cantidad;
        return "El costo total del repuesto es " + costoTotal;
    }
}
