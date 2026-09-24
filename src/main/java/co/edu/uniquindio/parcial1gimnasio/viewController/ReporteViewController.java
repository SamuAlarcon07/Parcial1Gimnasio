package co.edu.uniquindio.parcial1gimnasio.viewController;

import co.edu.uniquindio.parcial1gimnasio.controller.InscripcionController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.time.LocalDate;

/**
 * Controlador de la vista de reportes.
 */
public class ReporteViewController {

    private InscripcionController inscripcionController;

    @FXML
    private DatePicker dpFechaInicio;

    @FXML
    private DatePicker dpFechaFin;

    @FXML
    private TextField txtIngresos;

    public void setInscripcionController(
            InscripcionController inscripcionController) {

        this.inscripcionController = inscripcionController;
    }

    @FXML
    private void calcularIngresos() {

        LocalDate fechaInicio =
                dpFechaInicio.getValue();

        LocalDate fechaFin =
                dpFechaFin.getValue();

        // Validar que se hayan seleccionado las dos fechas
        if (fechaInicio == null || fechaFin == null) {

            mostrarMensaje(
                    "Datos incompletos",
                    "Debe seleccionar la fecha inicial y la fecha final."
            );

            return;
        }

        // Validar que la fecha final no sea anterior
        if (fechaFin.isBefore(fechaInicio)) {

            mostrarMensaje(
                    "Fechas inválidas",
                    "La fecha final no puede ser anterior "
                            + "a la fecha inicial."
            );

            return;
        }

        // Calcular ingresos
        double ingresos =
                inscripcionController
                        .calcularIngresosPorPeriodo(
                                fechaInicio,
                                fechaFin
                        );

        txtIngresos.setText(
                String.format("$%,.2f", ingresos)
        );
    }

    @FXML
    private void volverMenu() {

        Stage stage =
                (Stage) dpFechaInicio
                        .getScene()
                        .getWindow();

        stage.close();
    }

    private void mostrarMensaje(
            String titulo,
            String mensaje) {

        Alert alert =
                new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("SmartGym");
        alert.setHeaderText(titulo);
        alert.setContentText(mensaje);

        DialogPane dialogPane =
                alert.getDialogPane();

        dialogPane.getStylesheets().add(
                getClass()
                        .getResource(
                                "/co/edu/uniquindio/parcial1gimnasio/styles.css"
                        )
                        .toExternalForm()
        );

        dialogPane.getStyleClass().add(
                "smartgym-dialog"
        );

        dialogPane.setGraphic(null);

        alert.setOnShown(event -> {

            Stage alertStage =
                    (Stage) alert.getDialogPane()
                            .getScene()
                            .getWindow();

            Image iconoSmartGym =
                    new Image(
                            getClass().getResourceAsStream(
                                    "/co/edu/uniquindio/parcial1gimnasio/logo_mancuerna_smartgym.png"
                            )
                    );

            alertStage.getIcons().add(
                    iconoSmartGym
            );
        });

        alert.showAndWait();
    }
}