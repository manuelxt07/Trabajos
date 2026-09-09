package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class OrdenServicio {
    private LocalDate fecha;
    private LocalTime hora;
    private String motivo;
    private String diagnostico;
    private String trabajosRealizados;
    private double costoTotal;
    private Taller ownedByTaller;
    private List<DetalleRepuesto> listaRepuestos;
    private Mecanico mecanico;
    private Bicicleta bicicleta;

    public OrdenServicio(LocalDate fecha, LocalTime hora, String motivo, String diagnostico, String trabajosRealizados, double costoTotal, Taller ownedByTaller, Bicicleta bicicleta, Mecanico mecanico) {
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.trabajosRealizados = trabajosRealizados;
        this.costoTotal = costoTotal;
        this.ownedByTaller = ownedByTaller;
        this.listaRepuestos = new ArrayList<>();
        this.mecanico = mecanico;
        this.bicicleta = bicicleta;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTrabajosRealizados() {
        return trabajosRealizados;
    }

    public void setTrabajosRealizados(String trabajosRealizados) {
        this.trabajosRealizados = trabajosRealizados;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public Taller getOwnedByTaller() {
        return ownedByTaller;
    }

    public void setOwnedByTaller(Taller ownedByTaller) {
        this.ownedByTaller = ownedByTaller;
    }

    public List<DetalleRepuesto> getListaRepuestos() {
        return listaRepuestos;
    }

    public void setListaRepuestos(List<DetalleRepuesto> listaRepuestos) {
        this.listaRepuestos = listaRepuestos;
    }

    public Mecanico getMecanico() {
        return mecanico;
    }

    public void setMecanico(Mecanico mecanico) {
        this.mecanico = mecanico;
    }

    public Bicicleta getBicicleta() {
        return bicicleta;
    }

    public void setBicicleta(Bicicleta bicicleta) {
        this.bicicleta = bicicleta;
    }

    @Override
    public String toString() {
        return "OrdenServicio{" +
                "fecha=" + fecha +
                ", hora=" + hora +
                ", motivo='" + motivo + '\'' +
                ", diagnostico='" + diagnostico + '\'' +
                ", trabajosRealizados='" + trabajosRealizados + '\'' +
                ", costoTotal=" + costoTotal +
                ", ownedByTaller=" + ownedByTaller +
                ", listaRepuestos=" + listaRepuestos +
                ", mecanico=" + mecanico +
                ", bicicleta=" + bicicleta +
                '}';
    }

    // Metodos

    /**
     * Metodo para añadir repuestos a la Orden de Servicio
     * @param nuevo
     * @return
     */
    public String agregarRepuestos (DetalleRepuesto nuevo){
        listaRepuestos.add(nuevo);
        return "Repuesto añadido correctamente";
    }
}
