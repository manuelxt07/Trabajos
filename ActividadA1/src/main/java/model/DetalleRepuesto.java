package model;

public class DetalleRepuesto {
    private int cantidadUsada;
    private double costoUnitario;
    private OrdenServicio ordenServicio;
    private Repuesto repuesto;

    public DetalleRepuesto(int cantidadUsada, double costoUnitario, OrdenServicio ordenServicio, Repuesto repuesto) {
        this.cantidadUsada = cantidadUsada;
        this.costoUnitario = costoUnitario;
        this.ordenServicio = ordenServicio;
        this.repuesto = repuesto;
    }

    public int getCantidadUsada() {
        return cantidadUsada;
    }

    public void setCantidadUsada(int cantidadUsada) {
        this.cantidadUsada = cantidadUsada;
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public OrdenServicio getOrdenServicio() {
        return ordenServicio;
    }

    public void setOrdenServicio(OrdenServicio ordenServicio) {
        this.ordenServicio = ordenServicio;
    }

    public Repuesto getRepuesto() {
        return repuesto;
    }

    public void setRepuesto(Repuesto repuesto) {
        this.repuesto = repuesto;
    }

    @Override
    public String toString() {
        return "DetalleRepuesto{" +
                "cantidadUsada=" + cantidadUsada +
                ", costoUnitario=" + costoUnitario +
                ", ordenServicio=" + ordenServicio +
                ", repuesto=" + repuesto +
                '}';
    }

    //Metodos

    /**
     * Metodo para calcular el subtotal de un repuesto en la orden
     * @return El costo total por la cantidad de repuestos usados
     */
    public double calcularSubtotal() {
        return this.cantidadUsada * this.costoUnitario;
    }
}
