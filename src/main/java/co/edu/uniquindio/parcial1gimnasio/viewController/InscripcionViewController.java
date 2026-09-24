package co.edu.uniquindio.parcial1gimnasio.viewController;

import co.edu.uniquindio.parcial1gimnasio.controller.ClienteController;
import co.edu.uniquindio.parcial1gimnasio.controller.EntrenadorController;
import co.edu.uniquindio.parcial1gimnasio.controller.InscripcionController;
import co.edu.uniquindio.parcial1gimnasio.controller.PlanController;
import co.edu.uniquindio.parcial1gimnasio.controller.ServicioAdicionalController;

import co.edu.uniquindio.parcial1gimnasio.model.Cliente;
import co.edu.uniquindio.parcial1gimnasio.model.Entrenador;
import co.edu.uniquindio.parcial1gimnasio.model.Inscripcion;
import co.edu.uniquindio.parcial1gimnasio.model.Plan;
import co.edu.uniquindio.parcial1gimnasio.model.PlanPersonalizado;
import co.edu.uniquindio.parcial1gimnasio.model.ServicioAdicional;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Controlador de la vista de gestión de inscripciones.
 *
 * Permite crear, consultar, actualizar y eliminar
 * inscripciones de SmartGym.
 */
public class InscripcionViewController {

    private ClienteController clienteController;
    private PlanController planController;
    private InscripcionController inscripcionController;
    private EntrenadorController entrenadorController;
    private ServicioAdicionalController servicioAdicionalController;

    /**
     * Inscripción actualmente seleccionada.
     */
    private Inscripcion inscripcionSeleccionada;


    // =========================================================
    // CONTROLES
    // =========================================================

    @FXML
    private ComboBox<Inscripcion> cmbInscripcion;

    @FXML
    private ComboBox<Cliente> cmbCliente;

    @FXML
    private ComboBox<Plan> cmbPlan;

    @FXML
    private ComboBox<Entrenador> cmbEntrenador;

    @FXML
    private ComboBox<ServicioAdicional> cmbServicio;


    @FXML
    private TextField txtNombreServicio;

    @FXML
    private TextField txtPrecioServicio;


    @FXML
    private Button btnCrear;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnEliminar;


    // =========================================================
    // CONFIGURACIÓN
    // =========================================================

    public void setControllers(
            ClienteController clienteController,
            PlanController planController,
            InscripcionController inscripcionController,
            EntrenadorController entrenadorController,
            ServicioAdicionalController servicioAdicionalController) {

        this.clienteController =
                clienteController;

        this.planController =
                planController;

        this.inscripcionController =
                inscripcionController;

        this.entrenadorController =
                entrenadorController;

        this.servicioAdicionalController =
                servicioAdicionalController;

        cargarDatos();

        configurarEventos();

        actualizarEstadoBotones();
    }


    // =========================================================
    // CARGAR DATOS
    // =========================================================

    private void cargarDatos() {

        if (clienteController != null) {

            cmbCliente.setItems(
                    FXCollections.observableArrayList(
                            clienteController.getClientes()
                    )
            );
        }


        if (planController != null) {

            cmbPlan.setItems(
                    FXCollections.observableArrayList(
                            planController.getPlanes()
                    )
            );
        }


        if (entrenadorController != null) {

            cmbEntrenador.setItems(
                    FXCollections.observableArrayList(
                            entrenadorController
                                    .getEntrenadores()
                    )
            );
        }


        if (servicioAdicionalController != null) {

            cmbServicio.setItems(
                    FXCollections.observableArrayList(
                            servicioAdicionalController
                                    .getServicios()
                    )
            );
        }


        if (inscripcionController != null) {

            cargarInscripciones();
        }


        configurarConversores();
    }


    /**
     * Carga las inscripciones existentes.
     */
    private void cargarInscripciones() {

        cmbInscripcion.setItems(
                FXCollections.observableArrayList(
                        inscripcionController
                                .getInscripciones()
                )
        );
    }


    // =========================================================
    // CONVERSORES
    // =========================================================

    private void configurarConversores() {

        // -----------------------------------------------------
        // INSCRIPCIÓN
        // -----------------------------------------------------

        cmbInscripcion.setConverter(
                new StringConverter<>() {

                    @Override
                    public String toString(
                            Inscripcion inscripcion) {

                        if (inscripcion == null) {
                            return "";
                        }

                        return inscripcion
                                .getCliente()
                                .getNombreCompleto()
                                + " — "
                                + inscripcion
                                .getPlan()
                                .getNombre()
                                + " — "
                                + inscripcion
                                .getFechaInscripcion();
                    }

                    @Override
                    public Inscripcion fromString(
                            String string) {

                        return null;
                    }
                }
        );


        // -----------------------------------------------------
        // CLIENTE
        // -----------------------------------------------------

        cmbCliente.setConverter(
                new StringConverter<>() {

                    @Override
                    public String toString(
                            Cliente cliente) {

                        if (cliente == null) {
                            return "";
                        }

                        return cliente
                                .getNombreCompleto()
                                + " — "
                                + cliente.getTelefono();
                    }

                    @Override
                    public Cliente fromString(
                            String string) {

                        return null;
                    }
                }
        );


        // -----------------------------------------------------
        // PLAN
        // -----------------------------------------------------

        cmbPlan.setConverter(
                new StringConverter<>() {

                    @Override
                    public String toString(
                            Plan plan) {

                        if (plan == null) {
                            return "";
                        }

                        return plan.getNombre()
                                + " — "
                                + plan.getCodigo();
                    }

                    @Override
                    public Plan fromString(
                            String string) {

                        return null;
                    }
                }
        );


        // -----------------------------------------------------
        // ENTRENADOR
        // -----------------------------------------------------

        cmbEntrenador.setConverter(
                new StringConverter<>() {

                    @Override
                    public String toString(
                            Entrenador entrenador) {

                        if (entrenador == null) {
                            return "";
                        }

                        return entrenador.getNombre()
                                + " — "
                                + entrenador
                                .getEspecialidad()
                                + " — "
                                + entrenador
                                .getIdentificacion();
                    }

                    @Override
                    public Entrenador fromString(
                            String string) {

                        return null;
                    }
                }
        );


        // -----------------------------------------------------
        // SERVICIO
        // -----------------------------------------------------

        cmbServicio.setConverter(
                new StringConverter<>() {

                    @Override
                    public String toString(
                            ServicioAdicional servicio) {

                        if (servicio == null) {
                            return "";
                        }

                        return servicio.getNombre()
                                + " — "
                                + servicio.getCodigo()
                                + " — COP "
                                + formatearDinero(
                                servicio.getPrecio()
                        );
                    }

                    @Override
                    public ServicioAdicional fromString(
                            String string) {

                        return null;
                    }
                }
        );
    }


    // =========================================================
    // EVENTOS
    // =========================================================

    private void configurarEventos() {

        // -----------------------------------------------------
        // CAMBIO DE INSCRIPCIÓN
        // -----------------------------------------------------

        cmbInscripcion.valueProperty()
                .addListener(
                        (observable, anterior, nueva) -> {

                            if (nueva != null) {

                                inscripcionSeleccionada =
                                        nueva;

                                cargarInscripcion(
                                        nueva
                                );

                            } else {

                                inscripcionSeleccionada =
                                        null;
                            }

                            actualizarEstadoBotones();
                        }
                );


        // -----------------------------------------------------
        // CAMBIO DE PLAN
        // -----------------------------------------------------

        cmbPlan.valueProperty().addListener(
                (observable, anterior, nuevoPlan) -> {

                    actualizarEstadoEntrenador(
                            nuevoPlan
                    );
                }
        );


        // -----------------------------------------------------
        // CAMBIO DE SERVICIO
        // -----------------------------------------------------

        cmbServicio.valueProperty().addListener(
                (observable, anterior, nuevoServicio) -> {

                    mostrarInformacionServicio(
                            nuevoServicio
                    );
                }
        );


        actualizarEstadoEntrenador(null);
    }


    /**
     * Habilita o deshabilita el entrenador dependiendo
     * del tipo de plan.
     */
    private void actualizarEstadoEntrenador(
            Plan plan) {

        if (plan instanceof PlanPersonalizado) {

            cmbEntrenador.setDisable(false);

        } else {

            cmbEntrenador.setDisable(true);

            cmbEntrenador
                    .getSelectionModel()
                    .clearSelection();
        }
    }


    /**
     * Muestra la información del servicio seleccionado.
     */
    private void mostrarInformacionServicio(
            ServicioAdicional servicio) {

        if (servicio == null) {

            txtNombreServicio.clear();

            txtPrecioServicio.clear();

            return;
        }

        txtNombreServicio.setText(
                servicio.getNombre()
        );

        txtPrecioServicio.setText(
                formatearDinero(
                        servicio.getPrecio()
                )
        );
    }


    // =========================================================
    // CREAR
    // =========================================================

    @FXML
    private void crearInscripcion() {

        try {

            Cliente cliente =
                    cmbCliente.getValue();

            if (cliente == null) {

                mostrarMensaje(
                        "Cliente requerido",
                        "Seleccione el cliente que "
                                + "realizará la inscripción."
                );

                return;
            }


            Plan plan =
                    cmbPlan.getValue();

            if (plan == null) {

                mostrarMensaje(
                        "Plan requerido",
                        "Seleccione el plan que "
                                + "adquirirá el cliente."
                );

                return;
            }


            Inscripcion inscripcion =
                    new Inscripcion(
                            cliente,
                            plan,
                            LocalDate.now()
                    );


            // -------------------------------------------------
            // ENTRENADOR
            // -------------------------------------------------

            if (plan instanceof PlanPersonalizado) {

                Entrenador entrenador =
                        cmbEntrenador.getValue();

                if (entrenador == null) {

                    mostrarMensaje(
                            "Entrenador requerido",
                            "Los planes personalizados "
                                    + "requieren un entrenador."
                    );

                    return;
                }

                inscripcion.asignarEntrenador(
                        entrenador
                );
            }


            // -------------------------------------------------
            // SERVICIO
            // -------------------------------------------------

            ServicioAdicional servicio =
                    cmbServicio.getValue();

            if (servicio != null) {

                if (!servicio.isDisponible()) {

                    mostrarMensaje(
                            "Servicio no disponible",
                            "El servicio seleccionado "
                                    + "no está disponible."
                    );

                    return;
                }

                inscripcion.agregarServicio(
                        servicio
                );
            }


            // -------------------------------------------------
            // REGISTRAR
            // -------------------------------------------------

            inscripcionController
                    .registrarInscripcion(
                            inscripcion
                    );


            cargarInscripciones();


            mostrarMensaje(
                    "Inscripción creada",
                    "Cliente: "
                            + cliente.getNombreCompleto()
                            + "\nPlan: "
                            + plan.getNombre()
                            + "\nFecha: "
                            + inscripcion
                            .getFechaInscripcion()
                            + "\nValor total: "
                            + formatearDinero(
                            inscripcion
                                    .calcularValorTotal()
                    )
            );


            limpiarCampos();

        } catch (Exception e) {

            mostrarMensaje(
                    "Error",
                    "No fue posible crear "
                            + "la inscripción.\n\n"
                            + e.getMessage()
            );
        }
    }


    // =========================================================
    // CARGAR INSCRIPCIÓN
    // =========================================================

    private void cargarInscripcion(
            Inscripcion inscripcion) {

        cmbCliente.setValue(
                inscripcion.getCliente()
        );

        cmbPlan.setValue(
                inscripcion.getPlan()
        );


        if (inscripcion.getPlan()
                instanceof PlanPersonalizado) {

            cmbEntrenador.setDisable(false);

            cmbEntrenador.setValue(
                    inscripcion.getEntrenador()
            );

        } else {

            cmbEntrenador
                    .getSelectionModel()
                    .clearSelection();

            cmbEntrenador.setDisable(true);
        }


        // -----------------------------------------------------
        // SERVICIO
        // -----------------------------------------------------

        if (!inscripcion
                .getServiciosAdicionales()
                .isEmpty()) {

            ServicioAdicional servicio =
                    inscripcion
                            .getServiciosAdicionales()
                            .get(0);

            cmbServicio.setValue(
                    servicio
            );

        } else {

            cmbServicio
                    .getSelectionModel()
                    .clearSelection();

            txtNombreServicio.clear();

            txtPrecioServicio.clear();
        }
    }


    // =========================================================
    // ACTUALIZAR
    // =========================================================

    @FXML
    private void actualizarInscripcion() {

        if (inscripcionSeleccionada == null) {

            mostrarMensaje(
                    "Inscripción no seleccionada",
                    "Seleccione una inscripción "
                            + "para poder actualizarla."
            );

            return;
        }


        try {

            Cliente nuevoCliente =
                    cmbCliente.getValue();

            if (nuevoCliente == null) {

                mostrarMensaje(
                        "Cliente requerido",
                        "Seleccione un cliente."
                );

                return;
            }


            Plan nuevoPlan =
                    cmbPlan.getValue();

            if (nuevoPlan == null) {

                mostrarMensaje(
                        "Plan requerido",
                        "Seleccione un plan."
                );

                return;
            }


            // -------------------------------------------------
            // VALIDAR ENTRENADOR
            // -------------------------------------------------

            Entrenador nuevoEntrenador = null;


            if (nuevoPlan
                    instanceof PlanPersonalizado) {

                nuevoEntrenador =
                        cmbEntrenador.getValue();

                if (nuevoEntrenador == null) {

                    mostrarMensaje(
                            "Entrenador requerido",
                            "Los planes personalizados "
                                    + "requieren un entrenador."
                    );

                    return;
                }
            }


            // -------------------------------------------------
            // SERVICIO
            // -------------------------------------------------

            ServicioAdicional nuevoServicio =
                    cmbServicio.getValue();


            if (nuevoServicio != null
                    && !nuevoServicio.isDisponible()) {

                mostrarMensaje(
                        "Servicio no disponible",
                        "El servicio seleccionado "
                                + "no está disponible."
                );

                return;
            }


            // -------------------------------------------------
            // CLIENTE
            // -------------------------------------------------

            Cliente clienteAnterior =
                    inscripcionSeleccionada
                            .getCliente();


            if (clienteAnterior != nuevoCliente) {

                clienteAnterior
                        .eliminarInscripcion(
                                inscripcionSeleccionada
                        );

                nuevoCliente
                        .agregarInscripcion(
                                inscripcionSeleccionada
                        );
            }


            // -------------------------------------------------
            // ACTUALIZAR DATOS
            // -------------------------------------------------

            inscripcionSeleccionada
                    .setCliente(nuevoCliente);

            inscripcionSeleccionada
                    .setPlan(nuevoPlan);

            inscripcionSeleccionada
                    .setEntrenador(nuevoEntrenador);


            // -------------------------------------------------
            // ACTUALIZAR SERVICIOS
            // -------------------------------------------------

            List<ServicioAdicional> servicios =
                    new ArrayList<>();


            if (nuevoServicio != null) {

                servicios.add(
                        nuevoServicio
                );
            }


            inscripcionSeleccionada
                    .setServiciosAdicionales(
                            servicios
                    );


            mostrarMensaje(
                    "Inscripción actualizada",
                    "La inscripción fue actualizada "
                            + "correctamente.\n\n"
                            + "Nuevo valor total: "
                            + formatearDinero(
                            inscripcionSeleccionada
                                    .calcularValorTotal()
                    )
            );


            cargarInscripciones();

            limpiarCampos();

        } catch (Exception e) {

            mostrarMensaje(
                    "Error",
                    "No fue posible actualizar "
                            + "la inscripción.\n\n"
                            + e.getMessage()
            );
        }
    }


    // =========================================================
    // ELIMINAR
    // =========================================================

    @FXML
    private void eliminarInscripcion() {

        if (inscripcionSeleccionada == null) {

            mostrarMensaje(
                    "Inscripción no seleccionada",
                    "Seleccione una inscripción "
                            + "para poder eliminarla."
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
                "Eliminar inscripción"
        );

        confirmacion.setContentText(
                "¿Está seguro de eliminar esta inscripción?\n\n"
                        + "Cliente: "
                        + inscripcionSeleccionada
                        .getCliente()
                        .getNombreCompleto()
                        + "\nPlan: "
                        + inscripcionSeleccionada
                        .getPlan()
                        .getNombre()
                        + "\nFecha: "
                        + inscripcionSeleccionada
                        .getFechaInscripcion()
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


        boolean eliminada =
                inscripcionController
                        .eliminarInscripcion(
                                inscripcionSeleccionada
                        );


        if (eliminada) {

            mostrarMensaje(
                    "Inscripción eliminada",
                    "La inscripción fue eliminada "
                            + "correctamente."
            );

            cargarInscripciones();

            limpiarCampos();

        } else {

            mostrarMensaje(
                    "No se pudo eliminar",
                    "La inscripción no pudo ser eliminada."
            );
        }
    }


    // =========================================================
    // BOTONES
    // =========================================================

    private void actualizarEstadoBotones() {

        boolean seleccionada =
                inscripcionSeleccionada != null;


        if (btnActualizar != null) {

            btnActualizar.setDisable(
                    !seleccionada
            );
        }


        if (btnEliminar != null) {

            btnEliminar.setDisable(
                    !seleccionada
            );
        }
    }


    // =========================================================
    // LIMPIAR
    // =========================================================

    private void limpiarCampos() {

        cmbInscripcion
                .getSelectionModel()
                .clearSelection();

        cmbCliente
                .getSelectionModel()
                .clearSelection();

        cmbPlan
                .getSelectionModel()
                .clearSelection();

        cmbEntrenador
                .getSelectionModel()
                .clearSelection();

        cmbServicio
                .getSelectionModel()
                .clearSelection();

        txtNombreServicio.clear();

        txtPrecioServicio.clear();

        inscripcionSeleccionada = null;

        actualizarEstadoEntrenador(null);

        actualizarEstadoBotones();
    }


    // =========================================================
    // VOLVER
    // =========================================================

    @FXML
    private void volverMenu() {

        Stage stage =
                (Stage) cmbCliente
                        .getScene()
                        .getWindow();

        stage.close();
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

                alertStage.getIcons()
                        .add(
                                iconoSmartGym
                        );
            }
        });
    }


    // =========================================================
    // FORMATO DE DINERO
    // =========================================================

    private String formatearDinero(
            double valor) {

        return String.format(
                        Locale.US,
                        "%,.0f",
                        valor
                )
                .replace(",", ".");
    }
}