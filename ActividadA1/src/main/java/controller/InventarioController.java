package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Repuesto;
import model.Taller;

import java.util.List;

public class InventarioController {

    private Taller taller;

    @FXML private TextField txtNombre;
    @FXML private TextField txtCosto;
    @FXML private TextField txtCantidad;
    @FXML private TextField txtCantidadMinima;

    @FXML private TableView<Repuesto> tablaInventario;
    @FXML private TableColumn<Repuesto, String> colNombre;
    @FXML private TableColumn<Repuesto, Double> colCosto;
    @FXML private TableColumn<Repuesto, Integer> colCantidad;
    @FXML private TableColumn<Repuesto, Integer> colMinimo;

    public void setTaller(Taller taller) {
        this.taller = taller;
        cargarDatos();
        verificarAlertas();
    }

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCosto.setCellValueFactory(new PropertyValueFactory<>("costo"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colMinimo.setCellValueFactory(new PropertyValueFactory<>("cantidadMinima"));
    }

    @FXML
    private void registrarRepuesto(ActionEvent event) {
        Repuesto repuesto = new Repuesto(
                txtNombre.getText(), Double.parseDouble(txtCosto.getText()), //[cite: 8]
                Integer.parseInt(txtCantidad.getText()), Integer.parseInt(txtCantidadMinima.getText()), taller
        );

        taller.getInventario().registrarRepuesto(repuesto);

        cargarDatos();
        verificarAlertas();

        txtNombre.clear();
        txtCosto.clear();
        txtCantidad.clear();
        txtCantidadMinima.clear();
    }

    private void cargarDatos() {
        tablaInventario.setItems(FXCollections.observableArrayList(taller.getInventario().getListaRepuestos()));
    }

    private void verificarAlertas() {
        List<Repuesto> alertas = taller.getInventario().obtenerAlertasStock();
        StringBuilder mensaje = new StringBuilder("Repuestos con stock bajo:\n");
        for (Repuesto r : alertas) {
            mensaje.append("- ").append(r.getNombre()).append(" (Actual: ").append(r.getCantidad()).append(")\n");
        }

        Alert alert = new Alert(Alert.AlertType.WARNING, mensaje.toString());
        alert.setHeaderText("Alerta de Inventario");
        alert.show();
    }
}
