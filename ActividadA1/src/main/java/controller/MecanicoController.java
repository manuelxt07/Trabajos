package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Mecanico;
import model.Taller;

import java.util.ArrayList;

public class MecanicoController {

    private Taller taller;

    @FXML private TextField txtNombre;
    @FXML private TextField txtEspecialidad;
    @FXML private TextField txtCodigo;

    @FXML private TableView<Mecanico> tablaMecanicos;
    @FXML private TableColumn<Mecanico, String> colNombre;
    @FXML private TableColumn<Mecanico, String> colEspecialidad;
    @FXML private TableColumn<Mecanico, String> colCodigo;

    public void setTaller(Taller taller) {
        this.taller = taller;
        cargarDatos();
    }

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEspecialidad.setCellValueFactory(new PropertyValueFactory<>("especialidad"));
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoInterno"));
    }

    @FXML
    private void registrarMecanico(ActionEvent event) {
        Mecanico nuevoMecanico = new Mecanico(
                txtNombre.getText(), taller, txtEspecialidad.getText(), txtCodigo.getText()
        );
        taller.registrarMecanico(nuevoMecanico);
        cargarDatos();

        txtNombre.clear();
        txtEspecialidad.clear();
        txtCodigo.clear();
    }

    private void cargarDatos() {
        ArrayList<Mecanico> mecanicos = new ArrayList<>();
        taller.getListaIndividuos().forEach(individuo -> {
            if (individuo instanceof Mecanico) {
                mecanicos.add((Mecanico) individuo);
            }
        });
        tablaMecanicos.setItems(FXCollections.observableArrayList(mecanicos));
    }
}