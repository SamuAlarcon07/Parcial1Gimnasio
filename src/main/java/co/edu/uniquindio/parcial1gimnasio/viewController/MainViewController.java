package co.edu.uniquindio.parcial1gimnasio.viewController;


import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controlador de la ventana principal de SmartGym.
 */
public class MainViewController {

    @FXML
    private Label lblBienvenida;

    @FXML
    public void initialize() {
        lblBienvenida.setText("Bienvenido a SmartGym");
    }
}