package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Bicicleta;
import model.Cliente;
import model.Taller;
import model.TipoBicicleta;

import java.util.ArrayList;

public class ClienteController {

    private Taller taller;

    @FXML private TextField txtNombre;
    @FXML private TextField txtIdentificacion;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtDireccion;

    @FXML private TextField txtMarca;
    @FXML private TextField txtColor;
    @FXML private TextField txtSerial;
    @FXML private TextField txtAnio;
    @FXML private ComboBox<TipoBicicleta> cbTipoBicicleta;

    @FXML private TableView<Cliente> tablaClientes;
    @FXML private TableColumn<Cliente, String> colNombre;
    @FXML private TableColumn<Cliente, String> colIdentificacion;
    @FXML private TableColumn<Cliente, String> colTelefono;

    public void setTaller(Taller taller) {
        this.taller = taller;
        cargarDatos();
    }

    @FXML
    public void initialize() {
        cbTipoBicicleta.getItems().setAll(TipoBicicleta.values());

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colIdentificacion.setCellValueFactory(new PropertyValueFactory<>("identificacion"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
    }

    @FXML
    private void registrarCliente(ActionEvent event) {
        if (txtNombre.getText().isBlank() || txtIdentificacion.getText().isBlank()) {
            mostrarAviso("El nombre y la identificación del cliente son obligatorios.");
            return;
        }
        if (txtMarca.getText().isBlank() || txtSerial.getText().isBlank() || cbTipoBicicleta.getValue() == null) {
            mostrarAviso("Marca, serial y tipo de bicicleta son obligatorios.");
            return;
        }

        int anio;
        try {
            anio = Integer.parseInt(txtAnio.getText());
        } catch (NumberFormatException e) {
            mostrarAviso("El año/antigüedad de la bicicleta debe ser un número entero.");
            return;
        }

        Cliente nuevoCliente = new Cliente(
                txtNombre.getText(), taller, txtIdentificacion.getText(),
                txtTelefono.getText(), txtDireccion.getText(), new ArrayList<>()
        );

        Bicicleta nuevaBici = new Bicicleta(
                txtMarca.getText(), txtColor.getText(), txtSerial.getText(),
                anio, cbTipoBicicleta.getValue(),
                nuevoCliente, taller
        );

        nuevoCliente.agregarBicicleta(nuevaBici);
        taller.registrarBicicleta(nuevaBici);
        taller.registrarCliente(nuevoCliente);

        cargarDatos();
        limpiarCampos();
    }

    private void cargarDatos() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        taller.getListaIndividuos().forEach(individuo -> {
            if (individuo instanceof Cliente) {
                clientes.add((Cliente) individuo);
            }
        });
        tablaClientes.setItems(FXCollections.observableArrayList(clientes));
    }

    private void mostrarAviso(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING, mensaje);
        alert.setHeaderText(null);
        alert.show();
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtIdentificacion.clear();
        txtTelefono.clear();
        txtDireccion.clear();
        txtMarca.clear();
        txtColor.clear();
        txtSerial.clear();
        txtAnio.clear();
        cbTipoBicicleta.getSelectionModel().clearSelection();
    }
}