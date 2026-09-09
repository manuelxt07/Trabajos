package model;

import java.util.ArrayList;
import java.util.List;

public class Taller {
    private String nombre;
    private List<OrdenServicio> listaOrdenes;
    private List <Individuo> listaIndividuos;
    private Inventario inventario;

    public Taller(String nombre) {
        this.nombre = nombre;
        this.listaOrdenes = new ArrayList<>();
        this.listaIndividuos = new ArrayList<>();
        this.inventario = new Inventario(this);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<OrdenServicio> getListaOrdenes() {
        return listaOrdenes;
    }

    public void setListaOrdenes(List<OrdenServicio> listaOrdenes) {
        this.listaOrdenes = listaOrdenes;
    }

    public List<Individuo> getListaIndividuos() {
        return listaIndividuos;
    }

    public void setListaIndividuos(List<Individuo> listaIndividuos) {
        this.listaIndividuos = listaIndividuos;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    @Override
    public String toString() {
        return "Taller{" +
                "nombre='" + nombre + '\'' +
                ", listaOrdenes=" + listaOrdenes +
                ", listaIndividuos=" + listaIndividuos +
                ", inventario=" + inventario +
                '}';
    }

    // Metodos

    /**
     * Metodo que registra Clientes
     * @param cliente
     */
    public void registrarCliente (Cliente cliente){
        listaIndividuos.add(cliente);
    }

    /**
     * Metodo que registra Mecanicos
     * @param mecanico
     */
    public void registrarMecanico (Mecanico mecanico){
        listaIndividuos.add(mecanico);
    }

    /**
     * Metodo que registra Ordenes de Servicio
     * @param ordenServicio
     */
    public void registrarOrdenServico (OrdenServicio ordenServicio){
        listaOrdenes.add(ordenServicio);
    }

}
