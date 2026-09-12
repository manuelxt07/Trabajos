package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Taller;

import java.io.IOException;

public class MainController {

    private Taller tallerPrincipal;

    @FXML
    public void initialize() {
        tallerPrincipal = new Taller("Taller FixIt");
    }

    @FXML
    private void abrirGestionClientes(ActionEvent event) {
        abrirVentana("/view/ClienteView.fxml", "Gestión de Clientes");
    }

    @FXML
    private void abrirGestionMecanicos(ActionEvent event) {
        abrirVentana("/view/MecanicoView.fxml", "Gestión de Mecánicos");
    }

    @FXML
    private void abrirGestionOrdenes(ActionEvent event) {
        abrirVentana("/view/OrdenServicioView.fxml", "Órdenes de Servicio");
    }

    @FXML
    private void abrirInventario(ActionEvent event) {
        abrirVentana("/view/InventarioView.fxml", "Inventario de Repuestos");
    }

    private void abrirVentana(String rutaFxml, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFxml));
            Parent root = loader.load();

            Object controlador = loader.getController();
            if (controlador instanceof ClienteController) {
                ((ClienteController) controlador).setTaller(tallerPrincipal);
            } else if (controlador instanceof OrdenServicioController) {
                ((OrdenServicioController) controlador).setTaller(tallerPrincipal);
            } else if (controlador instanceof InventarioController) {
                ((InventarioController) controlador).setTaller(tallerPrincipal);
            } else if (controlador instanceof MecanicoController) {
                ((MecanicoController) controlador).setTaller(tallerPrincipal);
            }

            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void ingresarAlSistema(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainView.fxml"));
            Parent root = loader.load();

            MainController controller = loader.getController();
            controller.tallerPrincipal = this.tallerPrincipal;

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 480, 420));
            stage.setTitle("Taller FixIt - Menú Principal");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}