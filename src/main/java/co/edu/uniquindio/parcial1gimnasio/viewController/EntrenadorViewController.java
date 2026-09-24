package co.edu.uniquindio.parcial1gimnasio.viewController;

import co.edu.uniquindio.parcial1gimnasio.controller.EntrenadorController;
import co.edu.uniquindio.parcial1gimnasio.model.Entrenador;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Controlador de la vista de entrenadores.
 *
 * Permite registrar, buscar, actualizar y eliminar
 * entrenadores de SmartGym.
 */
public class EntrenadorViewController {

    private EntrenadorController entrenadorController;

    /**
     * Entrenador actualmente seleccionado.
     */
    private Entrenador entrenadorSeleccionado;


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


    @FXML
    private Button btnRegistrar;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnEliminar;


    /**
     * Recibe el controlador encargado de gestionar
     * los entrenadores.
     *
     * @param entrenadorController controlador de entrenadores.
     */
    public void setEntrenadorController(
            EntrenadorController entrenadorController) {

        this.entrenadorController =
                entrenadorController;

        entrenadorSeleccionado = null;

        actualizarEstadoBotones();
    }


    // =========================================================
    // REGISTRAR
    // =========================================================

    /**
     * Registra un nuevo entrenador.
     */
    @FXML
    private void registrarEntrenador() {

        try {

            String identificacion =
                    txtIdentificacion
                            .getText()
                            .trim();

            String nombre =
                    txtNombre
                            .getText()
                            .trim();

            String especialidad =
                    txtEspecialidad
                            .getText()
                            .trim();

            String telefono =
                    txtTelefono
                            .getText()
                            .trim();

            String tarifaTexto =
                    txtTarifa
                            .getText()
                            .trim();


            if (identificacion.isBlank()
                    || nombre.isBlank()
                    || especialidad.isBlank()
                    || telefono.isBlank()
                    || tarifaTexto.isBlank()) {

                mostrarMensaje(
                        "Datos incompletos",
                        "Debe completar todos los campos."
                );

                return;
            }


            if (entrenadorController
                    .buscarPorIdentificacion(
                            identificacion
                    ) != null) {

                mostrarMensaje(
                        "Identificación registrada",
                        "Ya existe un entrenador "
                                + "con esa identificación."
                );

                return;
            }


            double tarifa =
                    Double.parseDouble(
                            tarifaTexto
                    );


            if (tarifa <= 0) {

                mostrarMensaje(
                        "Tarifa inválida",
                        "La tarifa por sesión debe "
                                + "ser mayor que cero."
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


            entrenadorController
                    .registrarEntrenador(
                            entrenador
                    );


            mostrarMensaje(
                    "Entrenador registrado correctamente",

                    "Identificación: "
                            + entrenador
                            .getIdentificacion()

                            + "\nNombre: "
                            + entrenador.getNombre()

                            + "\nEspecialidad: "
                            + entrenador.getEspecialidad()

                            + "\nTeléfono: "
                            + entrenador.getTelefono()

                            + "\nTarifa por sesión: $"
                            + entrenador
                            .getTarifaPorSesion()
            );


            limpiarCampos();

        } catch (NumberFormatException e) {

            mostrarMensaje(
                    "Dato inválido",
                    "La tarifa por sesión debe "
                            + "ser un número válido."
            );
        }
    }


    // =========================================================
    // BUSCAR
    // =========================================================

    /**
     * Busca un entrenador por identificación
     * y carga sus datos en el formulario.
     */
    @FXML
    private void buscarEntrenador() {

        String identificacion =
                txtIdentificacion
                        .getText()
                        .trim();


        if (identificacion.isBlank()) {

            mostrarMensaje(
                    "Identificación requerida",
                    "Ingrese la identificación "
                            + "del entrenador."
            );

            return;
        }


        Entrenador entrenador =
                entrenadorController
                        .buscarPorIdentificacion(
                                identificacion
                        );


        if (entrenador == null) {

            entrenadorSeleccionado = null;

            actualizarEstadoBotones();

            mostrarMensaje(
                    "Entrenador no encontrado",
                    "No existe un entrenador "
                            + "con la identificación "
                            + identificacion
                            + "."
            );

            return;
        }


        entrenadorSeleccionado =
                entrenador;


        cargarEntrenadorEnFormulario(
                entrenador
        );


        actualizarEstadoBotones();


        mostrarMensaje(
                "Entrenador encontrado",
                "Los datos del entrenador "
                        + "fueron cargados en el formulario."
        );
    }


    /**
     * Carga los datos del entrenador seleccionado
     * en el formulario.
     */
    private void cargarEntrenadorEnFormulario(
            Entrenador entrenador) {

        txtIdentificacion.setText(
                entrenador.getIdentificacion()
        );

        txtNombre.setText(
                entrenador.getNombre()
        );

        txtEspecialidad.setText(
                entrenador.getEspecialidad()
        );

        txtTelefono.setText(
                entrenador.getTelefono()
        );

        txtTarifa.setText(
                String.valueOf(
                        entrenador.getTarifaPorSesion()
                )
        );
    }


    // =========================================================
    // ACTUALIZAR
    // =========================================================

    /**
     * Actualiza el entrenador seleccionado.
     */
    @FXML
    private void actualizarEntrenador() {

        if (entrenadorSeleccionado == null) {

            mostrarMensaje(
                    "Entrenador no seleccionado",
                    "Primero busque un entrenador "
                            + "para poder editarlo."
            );

            return;
        }


        try {

            String identificacion =
                    txtIdentificacion
                            .getText()
                            .trim();

            String nombre =
                    txtNombre
                            .getText()
                            .trim();

            String especialidad =
                    txtEspecialidad
                            .getText()
                            .trim();

            String telefono =
                    txtTelefono
                            .getText()
                            .trim();

            String tarifaTexto =
                    txtTarifa
                            .getText()
                            .trim();


            if (identificacion.isBlank()
                    || nombre.isBlank()
                    || especialidad.isBlank()
                    || telefono.isBlank()
                    || tarifaTexto.isBlank()) {

                mostrarMensaje(
                        "Datos incompletos",
                        "Debe completar todos los campos."
                );

                return;
            }


            if (entrenadorController
                    .identificacionYaRegistrada(
                            identificacion,
                            entrenadorSeleccionado
                    )) {

                mostrarMensaje(
                        "Identificación registrada",
                        "Otro entrenador ya utiliza "
                                + "esa identificación."
                );

                return;
            }


            double tarifa =
                    Double.parseDouble(
                            tarifaTexto
                    );


            if (tarifa <= 0) {

                mostrarMensaje(
                        "Tarifa inválida",
                        "La tarifa por sesión debe "
                                + "ser mayor que cero."
                );

                return;
            }


            entrenadorController
                    .actualizarEntrenador(
                            entrenadorSeleccionado,
                            identificacion,
                            nombre,
                            especialidad,
                            telefono,
                            tarifa
                    );


            mostrarMensaje(
                    "Entrenador actualizado",
                    "La información del entrenador "
                            + "fue actualizada correctamente."
            );


            limpiarCampos();

        } catch (NumberFormatException e) {

            mostrarMensaje(
                    "Dato inválido",
                    "La tarifa por sesión debe "
                            + "ser un número válido."
            );
        }
    }


    // =========================================================
    // ELIMINAR
    // =========================================================

    /**
     * Elimina el entrenador seleccionado.
     */
    @FXML
    private void eliminarEntrenador() {

        if (entrenadorSeleccionado == null) {

            mostrarMensaje(
                    "Entrenador no seleccionado",
                    "Primero busque un entrenador "
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
                "Eliminar entrenador"
        );

        confirmacion.setContentText(
                "¿Está seguro de eliminar este entrenador?\n\n"
                        + "Identificación: "
                        + entrenadorSeleccionado
                        .getIdentificacion()
                        + "\nNombre: "
                        + entrenadorSeleccionado
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
                entrenadorController
                        .eliminarEntrenador(
                                entrenadorSeleccionado
                        );


        if (eliminado) {

            mostrarMensaje(
                    "Entrenador eliminado",
                    "El entrenador fue eliminado "
                            + "correctamente."
            );

            limpiarCampos();

        } else {

            mostrarMensaje(
                    "No se pudo eliminar",
                    "El entrenador no pudo ser eliminado."
            );
        }
    }


    // =========================================================
    // BOTONES
    // =========================================================

    private void actualizarEstadoBotones() {

        boolean seleccionado =
                entrenadorSeleccionado != null;


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

    /**
     * Regresa al menú principal cerrando
     * la ventana actual.
     */
    @FXML
    private void volverMenu() {

        Stage stage =
                (Stage) txtIdentificacion
                        .getScene()
                        .getWindow();

        stage.close();
    }


    // =========================================================
    // LIMPIAR
    // =========================================================

    /**
     * Limpia todos los campos del formulario.
     */
    private void limpiarCampos() {

        txtIdentificacion.clear();
        txtNombre.clear();
        txtEspecialidad.clear();
        txtTelefono.clear();
        txtTarifa.clear();


        entrenadorSeleccionado = null;

        actualizarEstadoBotones();
    }


    // =========================================================
    // MENSAJES
    // =========================================================

    /**
     * Muestra un mensaje al usuario.
     */
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
                                recursoLogo
                                        .toExternalForm()
                        );

                alertStage.getIcons()
                        .add(iconoSmartGym);
            }
        });
    }
}