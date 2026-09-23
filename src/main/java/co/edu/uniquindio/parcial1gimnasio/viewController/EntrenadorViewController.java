package co.edu.uniquindio.parcial1gimnasio.viewController;

import co.edu.uniquindio.parcial1gimnasio.controller.EntrenadorController;
import co.edu.uniquindio.parcial1gimnasio.model.Entrenador;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * Controlador de la vista de entrenadores.
 */
public class EntrenadorViewController {

    private EntrenadorController entrenadorController;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtEspecialidad;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtTarifa;

    public void setEntrenadorController(
            EntrenadorController entrenadorController) {

        this.entrenadorController = entrenadorController;
    }

    @FXML
    private void registrarEntrenador() {

        try {

            String identificacion = txtIdentificacion.getText();
            String nombre = txtNombre.getText();
            String especialidad = txtEspecialidad.getText();
            String telefono = txtTelefono.getText();

            double tarifa = Double.parseDouble(txtTarifa.getText());

            if (identificacion.isBlank()
                    || nombre.isBlank()
                    || especialidad.isBlank()
                    || telefono.isBlank()) {

                mostrarMensaje(
                        "Datos incompletos",
                        "Debe completar todos los campos."
                );

                return;
            }

            Entrenador entrenador =
                    new Entrenador(
                            identificacion,
                            nombre,
                            especialidad,
                            telefono,
                            tarifa
                    );

            entrenadorController.registrarEntrenador(entrenador);

            mostrarMensaje(
                    "Entrenador registrado",
                    "Identificación: "
                            + entrenador.getIdentificacion()
                            + "\nNombre: "
                            + entrenador.getNombre()
                            + "\nEspecialidad: "
                            + entrenador.getEspecialidad()
                            + "\nTarifa por sesión: $"
                            + entrenador.getTarifaPorSesion()
            );

            limpiarCampos();

        } catch (NumberFormatException e) {

            mostrarMensaje(
                    "Dato inválido",
                    "La tarifa por sesión debe ser numérica."
            );
        }
    }

    private void limpiarCampos() {

        txtIdentificacion.clear();
        txtNombre.clear();
        txtEspecialidad.clear();
        txtTelefono.clear();
        txtTarifa.clear();
    }

    private void mostrarMensaje(String titulo, String mensaje) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("SmartGym");
        alert.setHeaderText(titulo);
        alert.setContentText(mensaje);

        alert.showAndWait();
    }
}