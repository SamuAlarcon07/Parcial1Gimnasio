package co.edu.uniquindio.parcial1gimnasio.viewController;

import co.edu.uniquindio.parcial1gimnasio.controller.PlanController;
import co.edu.uniquindio.parcial1gimnasio.factory.FactoryPlan;
import co.edu.uniquindio.parcial1gimnasio.factory.FactoryPlanBasico;
import co.edu.uniquindio.parcial1gimnasio.factory.FactoryPlanPersonalizado;
import co.edu.uniquindio.parcial1gimnasio.factory.FactoryPlanPremium;
import co.edu.uniquindio.parcial1gimnasio.model.BeneficioPlan;
import co.edu.uniquindio.parcial1gimnasio.model.EstadoPlan;
import co.edu.uniquindio.parcial1gimnasio.model.Plan;
import co.edu.uniquindio.parcial1gimnasio.model.PlanPersonalizado;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

/**
 * Controlador de la vista de gestión de planes.
 *
 * Permite:
 * - Crear planes.
 * - Buscar planes.
 * - Editar planes.
 * - Eliminar planes.
 *
 * Mantiene el uso del patrón Factory Method
 * para la creación de los diferentes tipos de planes.
 */
public class PlanViewController {

    private PlanController planController;

    /**
     * Plan actualmente seleccionado.
     */
    private Plan planSeleccionado;


    @FXML
    private ComboBox<String> cmbTipoPlan;

    @FXML
    private ComboBox<EstadoPlan> cmbEstadoPlan;

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

    @FXML
    private CheckBox chkAccesoZonas;

    @FXML
    private CheckBox chkClasesGrupales;

    @FXML
    private CheckBox chkAcompanamientoEntrenador;

    @FXML
    private Button btnCrear;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnEliminar;


    /**
     * Recibe el controlador encargado de gestionar los planes.
     */
    public void setPlanController(
            PlanController planController) {

        this.planController = planController;

        planSeleccionado = null;

        actualizarEstadoBotones();
    }


    // =========================================================
    // INICIALIZACIÓN
    // =========================================================

    @FXML
    private void initialize() {

        cmbTipoPlan.getItems().addAll(
                "Básico",
                "Premium",
                "Personalizado"
        );

        cmbTipoPlan.setValue("Básico");


        cmbEstadoPlan.getItems().addAll(
                EstadoPlan.values()
        );

        cmbEstadoPlan.setValue(
                EstadoPlan.ACTIVO
        );


        actualizarCamposPersonalizados();

        actualizarBeneficiosPorTipo();


        cmbTipoPlan.valueProperty().addListener(
                (observable, anterior, nuevo) -> {

                    actualizarCamposPersonalizados();

                    /*
                     * Solo modificamos automáticamente
                     * los beneficios cuando no estamos
                     * editando un plan existente.
                     */
                    if (planSeleccionado == null) {
                        actualizarBeneficiosPorTipo();
                    }
                }
        );


        actualizarEstadoBotones();
    }


    // =========================================================
    // CAMPOS PERSONALIZADOS
    // =========================================================

    private void actualizarCamposPersonalizados() {

        boolean personalizado =
                "Personalizado".equals(
                        cmbTipoPlan.getValue()
                );


        txtSesiones.setDisable(
                !personalizado
        );

        txtEspecialidad.setDisable(
                !personalizado
        );

        txtObjetivos.setDisable(
                !personalizado
        );
    }


    // =========================================================
    // BENEFICIOS
    // =========================================================

    private void actualizarBeneficiosPorTipo() {

        String tipo =
                cmbTipoPlan.getValue();


        if (tipo == null) {
            return;
        }


        chkAccesoZonas.setSelected(false);

        chkClasesGrupales.setSelected(false);

        chkAcompanamientoEntrenador.setSelected(false);


        if (tipo.equals("Básico")) {

            chkAccesoZonas.setSelected(true);

        } else if (tipo.equals("Premium")) {

            chkAccesoZonas.setSelected(true);

            chkClasesGrupales.setSelected(true);

            chkAcompanamientoEntrenador
                    .setSelected(true);

        } else if (tipo.equals("Personalizado")) {

            chkAccesoZonas.setSelected(true);

            chkAcompanamientoEntrenador
                    .setSelected(true);
        }
    }


    // =========================================================
    // CREAR PLAN
    // =========================================================

    @FXML
    private void crearPlan() {

        try {

            String codigo =
                    txtCodigo.getText().trim();

            String nombre =
                    txtNombre.getText().trim();

            String descripcion =
                    txtDescripcion.getText().trim();

            String tipo =
                    cmbTipoPlan.getValue();


            if (codigo.isBlank()
                    || nombre.isBlank()
                    || descripcion.isBlank()
                    || tipo == null
                    || cmbEstadoPlan.getValue() == null) {

                mostrarMensaje(
                        "Datos incompletos",
                        "Debe completar todos los datos "
                                + "básicos del plan."
                );

                return;
            }


            // -------------------------------------------------
            // VALIDAR CÓDIGO
            // -------------------------------------------------

            if (planController.buscarPorCodigo(
                    codigo
            ) != null) {

                mostrarMensaje(
                        "Código registrado",
                        "Ya existe un plan registrado "
                                + "con el código "
                                + codigo
                                + "."
                );

                return;
            }


            int duracion =
                    Integer.parseInt(
                            txtDuracion
                                    .getText()
                                    .trim()
                    );


            double valorMensual =
                    Double.parseDouble(
                            txtValorMensual
                                    .getText()
                                    .trim()
                    );


            if (duracion <= 0) {

                mostrarMensaje(
                        "Duración inválida",
                        "La duración debe ser mayor "
                                + "que cero."
                );

                return;
            }


            if (valorMensual <= 0) {

                mostrarMensaje(
                        "Valor inválido",
                        "El valor mensual debe ser "
                                + "mayor que cero."
                );

                return;
            }


            FactoryPlan factory;


            // -------------------------------------------------
            // FACTORY METHOD
            // -------------------------------------------------

            switch (tipo) {

                case "Básico":

                    factory =
                            new FactoryPlanBasico(
                                    codigo,
                                    nombre,
                                    descripcion,
                                    duracion,
                                    valorMensual
                            );

                    break;


                case "Premium":

                    factory =
                            new FactoryPlanPremium(
                                    codigo,
                                    nombre,
                                    descripcion,
                                    duracion,
                                    valorMensual
                            );

                    break;


                case "Personalizado":

                    if (txtSesiones
                            .getText()
                            .isBlank()
                            || txtEspecialidad
                            .getText()
                            .isBlank()
                            || txtObjetivos
                            .getText()
                            .isBlank()) {

                        mostrarMensaje(
                                "Datos incompletos",
                                "Los planes personalizados "
                                        + "requieren sesiones, "
                                        + "especialidad y objetivos."
                        );

                        return;
                    }


                    int sesiones =
                            Integer.parseInt(
                                    txtSesiones
                                            .getText()
                                            .trim()
                            );


                    if (sesiones <= 0) {

                        mostrarMensaje(
                                "Sesiones inválidas",
                                "La cantidad de sesiones "
                                        + "debe ser mayor que cero."
                        );

                        return;
                    }


                    String especialidad =
                            txtEspecialidad
                                    .getText()
                                    .trim();


                    String objetivos =
                            txtObjetivos
                                    .getText()
                                    .trim();


                    factory =
                            new FactoryPlanPersonalizado(
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


            // -------------------------------------------------
            // CREAR PRODUCTO
            // -------------------------------------------------

            Plan plan =
                    factory.crearPlan();


            plan.setEstado(
                    cmbEstadoPlan.getValue()
            );


            configurarBeneficios(plan);


            planController.registrarPlan(
                    plan
            );


            mostrarMensaje(
                    "Plan creado correctamente",
                    "Plan: "
                            + plan.getNombre()
                            + "\nCódigo: "
                            + plan.getCodigo()
                            + "\nTipo: "
                            + tipo
                            + "\nEstado: "
                            + plan.getEstado()
                            + "\nBeneficios: "
                            + obtenerBeneficiosTexto(plan)
                            + "\nValor total: $"
                            + plan.calcularValor()
            );


            limpiarCampos();

        } catch (NumberFormatException e) {

            mostrarMensaje(
                    "Datos inválidos",
                    "Duración, valor mensual y sesiones "
                            + "deben contener valores numéricos."
            );
        }
    }


    // =========================================================
    // BUSCAR
    // =========================================================

    /**
     * Busca un plan por código y carga sus datos
     * en el formulario.
     *
     * Esta acción será conectada al botón Buscar
     * del FXML.
     */
    @FXML
    private void buscarPlan() {

        String codigo =
                txtCodigo
                        .getText()
                        .trim();


        if (codigo.isBlank()) {

            mostrarMensaje(
                    "Código requerido",
                    "Ingrese el código del plan."
            );

            return;
        }


        Plan plan =
                planController.buscarPorCodigo(
                        codigo
                );


        if (plan == null) {

            planSeleccionado = null;

            actualizarEstadoBotones();

            mostrarMensaje(
                    "Plan no encontrado",
                    "No existe un plan registrado "
                            + "con el código "
                            + codigo
                            + "."
            );

            return;
        }


        planSeleccionado = plan;


        cargarPlanEnFormulario(
                plan
        );


        actualizarEstadoBotones();


        mostrarMensaje(
                "Plan encontrado",
                "Los datos del plan fueron cargados "
                        + "en el formulario.\n\n"
                        + "Ahora puede editarlo o eliminarlo."
        );
    }


    // =========================================================
    // CARGAR PLAN
    // =========================================================

    private void cargarPlanEnFormulario(
            Plan plan) {

        txtCodigo.setText(
                plan.getCodigo()
        );

        txtNombre.setText(
                plan.getNombre()
        );

        txtDescripcion.setText(
                plan.getDescripcion()
        );

        txtDuracion.setText(
                String.valueOf(
                        plan.getDuracionMeses()
                )
        );

        txtValorMensual.setText(
                String.valueOf(
                        plan.getValorMensual()
                )
        );

        cmbEstadoPlan.setValue(
                plan.getEstado()
        );


        // -----------------------------------------------------
        // DETERMINAR TIPO
        // -----------------------------------------------------

        if (plan instanceof PlanPersonalizado) {

            cmbTipoPlan.setValue(
                    "Personalizado"
            );

            PlanPersonalizado personalizado =
                    (PlanPersonalizado) plan;


            txtSesiones.setText(
                    String.valueOf(
                            personalizado
                                    .getCantidadSesiones()
                    )
            );

            txtEspecialidad.setText(
                    personalizado
                            .getEspecialidadRequerida()
            );

            txtObjetivos.setText(
                    personalizado
                            .getObjetivosCliente()
            );

        } else {

            if (plan.getClass()
                    .getSimpleName()
                    .equals("PlanPremium")) {

                cmbTipoPlan.setValue(
                        "Premium"
                );

            } else {

                cmbTipoPlan.setValue(
                        "Básico"
                );
            }


            txtSesiones.clear();

            txtEspecialidad.clear();

            txtObjetivos.clear();
        }


        actualizarCamposPersonalizados();


        // -----------------------------------------------------
        // BENEFICIOS
        // -----------------------------------------------------

        chkAccesoZonas.setSelected(
                plan.getBeneficios().contains(
                        BeneficioPlan.ACCESO_ZONAS_DEPORTIVAS
                )
        );


        chkClasesGrupales.setSelected(
                plan.getBeneficios().contains(
                        BeneficioPlan.CLASES_GRUPALES
                )
        );


        chkAcompanamientoEntrenador
                .setSelected(
                        plan.getBeneficios().contains(
                                BeneficioPlan
                                        .ACOMPANAMIENTO_ENTRENADOR
                        )
                );
    }


    // =========================================================
    // ACTUALIZAR
    // =========================================================

    @FXML
    private void actualizarPlan() {

        if (planSeleccionado == null) {

            mostrarMensaje(
                    "Plan no seleccionado",
                    "Primero busque un plan "
                            + "para editarlo."
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

            String tipo =
                    cmbTipoPlan.getValue();


            if (codigo.isBlank()
                    || nombre.isBlank()
                    || descripcion.isBlank()
                    || tipo == null
                    || cmbEstadoPlan.getValue() == null) {

                mostrarMensaje(
                        "Datos incompletos",
                        "Debe completar todos los "
                                + "datos del plan."
                );

                return;
            }


            // -------------------------------------------------
            // VALIDAR CÓDIGO
            // -------------------------------------------------

            if (planController.codigoYaRegistrado(
                    codigo,
                    planSeleccionado
            )) {

                mostrarMensaje(
                        "Código registrado",
                        "Otro plan ya utiliza "
                                + "ese código."
                );

                return;
            }


            int duracion =
                    Integer.parseInt(
                            txtDuracion
                                    .getText()
                                    .trim()
                    );


            double valorMensual =
                    Double.parseDouble(
                            txtValorMensual
                                    .getText()
                                    .trim()
                    );


            if (duracion <= 0) {

                mostrarMensaje(
                        "Duración inválida",
                        "La duración debe ser "
                                + "mayor que cero."
                );

                return;
            }


            if (valorMensual <= 0) {

                mostrarMensaje(
                        "Valor inválido",
                        "El valor mensual debe ser "
                                + "mayor que cero."
                );

                return;
            }


            // -------------------------------------------------
            // VALIDAR TIPO
            // -------------------------------------------------

            boolean esPersonalizado =
                    planSeleccionado
                            instanceof PlanPersonalizado;


            if (esPersonalizado
                    && !tipo.equals(
                    "Personalizado"
            )) {

                mostrarMensaje(
                        "Tipo de plan",
                        "No se puede cambiar un plan "
                                + "personalizado a otro tipo "
                                + "de plan porque perdería "
                                + "su información específica."
                );

                return;
            }


            if (!esPersonalizado
                    && tipo.equals(
                    "Personalizado"
            )) {

                mostrarMensaje(
                        "Tipo de plan",
                        "No se puede convertir "
                                + "directamente este plan en "
                                + "uno personalizado."
                                + "\n\n"
                                + "Para conservar la integridad "
                                + "de los datos, cree un nuevo "
                                + "plan personalizado."
                );

                return;
            }


            // -------------------------------------------------
            // PERSONALIZADO
            // -------------------------------------------------

            if (planSeleccionado
                    instanceof PlanPersonalizado) {

                PlanPersonalizado personalizado =
                        (PlanPersonalizado)
                                planSeleccionado;


                if (txtSesiones
                        .getText()
                        .isBlank()
                        || txtEspecialidad
                        .getText()
                        .isBlank()
                        || txtObjetivos
                        .getText()
                        .isBlank()) {

                    mostrarMensaje(
                            "Datos incompletos",
                            "Los planes personalizados "
                                    + "requieren sesiones, "
                                    + "especialidad y objetivos."
                    );

                    return;
                }


                int sesiones =
                        Integer.parseInt(
                                txtSesiones
                                        .getText()
                                        .trim()
                        );


                if (sesiones <= 0) {

                    mostrarMensaje(
                            "Sesiones inválidas",
                            "La cantidad de sesiones "
                                    + "debe ser mayor que cero."
                    );

                    return;
                }


                personalizado.setCantidadSesiones(
                        sesiones
                );

                personalizado.setEspecialidadRequerida(
                        txtEspecialidad
                                .getText()
                                .trim()
                );

                personalizado.setObjetivosCliente(
                        txtObjetivos
                                .getText()
                                .trim()
                );
            }


            // -------------------------------------------------
            // DATOS GENERALES
            // -------------------------------------------------

            planSeleccionado.setCodigo(
                    codigo
            );

            planSeleccionado.setNombre(
                    nombre
            );

            planSeleccionado.setDescripcion(
                    descripcion
            );

            planSeleccionado.setDuracionMeses(
                    duracion
            );

            planSeleccionado.setValorMensual(
                    valorMensual
            );

            planSeleccionado.setEstado(
                    cmbEstadoPlan.getValue()
            );


            // -------------------------------------------------
            // BENEFICIOS
            // -------------------------------------------------

            configurarBeneficios(
                    planSeleccionado
            );


            planController.actualizarPlan(
                    planSeleccionado
            );


            mostrarMensaje(
                    "Plan actualizado",
                    "La información del plan "
                            + "fue actualizada correctamente."
            );


            limpiarCampos();

        } catch (NumberFormatException e) {

            mostrarMensaje(
                    "Datos inválidos",
                    "Duración, valor mensual y sesiones "
                            + "deben contener valores numéricos."
            );
        }
    }


    // =========================================================
    // ELIMINAR
    // =========================================================

    @FXML
    private void eliminarPlan() {

        if (planSeleccionado == null) {

            mostrarMensaje(
                    "Plan no seleccionado",
                    "Primero busque un plan "
                            + "para eliminarlo."
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
                "Eliminar plan"
        );


        confirmacion.setContentText(
                "¿Está seguro de eliminar este plan?\n\n"
                        + "Código: "
                        + planSeleccionado
                        .getCodigo()
                        + "\nNombre: "
                        + planSeleccionado
                        .getNombre()
        );


        aplicarEstiloDialogo(
                confirmacion
        );


        var resultado =
                confirmacion.showAndWait();


        if (resultado.isEmpty()
                || resultado.get()
                != javafx.scene.control.ButtonType.OK) {

            return;
        }


        boolean eliminado =
                planController.eliminarPlan(
                        planSeleccionado
                );


        if (eliminado) {

            mostrarMensaje(
                    "Plan eliminado",
                    "El plan fue eliminado "
                            + "correctamente."
            );

            limpiarCampos();

        } else {

            mostrarMensaje(
                    "No se pudo eliminar",
                    "El plan no pudo ser eliminado."
            );
        }
    }


    // =========================================================
    // BENEFICIOS
    // =========================================================

    private void configurarBeneficios(
            Plan plan) {

        Set<BeneficioPlan> beneficios =
                new HashSet<>();


        if (chkAccesoZonas.isSelected()) {

            beneficios.add(
                    BeneficioPlan
                            .ACCESO_ZONAS_DEPORTIVAS
            );
        }


        if (chkClasesGrupales.isSelected()) {

            beneficios.add(
                    BeneficioPlan
                            .CLASES_GRUPALES
            );
        }


        if (chkAcompanamientoEntrenador
                .isSelected()) {

            beneficios.add(
                    BeneficioPlan
                            .ACOMPANAMIENTO_ENTRENADOR
            );
        }


        plan.setBeneficios(
                beneficios
        );
    }


    // =========================================================
    // TEXTO DE BENEFICIOS
    // =========================================================

    private String obtenerBeneficiosTexto(
            Plan plan) {

        if (plan.getBeneficios().isEmpty()) {

            return "Sin beneficios";
        }


        StringBuilder texto =
                new StringBuilder();


        for (BeneficioPlan beneficio :
                plan.getBeneficios()) {

            switch (beneficio) {

                case ACCESO_ZONAS_DEPORTIVAS:

                    texto.append(
                            "Acceso a zonas deportivas"
                    );

                    break;


                case CLASES_GRUPALES:

                    texto.append(
                            "Clases grupales"
                    );

                    break;


                case ACOMPANAMIENTO_ENTRENADOR:

                    texto.append(
                            "Acompañamiento de entrenador"
                    );

                    break;
            }


            texto.append(", ");
        }


        if (texto.length() >= 2) {

            texto.setLength(
                    texto.length() - 2
            );
        }


        return texto.toString();
    }


    // =========================================================
    // LIMPIAR
    // =========================================================

    private void limpiarCampos() {

        txtCodigo.clear();

        txtNombre.clear();

        txtDescripcion.clear();

        txtDuracion.clear();

        txtValorMensual.clear();

        txtSesiones.clear();

        txtEspecialidad.clear();

        txtObjetivos.clear();


        cmbTipoPlan.setValue(
                "Básico"
        );

        cmbEstadoPlan.setValue(
                EstadoPlan.ACTIVO
        );


        planSeleccionado = null;


        actualizarBeneficiosPorTipo();

        actualizarCamposPersonalizados();

        actualizarEstadoBotones();
    }


    // =========================================================
    // BOTONES
    // =========================================================

    private void actualizarEstadoBotones() {

        boolean seleccionado =
                planSeleccionado != null;


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
    // MENSAJES
    // =========================================================

    private void mostrarMensaje(
            String titulo,
            String mensaje) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );


        alert.setTitle(
                "SmartGym"
        );

        alert.setHeaderText(
                titulo
        );

        alert.setContentText(
                mensaje
        );


        aplicarEstiloDialogo(
                alert
        );


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


            Image iconoSmartGym =
                    new Image(
                            getClass()
                                    .getResourceAsStream(
                                            "/co/edu/uniquindio/parcial1gimnasio/logo_mancuerna_smartgym.png"
                                    )
                    );


            alertStage.getIcons().add(
                    iconoSmartGym
            );
        });
    }
}