package co.edu.uniquindio.parcial1gimnasio.viewController;

import co.edu.uniquindio.parcial1gimnasio.controller.InscripcionController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Controlador de la vista de reportes.
 */
public class ReporteViewController {

    private InscripcionController inscripcionController;

    @FXML
    private TextField txtFechaInicio;

    @FXML
    private TextField txtFechaFin;

    @FXML
    private TextField txtIngresos;

    public void setInscripcionController(
            InscripcionController inscripcionController) {

        this.inscripcionController = inscripcionController;
    }

    @FXML
    private void calcularIngresos() {

        try {

            LocalDate fechaInicio =
                    LocalDate.parse(
                            txtFechaInicio.getText()
                    );

            LocalDate fechaFin =
                    LocalDate.parse(
                            txtFechaFin.getText()
                    );

            if (fechaFin.isBefore(fechaInicio)) {

                mostrarMensaje(
                        "Fechas inválidas",
                        "La fecha final no puede ser anterior "
                                + "a la fecha inicial."
                );

                return;
            }

            double ingresos =
                    inscripcionController
                            .calcularIngresosPorPeriodo(
                                    fechaInicio,
                                    fechaFin
                            );

            txtIngresos.setText(
                    String.format("$%,.2f", ingresos)
            );

        } catch (DateTimeParseException e) {

            mostrarMensaje(
                    "Fecha inválida",
                    "Utilice el formato:\n"
                            + "AAAA-MM-DD\n\n"
                            + "Ejemplo: 2026-09-23"
            );
        }
    }

    private void mostrarMensaje(
            String titulo,
            String mensaje) {

        Alert alert =
                new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("SmartGym");
        alert.setHeaderText(titulo);
        alert.setContentText(mensaje);

        alert.showAndWait();
    }
}