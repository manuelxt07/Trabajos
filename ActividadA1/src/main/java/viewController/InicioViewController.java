package viewController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class InicioViewController {

    @FXML
    private Button btnIngresar;

    @FXML
    void onIngresar() {
        System.out.println("¡Botón presionado! Entrando a BiciTaller...");
    }

    @FXML
    void initialize() {
    }
}