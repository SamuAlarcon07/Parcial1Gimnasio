package co.edu.uniquindio.parcial1gimnasio.viewController;

import co.edu.uniquindio.parcial1gimnasio.controller.ServicioAdicionalController;
import co.edu.uniquindio.parcial1gimnasio.model.ServicioAdicional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Controlador de la vista de servicios adicionales.
 *
 * Permite registrar, buscar, actualizar y eliminar
 * servicios adicionales de SmartGym.
 */
public class ServicioAdicionalViewController {

    private ServicioAdicionalController servicioController;

    /**
     * Servicio actualmente seleccionado.
     */
    private ServicioAdicional servicioSeleccionado;


    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtPrecio;


    @FXML
    private CheckBox chkDisponible;


    @FXML
    private Button btnRegistrar;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnEliminar;


    /**
     * Recibe el controlador encargado de gestionar
     * los servicios.
     *
     * @param servicioController controlador de servicios.
     */
    public void setServicioController(
            ServicioAdicionalController servicioController) {

        this.servicioController =
                servicioController;

        servicioSeleccionado = null;

        actualizarEstadoBotones();
    }


    // =========================================================
    // REGISTRAR
    // =========================================================

    @FXML
    private void registrarServicio() {

        try {

            String codigo =
                    txtCodigo.getText().trim();

            String nombre =
                    txtNombre.getText().trim();

            String descripcion =
                    txtDescripcion.getText().trim();

            String precioTexto =
                    txtPrecio.getText().trim();


            if (codigo.isBlank()
                    || nombre.isBlank()
                    || descripcion.isBlank()
                    || precioTexto.isBlank()) {

                mostrarMensaje(
                        "Datos incompletos",
                        "Debe completar todos los campos."
                );

                return;
            }


            if (servicioController.buscarPorCodigo(
                    codigo
            ) != null) {

                mostrarMensaje(
                        "Código registrado",
                        "Ya existe un servicio con "
                                + "ese código."
                );

                return;
            }


            double precio =
                    Double.parseDouble(
                            precioTexto
                    );


            if (precio <= 0) {

                mostrarMensaje(
                        "Precio inválido",
                        "El precio debe ser mayor "
                                + "que cero."
                );

                return;
            }


            boolean disponible =
                    chkDisponible.isSelected();


            ServicioAdicional servicio =
                    new ServicioAdicional(
                            codigo,
                            nombre,
                            descripcion,
                            precio,
                            disponible
                    );


            servicioController.registrarServicio(
                    servicio
            );


            mostrarMensaje(
                    "Servicio registrado",
                    "Código: "
                            + servicio.getCodigo()
                            + "\nNombre: "
                            + servicio.getNombre()
                            + "\nPrecio: $"
                            + servicio.getPrecio()
                            + "\nDisponible: "
                            + (servicio.isDisponible()
                            ? "Sí"
                            : "No")
            );


            limpiarCampos();

        } catch (NumberFormatException e) {

            mostrarMensaje(
                    "Dato inválido",
                    "El precio debe ser un valor numérico."
            );
        }
    }


    // =========================================================
    // BUSCAR
    // =========================================================

    @FXML
    private void buscarServicio() {

        String codigo =
                txtCodigo.getText().trim();


        if (codigo.isBlank()) {

            mostrarMensaje(
                    "Código requerido",
                    "Ingrese el código del servicio."
            );

            return;
        }


        ServicioAdicional servicio =
                servicioController.buscarPorCodigo(
                        codigo
                );


        if (servicio == null) {

            servicioSeleccionado = null;

            actualizarEstadoBotones();

            mostrarMensaje(
                    "Servicio no encontrado",
                    "No existe un servicio con "
                            + "el código "
                            + codigo
                            + "."
            );

            return;
        }


        servicioSeleccionado =
                servicio;


        cargarServicioEnFormulario(
                servicio
        );


        actualizarEstadoBotones();


        mostrarMensaje(
                "Servicio encontrado",
                "Los datos del servicio fueron "
                        + "cargados en el formulario."
        );
    }


    /**
     * Carga los datos del servicio seleccionado
     * en el formulario.
     */
    private void cargarServicioEnFormulario(
            ServicioAdicional servicio) {

        txtCodigo.setText(
                servicio.getCodigo()
        );

        txtNombre.setText(
                servicio.getNombre()
        );

        txtDescripcion.setText(
                servicio.getDescripcion()
        );

        txtPrecio.setText(
                String.valueOf(
                        servicio.getPrecio()
                )
        );

        chkDisponible.setSelected(
                servicio.isDisponible()
        );
    }


    // =========================================================
    // ACTUALIZAR
    // =========================================================

    @FXML
    private void actualizarServicio() {

        if (servicioSeleccionado == null) {

            mostrarMensaje(
                    "Servicio no seleccionado",
                    "Primero busque un servicio "
                            + "para poder editarlo."
            );

            return;
        }


        try {

            String codigo =
                    txtCodigo.getText().trim();

            String nombre =
                    txtNombre.getText().trim();

            String descripcion =
                    txtDescripcion.getText().trim();

            String precioTexto =
                    txtPrecio.getText().trim();


            if (codigo.isBlank()
                    || nombre.isBlank()
                    || descripcion.isBlank()
                    || precioTexto.isBlank()) {

                mostrarMensaje(
                        "Datos incompletos",
                        "Debe completar todos los campos."
                );

                return;
            }


            if (servicioController.codigoYaRegistrado(
                    codigo,
                    servicioSeleccionado
            )) {

                mostrarMensaje(
                        "Código registrado",
                        "Otro servicio ya utiliza "
                                + "ese código."
                );

                return;
            }


            double precio =
                    Double.parseDouble(
                            precioTexto
                    );


            if (precio <= 0) {

                mostrarMensaje(
                        "Precio inválido",
                        "El precio debe ser mayor "
                                + "que cero."
                );

                return;
            }


            boolean disponible =
                    chkDisponible.isSelected();


            servicioController.actualizarServicio(
                    servicioSeleccionado,
                    codigo,
                    nombre,
                    descripcion,
                    precio,
                    disponible
            );


            mostrarMensaje(
                    "Servicio actualizado",
                    "La información del servicio "
                            + "fue actualizada correctamente."
            );


            limpiarCampos();

        } catch (NumberFormatException e) {

            mostrarMensaje(
                    "Dato inválido",
                    "El precio debe ser un valor numérico."
            );
        }
    }


    // =========================================================
    // ELIMINAR
    // =========================================================

    @FXML
    private void eliminarServicio() {

        if (servicioSeleccionado == null) {

            mostrarMensaje(
                    "Servicio no seleccionado",
                    "Primero busque un servicio "
                            + "para poder eliminarlo."
            );

            return;
        }


        Alert confirmacion =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );


        confirmacion.setTitle(
                "SmartGym"
        );

        confirmacion.setHeaderText(
                "Eliminar servicio"
        );

        confirmacion.setContentText(
                "¿Está seguro de eliminar este servicio?\n\n"
                        + "Código: "
                        + servicioSeleccionado
                        .getCodigo()
                        + "\nNombre: "
                        + servicioSeleccionado
                        .getNombre()
        );


        aplicarEstiloDialogo(
                confirmacion
        );


        var resultado =
                confirmacion.showAndWait();


        if (resultado.isEmpty()
                || resultado.get()
                != ButtonType.OK) {

            return;
        }


        boolean eliminado =
                servicioController.eliminarServicio(
                        servicioSeleccionado
                );


        if (eliminado) {

            mostrarMensaje(
                    "Servicio eliminado",
                    "El servicio fue eliminado "
                            + "correctamente."
            );

            limpiarCampos();

        } else {

            mostrarMensaje(
                    "No se pudo eliminar",
                    "El servicio no pudo ser eliminado."
            );
        }
    }


    // =========================================================
    // BOTONES
    // =========================================================

    private void actualizarEstadoBotones() {

        boolean seleccionado =
                servicioSeleccionado != null;


        if (btnActualizar != null) {

            btnActualizar.setDisable(
                    !seleccionado
            );
        }


        if (btnEliminar != null) {

            btnEliminar.setDisable(
                    !seleccionado
            );
        }
    }


    // =========================================================
    // VOLVER
    // =========================================================

    @FXML
    private void volverMenu() {

        Stage stage =
                (Stage) txtCodigo
                        .getScene()
                        .getWindow();

        stage.close();
    }


    // =========================================================
    // LIMPIAR
    // =========================================================

    private void limpiarCampos() {

        txtCodigo.clear();

        txtNombre.clear();

        txtDescripcion.clear();

        txtPrecio.clear();

        chkDisponible.setSelected(true);


        servicioSeleccionado = null;

        actualizarEstadoBotones();
    }


    // =========================================================
    // MENSAJES
    // =========================================================

    private void mostrarMensaje(
            String titulo,
            String mensaje) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle("SmartGym");
        alert.setHeaderText(titulo);
        alert.setContentText(mensaje);

        aplicarEstiloDialogo(alert);

        alert.showAndWait();
    }


    /**
     * Aplica el estilo visual de SmartGym
     * a los cuadros de diálogo.
     */
    private void aplicarEstiloDialogo(
            Alert alert) {

        DialogPane dialogPane =
                alert.getDialogPane();


        var recurso =
                getClass().getResource(
                        "/co/edu/uniquindio/parcial1gimnasio/styles.css"
                );


        if (recurso != null) {

            dialogPane.getStylesheets()
                    .add(
                            recurso.toExternalForm()
                    );
        }


        dialogPane.getStyleClass()
                .add(
                        "smartgym-dialog"
                );


        dialogPane.setGraphic(null);


        alert.setOnShown(event -> {

            Stage alertStage =
                    (Stage) alert
                            .getDialogPane()
                            .getScene()
                            .getWindow();


            var recursoLogo =
                    getClass().getResource(
                            "/co/edu/uniquindio/parcial1gimnasio/logo_mancuerna_smartgym.png"
                    );


            if (recursoLogo != null) {

                Image iconoSmartGym =
                        new Image(
                                recursoLogo.toExternalForm()
                        );

                alertStage.getIcons().add(
                        iconoSmartGym
                );
            }
        });
    }
}