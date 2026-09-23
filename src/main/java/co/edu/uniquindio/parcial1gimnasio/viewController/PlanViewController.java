package co.edu.uniquindio.parcial1gimnasio.viewController;

import co.edu.uniquindio.parcial1gimnasio.controller.PlanController;
import co.edu.uniquindio.parcial1gimnasio.factory.FactoryPlan;
import co.edu.uniquindio.parcial1gimnasio.factory.FactoryPlanBasico;
import co.edu.uniquindio.parcial1gimnasio.factory.FactoryPlanPersonalizado;
import co.edu.uniquindio.parcial1gimnasio.factory.FactoryPlanPremium;
import co.edu.uniquindio.parcial1gimnasio.model.Plan;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * Controlador de la vista de gestión de planes.
 */
public class PlanViewController {

    private PlanController planController;

    @FXML
    private ComboBox<String> cmbTipoPlan;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtDuracion;

    @FXML
    private TextField txtValorMensual;

    @FXML
    private TextField txtSesiones;

    @FXML
    private TextField txtEspecialidad;

    @FXML
    private TextField txtObjetivos;

    /**
     * Recibe el controlador encargado de gestionar los planes.
     *
     * @param planController controlador de planes.
     */
    public void setPlanController(PlanController planController) {
        this.planController = planController;
    }

    @FXML
    private void initialize() {

        cmbTipoPlan.getItems().addAll(
                "Básico",
                "Premium",
                "Personalizado"
        );

        cmbTipoPlan.setValue("Básico");
    }

    /**
     * Crea un plan utilizando el Factory Method correspondiente
     * al tipo seleccionado.
     */
    @FXML
    private void crearPlan() {

        try {

            String codigo = txtCodigo.getText();
            String nombre = txtNombre.getText();
            String descripcion = txtDescripcion.getText();

            int duracion =
                    Integer.parseInt(txtDuracion.getText());

            double valorMensual =
                    Double.parseDouble(txtValorMensual.getText());

            String tipo = cmbTipoPlan.getValue();

            FactoryPlan factory;

            switch (tipo) {

                case "Básico":

                    factory = new FactoryPlanBasico(
                            codigo,
                            nombre,
                            descripcion,
                            duracion,
                            valorMensual
                    );

                    break;

                case "Premium":

                    factory = new FactoryPlanPremium(
                            codigo,
                            nombre,
                            descripcion,
                            duracion,
                            valorMensual
                    );

                    break;

                case "Personalizado":

                    int sesiones =
                            Integer.parseInt(txtSesiones.getText());

                    String especialidad =
                            txtEspecialidad.getText();

                    String objetivos =
                            txtObjetivos.getText();

                    factory = new FactoryPlanPersonalizado(
                            codigo,
                            nombre,
                            descripcion,
                            duracion,
                            valorMensual,
                            sesiones,
                            especialidad,
                            objetivos
                    );

                    break;

                default:

                    throw new IllegalStateException(
                            "Debe seleccionar un tipo de plan."
                    );
            }

            Plan plan = factory.crearPlan();

            planController.registrarPlan(plan);

            mostrarMensaje(
                    "Plan creado",
                    "Plan: " + plan.getNombre()
                            + "\nCódigo: " + plan.getCodigo()
                            + "\nTipo: " + tipo
                            + "\nValor total: $"
                            + plan.calcularValor()
            );

            limpiarCampos();

        } catch (NumberFormatException e) {

            mostrarMensaje(
                    "Datos inválidos",
                    "Duración, valor mensual y sesiones deben "
                            + "contener valores numéricos."
            );
        }
    }

    private void limpiarCampos() {

        txtCodigo.clear();
        txtNombre.clear();
        txtDescripcion.clear();
        txtDuracion.clear();
        txtValorMensual.clear();
        txtSesiones.clear();
        txtEspecialidad.clear();
        txtObjetivos.clear();
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