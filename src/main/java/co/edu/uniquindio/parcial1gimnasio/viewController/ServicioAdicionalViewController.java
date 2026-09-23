package co.edu.uniquindio.parcial1gimnasio.viewController;

import co.edu.uniquindio.parcial1gimnasio.controller.ServicioAdicionalController;
import co.edu.uniquindio.parcial1gimnasio.model.ServicioAdicional;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * Controlador de la vista de servicios adicionales.
 */
public class ServicioAdicionalViewController {

    private ServicioAdicionalController servicioController;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtPrecio;

    public void setServicioController(
            ServicioAdicionalController servicioController) {

        this.servicioController = servicioController;
    }

    @FXML
    private void registrarServicio() {

        try {

            String codigo = txtCodigo.getText();
            String nombre = txtNombre.getText();
            String descripcion = txtDescripcion.getText();

            double precio = Double.parseDouble(txtPrecio.getText());

            if (codigo.isBlank()
                    || nombre.isBlank()
                    || descripcion.isBlank()) {

                mostrarMensaje(
                        "Datos incompletos",
                        "Debe completar todos los campos."
                );

                return;
            }

            ServicioAdicional servicio =
                    new ServicioAdicional(
                            codigo,
                            nombre,
                            descripcion,
                            precio,
                            true
                    );

            servicioController.registrarServicio(servicio);

            mostrarMensaje(
                    "Servicio registrado",
                    "Código: " + servicio.getCodigo()
                            + "\nNombre: " + servicio.getNombre()
                            + "\nPrecio: $" + servicio.getPrecio()
            );

            limpiarCampos();

        } catch (NumberFormatException e) {

            mostrarMensaje(
                    "Dato inválido",
                    "El precio debe ser un valor numérico."
            );
        }
    }

    private void limpiarCampos() {

        txtCodigo.clear();
        txtNombre.clear();
        txtDescripcion.clear();
        txtPrecio.clear();
    }

    private void mostrarMensaje(String titulo, String mensaje) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("SmartGym");
        alert.setHeaderText(titulo);
        alert.setContentText(mensaje);

        alert.showAndWait();
    }
}