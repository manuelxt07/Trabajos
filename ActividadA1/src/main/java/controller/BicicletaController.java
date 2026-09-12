package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Bicicleta;
import model.OrdenServicio;
import model.Taller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class BicicletaController {

    @FXML private TableView<Bicicleta> tablaBicicletas;
    @FXML private TableColumn<Bicicleta, String> colSerial;
    @FXML private TableColumn<Bicicleta, String> colMarca;
    @FXML private TableColumn<Bicicleta, String> colColor;

    @FXML private TableView<OrdenServicio> tablaHistorial;
    @FXML private TableColumn<OrdenServicio, LocalDate> colFecha;
    @FXML private TableColumn<OrdenServicio, String> colMotivo;
    @FXML private TableColumn<OrdenServicio, String> colDiagnostico;
    @FXML private TableColumn<OrdenServicio, String> colMecanico;
    @FXML private Label lblHistorialTitulo;

    private Taller tallerPrincipal;

    @FXML
    public void initialize() {
        // Mapeo columnas bicicletas
        colSerial.setCellValueFactory(new PropertyValueFactory<>("serial"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("color"));

        // Mapeo columnas historial
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colMotivo.setCellValueFactory(new PropertyValueFactory<>("motivo"));
        colDiagnostico.setCellValueFactory(new PropertyValueFactory<>("diagnostico"));
        colMecanico.setCellValueFactory(new PropertyValueFactory<>("mecanico"));

        // Escuchador de clic en la tabla de bicicletas
        tablaBicicletas.getSelectionModel().selectedItemProperty().addListener((obs, viejaSeleccion, nuevaSeleccion) -> {
            if (nuevaSeleccion != null) {
                cargarHistorialBicicleta(nuevaSeleccion);
            }
        });
    }

    public void setTallerPrincipal(Taller taller) {
        this.tallerPrincipal = taller;
        if (tallerPrincipal != null) {
            tablaBicicletas.setItems(FXCollections.observableArrayList(tallerPrincipal.getListaBicicletas()));
        }
    }

    private void cargarHistorialBicicleta(Bicicleta bicicleta) {
        lblHistorialTitulo.setText("Historial de: " + bicicleta.getMarca() + " (" + bicicleta.getSerial() + ")");

        List<OrdenServicio> historial = tallerPrincipal.getListaOrdenes().stream()
                .filter(orden -> orden.getBicicleta().equals(bicicleta))
                .collect(Collectors.toList());

        tablaHistorial.setItems(FXCollections.observableArrayList(historial));
    }
}