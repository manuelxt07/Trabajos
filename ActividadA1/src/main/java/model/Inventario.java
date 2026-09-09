package model;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private Taller ownedByTaller;
    private List<Repuesto> listaRepuestos;

    public Inventario(Taller ownedByTaller) {
        this.ownedByTaller = ownedByTaller;
        this.listaRepuestos = new ArrayList<>();
    }

    public Taller getOwnedByTaller() {
        return ownedByTaller;
    }

    public void setOwnedByTaller(Taller ownedByTaller) {
        this.ownedByTaller = ownedByTaller;
    }

    public List<Repuesto> getListaRepuestos() {
        return listaRepuestos;
    }

    public void setListaRepuestos(List<Repuesto> listaRepuestos) {
        listaRepuestos = listaRepuestos;
    }
    /**
     * Metodo que registra Repuestos al Inventario
     * @param repuesto
     */
    public void registrarRepuesto (Repuesto repuesto){
        listaRepuestos.add(repuesto);
    }
    /**
     * Metodo para obtener las alertas de Stock minimo cuando se llegue a la cantidad minima de un repuesto
     * @return
     */
    public List<Repuesto> obtenerAlertasStock() {
        List<Repuesto> alertas = new ArrayList<>();
        for (Repuesto repuesto : listaRepuestos) {
            if (repuesto.getCantidad() <= repuesto.getCantidadMinima()) {
                alertas.add(repuesto);
            }
        }
        return alertas;
    }
}
