package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import model.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrdenServicioController {

    private static final double COSTO_BASE_SIN_REPUESTOS = 2000;

    private Taller taller;

    @FXML private DatePicker dpFecha;
    @FXML private TextArea txtMotivo;
    @FXML private TextArea txtDiagnostico;
    @FXML private TextField txtCostoTotal;
    @FXML private ComboBox<Repuesto> cbRepuesto;
    @FXML private TextField txtCantidadRepuesto;
    @FXML private TableView<DetalleRepuesto> tablaRepuestosOrden;
    @FXML private TableColumn<DetalleRepuesto, String> colRepuestoNombre;
    @FXML private TableColumn<DetalleRepuesto, Integer> colRepuestoCantidad;
    @FXML private TableColumn<DetalleRepuesto, Double> colRepuestoSubtotal;
    private List<DetalleRepuesto> repuestosSeleccionados = new ArrayList<>();
    @FXML private ComboBox<Cliente> cbCliente;
    @FXML private ComboBox<Bicicleta> cbBicicleta;
    @FXML private ComboBox<Mecanico> cbMecanico;

    public void setTaller(Taller taller) {
        this.taller = taller;
        cargarComboBoxes();
    }

    @FXML
    public void initialize() {
        // Conversores: evitan depender de toString() y muestran algo legible en cada combo
        cbCliente.setConverter(new StringConverter<>() {
            @Override
            public String toString(Cliente c) {
                return c == null ? "" : c.getNombre() + " (" + c.getIdentificacion() + ")";
            }
            @Override
            public Cliente fromString(String s) { return null; }
        });

        cbBicicleta.setConverter(new StringConverter<>() {
            @Override
            public String toString(Bicicleta b) {
                return b == null ? "" : b.getMarca() + " - " + b.getSerial();
            }
            @Override
            public Bicicleta fromString(String s) { return null; }
        });

        cbMecanico.setConverter(new StringConverter<>() {
            @Override
            public String toString(Mecanico m) {
                return m == null ? "" : m.getNombre() + " (" + m.getEspecialidad() + ")";
            }
            @Override
            public Mecanico fromString(String s) { return null; }
        });

        cbRepuesto.setConverter(new StringConverter<>() {
            @Override
            public String toString(Repuesto r) {
                return r == null ? "" : r.getNombre() + " - stock: " + r.getCantidad();
            }
            @Override
            public Repuesto fromString(String s) { return null; }
        });

        // Al cambiar de cliente se recargan SUS bicicletas y se limpia cualquier seleccion anterior
        cbCliente.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            cbBicicleta.getSelectionModel().clearSelection();
            if (newVal != null && newVal.getListaBicicletas() != null && !newVal.getListaBicicletas().isEmpty()) {
                cbBicicleta.setItems(FXCollections.observableArrayList(newVal.getListaBicicletas()));
                cbBicicleta.setDisable(false);
            } else {
                cbBicicleta.setItems(FXCollections.observableArrayList());
                cbBicicleta.setDisable(true);
                if (newVal != null) {
                    mostrarAviso("Este cliente no tiene bicicletas registradas.");
                }
            }
        });

        colRepuestoNombre.setCellValueFactory(datos -> new javafx.beans.property.SimpleStringProperty(datos.getValue().getRepuesto().getNombre()));
        colRepuestoCantidad.setCellValueFactory(datos -> new javafx.beans.property.SimpleIntegerProperty(datos.getValue().getCantidadUsada()).asObject());
        colRepuestoSubtotal.setCellValueFactory(datos -> new javafx.beans.property.SimpleDoubleProperty(datos.getValue().calcularSubtotal()).asObject());

        // El costo total ahora se calcula solo, ya no se escribe a mano
        txtCostoTotal.setEditable(false);
        actualizarCostoTotal();
    }

    private void cargarComboBoxes() {
        List<Cliente> clientes = new ArrayList<>();
        List<Mecanico> mecanicos = new ArrayList<>();

        taller.getListaIndividuos().forEach(ind -> {
            if (ind instanceof Cliente) clientes.add((Cliente) ind);
            if (ind instanceof Mecanico) mecanicos.add((Mecanico) ind);
        });

        cbCliente.setItems(FXCollections.observableArrayList(clientes));
        cbMecanico.setItems(FXCollections.observableArrayList(mecanicos));
        cargarRepuestosDisponibles();

        cbBicicleta.setItems(FXCollections.observableArrayList());
        cbBicicleta.setDisable(true);
    }

    private void cargarRepuestosDisponibles() {
        List<Repuesto> disponibles = taller.getInventario().getListaRepuestos().stream()
                .filter(r -> r.getCantidad() > 0)
                .collect(Collectors.toList());
        cbRepuesto.setItems(FXCollections.observableArrayList(disponibles));
    }

    @FXML
    private void registrarOrden(ActionEvent event) {
        if (dpFecha.getValue() == null) {
            mostrarAviso("Selecciona una fecha para la orden.");
            return;
        }
        if (cbCliente.getValue() == null) {
            mostrarAviso("Selecciona un cliente.");
            return;
        }
        if (cbBicicleta.getValue() == null) {
            mostrarAviso("Selecciona la bicicleta del cliente.");
            return;
        }
        if (cbMecanico.getValue() == null) {
            mostrarAviso("Selecciona un mecánico.");
            return;
        }

        double costoTotal;
        try {
            costoTotal = Double.parseDouble(txtCostoTotal.getText());
        } catch (NumberFormatException e) {
            mostrarAviso("El costo total debe ser un número válido.");
            return;
        }

        OrdenServicio orden = new OrdenServicio(
                dpFecha.getValue(), LocalTime.now(), txtMotivo.getText(),
                txtDiagnostico.getText(), "Pendiente",
                costoTotal, taller,
                cbBicicleta.getValue(), cbMecanico.getValue()
        );

        taller.registrarOrdenServico(orden);

        for (DetalleRepuesto detalle : repuestosSeleccionados) {
            detalle.setOrdenServicio(orden);
            orden.agregarRepuestos(detalle);

            // Se descuenta del inventario lo que se usó en esta orden
            Repuesto repuesto = detalle.getRepuesto();
            repuesto.setCantidad(repuesto.getCantidad() - detalle.getCantidadUsada());
        }

        repuestosSeleccionados.clear();
        tablaRepuestosOrden.getItems().clear();
        cargarRepuestosDisponibles();
        limpiarFormulario();

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Orden de servicio registrada con éxito.");
        alert.show();
    }

    @FXML
    private void agregarRepuestoALista(ActionEvent event) {
        Repuesto repuestoSeleccionado = cbRepuesto.getValue();
        if (repuestoSeleccionado == null) {
            mostrarAviso("Selecciona un repuesto de la lista.");
            return;
        }

        int cantidad;
        try {
            cantidad = Integer.parseInt(txtCantidadRepuesto.getText());
        } catch (NumberFormatException e) {
            mostrarAviso("La cantidad debe ser un número entero.");
            return;
        }

        if (cantidad <= 0) {
            mostrarAviso("La cantidad debe ser mayor a cero.");
            return;
        }

        // Suma lo que ya se agregó de este mismo repuesto en la orden actual, para no exceder el stock real
        int yaReservado = repuestosSeleccionados.stream()
                .filter(d -> d.getRepuesto().equals(repuestoSeleccionado))
                .mapToInt(DetalleRepuesto::getCantidadUsada)
                .sum();

        if (yaReservado + cantidad > repuestoSeleccionado.getCantidad()) {
            mostrarAviso("No hay suficiente stock de " + repuestoSeleccionado.getNombre() +
                    ". Disponible: " + (repuestoSeleccionado.getCantidad() - yaReservado));
            return;
        }

        DetalleRepuesto detalle = new DetalleRepuesto(cantidad, repuestoSeleccionado.getCosto(), null, repuestoSeleccionado);
        repuestosSeleccionados.add(detalle);
        tablaRepuestosOrden.setItems(FXCollections.observableArrayList(repuestosSeleccionados));
        txtCantidadRepuesto.clear();
        actualizarCostoTotal();
    }

    /**
     * Suma el subtotal (cantidadUsada x costoUnitario) de cada repuesto agregado a la orden
     * y actualiza el campo de costo total con ese valor. Si no hay repuestos agregados,
     * se usa un costo base fijo de 2000.
     */
    private void actualizarCostoTotal() {
        double totalRepuestos = repuestosSeleccionados.stream()
                .mapToDouble(DetalleRepuesto::calcularSubtotal)
                .sum();
        double total = repuestosSeleccionados.isEmpty() ? COSTO_BASE_SIN_REPUESTOS : totalRepuestos;
        txtCostoTotal.setText(String.format(java.util.Locale.US, "%.2f", total));
    }

    private void mostrarAviso(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING, mensaje);
        alert.setHeaderText(null);
        alert.show();
    }

    private void limpiarFormulario() {
        dpFecha.setValue(null);
        txtMotivo.clear();
        txtDiagnostico.clear();
        actualizarCostoTotal();
        cbCliente.getSelectionModel().clearSelection();
        cbBicicleta.getSelectionModel().clearSelection();
        cbMecanico.getSelectionModel().clearSelection();
    }
}