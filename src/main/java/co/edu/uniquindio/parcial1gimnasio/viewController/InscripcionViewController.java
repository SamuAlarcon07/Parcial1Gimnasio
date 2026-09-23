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
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.time.LocalDate;

/**
 * Controlador de la vista de gestión de inscripciones.
 */
public class InscripcionViewController {

    private ClienteController clienteController;
    private PlanController planController;
    private InscripcionController inscripcionController;
    private EntrenadorController entrenadorController;
    private ServicioAdicionalController servicioAdicionalController;

    @FXML
    private TextField txtTelefonoCliente;

    @FXML
    private TextField txtCodigoPlan;

    @FXML
    private TextField txtIdentificacionEntrenador;

    @FXML
    private TextField txtCodigoServicio;

    @FXML
    private TextField txtNombreServicio;

    @FXML
    private TextField txtPrecioServicio;

    public void setControllers(
            ClienteController clienteController,
            PlanController planController,
            InscripcionController inscripcionController,
            EntrenadorController entrenadorController,
            ServicioAdicionalController servicioAdicionalController) {

        this.clienteController = clienteController;
        this.planController = planController;
        this.inscripcionController = inscripcionController;
        this.entrenadorController = entrenadorController;
        this.servicioAdicionalController = servicioAdicionalController;
    }

    @FXML
    private void crearInscripcion() {

        try {

            // =========================
            // BUSCAR CLIENTE
            // =========================

            Cliente cliente =
                    clienteController.buscarPorTelefono(
                            txtTelefonoCliente.getText()
                    );

            if (cliente == null) {

                mostrarMensaje(
                        "Cliente no encontrado",
                        "Debe registrar primero el cliente."
                );

                return;
            }


            // =========================
            // BUSCAR PLAN
            // =========================

            Plan plan =
                    planController.buscarPorCodigo(
                            txtCodigoPlan.getText()
                    );

            if (plan == null) {

                mostrarMensaje(
                        "Plan no encontrado",
                        "Debe registrar primero el plan."
                );

                return;
            }


            // =========================
            // CREAR INSCRIPCIÓN
            // =========================

            Inscripcion inscripcion =
                    new Inscripcion(
                            cliente,
                            plan,
                            LocalDate.now()
                    );


            // =========================
            // ENTRENADOR
            // =========================

            if (plan instanceof PlanPersonalizado) {

                String identificacion =
                        txtIdentificacionEntrenador.getText();

                if (identificacion.isBlank()) {

                    mostrarMensaje(
                            "Entrenador requerido",
                            "Los planes personalizados requieren "
                                    + "un entrenador."
                    );

                    return;
                }

                Entrenador entrenador =
                        entrenadorController.buscarPorIdentificacion(
                                identificacion
                        );

                if (entrenador == null) {

                    mostrarMensaje(
                            "Entrenador no encontrado",
                            "No existe un entrenador registrado "
                                    + "con esa identificación."
                    );

                    return;
                }

                inscripcion.asignarEntrenador(entrenador);
            }


            // =========================
            // SERVICIO ADICIONAL
            // =========================

            String codigoServicio =
                    txtCodigoServicio.getText();

            if (!codigoServicio.isBlank()) {

                ServicioAdicional servicio =
                        servicioAdicionalController.buscarPorCodigo(
                                codigoServicio
                        );

                if (servicio == null) {

                    mostrarMensaje(
                            "Servicio no encontrado",
                            "No existe un servicio registrado "
                                    + "con ese código."
                    );

                    return;
                }

                inscripcion.agregarServicio(servicio);
            }


            // =========================
            // REGISTRAR INSCRIPCIÓN
            // =========================

            inscripcionController.registrarInscripcion(
                    inscripcion
            );


            // =========================
            // MOSTRAR RESULTADO
            // =========================

            mostrarMensaje(
                    "Inscripción creada",
                    "Cliente: "
                            + cliente.getNombreCompleto()
                            + "\nPlan: "
                            + plan.getNombre()
                            + "\nValor total: $"
                            + inscripcion.calcularValorTotal()
            );

            limpiarCampos();

        } catch (Exception e) {

            mostrarMensaje(
                    "Error",
                    "No fue posible crear la inscripción.\n"
                            + e.getMessage()
            );
        }
    }

    private void limpiarCampos() {

        txtTelefonoCliente.clear();
        txtCodigoPlan.clear();
        txtIdentificacionEntrenador.clear();
        txtCodigoServicio.clear();
        txtNombreServicio.clear();
        txtPrecioServicio.clear();
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