package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class OrdenServicioController {

    private Taller taller;

    @FXML private DatePicker dpFecha;
    @FXML private TextArea txtMotivo;
    @FXML private TextArea txtDiagnostico;
    @FXML private TextField txtCostoTotal;

    @FXML private ComboBox<Cliente> cbCliente;
    @FXML private ComboBox<Bicicleta> cbBicicleta;
    @FXML private ComboBox<Mecanico> cbMecanico;

    public void setTaller(Taller taller) {
        this.taller = taller;
        cargarComboBoxes();
    }

    @FXML
    public void initialize() {
        // Listener para actualizar las bicicletas cuando se selecciona un cliente
        cbCliente.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                cbBicicleta.setItems(FXCollections.observableArrayList(newVal.getListaBicicletas()));
            }
        });
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
    }

    @FXML
    private void registrarOrden(ActionEvent event) {
        OrdenServicio orden = new OrdenServicio(
                dpFecha.getValue(), LocalTime.now(), txtMotivo.getText(),
                txtDiagnostico.getText(), "Pendiente",
                Double.parseDouble(txtCostoTotal.getText()), taller,
                cbBicicleta.getValue(), cbMecanico.getValue()
        );

        taller.registrarOrdenServico(orden);

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Orden de servicio registrada con éxito.");
        alert.show();
    }
}