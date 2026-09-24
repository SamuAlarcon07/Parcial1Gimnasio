package co.edu.uniquindio.parcial1gimnasio.viewController;

import co.edu.uniquindio.parcial1gimnasio.controller.ClienteController;
import co.edu.uniquindio.parcial1gimnasio.controller.EntrenadorController;
import co.edu.uniquindio.parcial1gimnasio.controller.InscripcionController;
import co.edu.uniquindio.parcial1gimnasio.controller.PlanController;
import co.edu.uniquindio.parcial1gimnasio.controller.ServicioAdicionalController;
import co.edu.uniquindio.parcial1gimnasio.model.Cliente;
import co.edu.uniquindio.parcial1gimnasio.model.Inscripcion;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Locale;

/**
 * Controlador del dashboard principal de SmartGym.
 */
public class MainViewController {

    private ClienteController clienteController;
    private PlanController planController;
    private InscripcionController inscripcionController;
    private EntrenadorController entrenadorController;
    private ServicioAdicionalController servicioAdicionalController;

    /**
     * Reloj utilizado para actualizar la fecha y hora.
     */
    private Timeline reloj;

    /**
     * Formato utilizado para mostrar la fecha y hora.
     */
    private static final DateTimeFormatter FORMATO_FECHA_HORA =
            DateTimeFormatter.ofPattern(
                    "dd 'de' MMMM 'de' yyyy  |  HH:mm:ss",
                    new Locale("es", "CO")
            );

    /**
     * Formato utilizado para las fechas de las inscripciones.
     */
    private static final DateTimeFormatter FORMATO_FECHA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");


    @FXML
    private Label lblFechaHora;

    @FXML
    private Label lblTotalClientes;

    @FXML
    private Label lblTotalPlanes;

    @FXML
    private Label lblTotalInscripciones;

    @FXML
    private Label lblIngresos;

    @FXML
    private TableView<ActividadReciente> tblActividad;

    @FXML
    private TableColumn<ActividadReciente, String> colCliente;

    @FXML
    private TableColumn<ActividadReciente, String> colPlan;

    @FXML
    private TableColumn<ActividadReciente, String> colFecha;

    @FXML
    private TableColumn<ActividadReciente, String> colValor;

    @FXML
    private ImageView imgBanner;


    /**
     * Recibe los controladores compartidos de la aplicación.
     */
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
        this.servicioAdicionalController =
                servicioAdicionalController;

        configurarBanner();

        configurarTablaActividad();

        actualizarDashboard();

        iniciarReloj();
    }


    /**
     * Inicia el reloj del dashboard.
     */
    private void iniciarReloj() {

        if (lblFechaHora == null) {
            return;
        }

        actualizarFechaHora();

        reloj = new Timeline(
                new KeyFrame(
                        Duration.seconds(1),
                        event -> actualizarFechaHora()
                )
        );

        reloj.setCycleCount(Timeline.INDEFINITE);
        reloj.play();
    }


    /**
     * Actualiza la fecha y hora mostradas en el encabezado.
     */
    private void actualizarFechaHora() {

        if (lblFechaHora == null) {
            return;
        }

        LocalDateTime ahora = LocalDateTime.now();

        lblFechaHora.setText(
                ahora.format(FORMATO_FECHA_HORA)
        );
    }


    /**
     * Configura las columnas de la tabla de actividad reciente.
     */
    private void configurarTablaActividad() {

        if (colCliente == null ||
                colPlan == null ||
                colFecha == null ||
                colValor == null) {

            return;
        }

        colCliente.setCellValueFactory(
                dato -> dato.getValue().clienteProperty()
        );

        colPlan.setCellValueFactory(
                dato -> dato.getValue().planProperty()
        );

        colFecha.setCellValueFactory(
                dato -> dato.getValue().fechaProperty()
        );

        colValor.setCellValueFactory(
                dato -> dato.getValue().valorProperty()
        );
    }


    /**
     * Actualiza toda la información del dashboard.
     */
    public void actualizarDashboard() {

        actualizarEstadisticas();

        actualizarActividadReciente();

        actualizarIngresos();
    }


    /**
     * Actualiza las cantidades generales.
     */
    private void actualizarEstadisticas() {

        if (clienteController != null &&
                lblTotalClientes != null) {

            lblTotalClientes.setText(
                    String.valueOf(
                            clienteController
                                    .getClientes()
                                    .size()
                    )
            );
        }

        if (planController != null &&
                lblTotalPlanes != null) {

            lblTotalPlanes.setText(
                    String.valueOf(
                            planController
                                    .getPlanes()
                                    .size()
                    )
            );
        }

        if (inscripcionController != null &&
                lblTotalInscripciones != null) {

            lblTotalInscripciones.setText(
                    String.valueOf(
                            inscripcionController
                                    .getInscripciones()
                                    .size()
                    )
            );
        }
    }


    /**
     * Actualiza la tabla de actividad reciente.
     *
     * Se muestran las últimas cinco inscripciones,
     * ordenadas desde la más reciente.
     */
    private void actualizarActividadReciente() {

        if (tblActividad == null ||
                inscripcionController == null) {

            return;
        }

        ObservableList<ActividadReciente> actividades =
                FXCollections.observableArrayList();

        inscripcionController
                .getInscripciones()
                .stream()
                .sorted(
                        Comparator.comparing(
                                Inscripcion::getFechaInscripcion
                        ).reversed()
                )
                .limit(5)
                .forEach(inscripcion -> {

                    Cliente cliente =
                            inscripcion.getCliente();

                    String nombreCliente =
                            cliente != null
                                    ? cliente.getNombreCompleto()
                                    : "Sin cliente";

                    String nombrePlan =
                            inscripcion.getPlan() != null
                                    ? inscripcion
                                      .getPlan()
                                      .getNombre()
                                    : "Sin plan";

                    String fecha =
                            inscripcion
                                    .getFechaInscripcion()
                                    .format(FORMATO_FECHA);

                    String valor =
                            formatearDinero(
                                    inscripcion
                                            .calcularValorTotal()
                            );

                    actividades.add(
                            new ActividadReciente(
                                    nombreCliente,
                                    nombrePlan,
                                    fecha,
                                    valor
                            )
                    );
                });

        tblActividad.setItems(actividades);
    }


    /**
     * Calcula los ingresos correspondientes
     * a los últimos siete días.
     */
    private void actualizarIngresos() {

        if (lblIngresos == null ||
                inscripcionController == null) {

            return;
        }

        LocalDate hoy = LocalDate.now();

        LocalDate fechaInicial =
                hoy.minusDays(6);

        double ingresos = 0;

        for (Inscripcion inscripcion :
                inscripcionController.getInscripciones()) {

            LocalDate fecha =
                    inscripcion.getFechaInscripcion();

            if ((fecha.isEqual(fechaInicial)
                    || fecha.isAfter(fechaInicial))
                    &&
                    (fecha.isEqual(hoy)
                            || fecha.isBefore(hoy))) {

                ingresos +=
                        inscripcion.calcularValorTotal();
            }
        }

        lblIngresos.setText(
                formatearDinero(ingresos)
        );
    }


    /**
     * Formatea un valor monetario en pesos colombianos.
     */
    private String formatearDinero(double valor) {

        return String.format(
                        Locale.US,
                        "COP %,.2f",
                        valor
                )
                .replace(",", "X")
                .replace(".", ",")
                .replace("X", ".");
    }


    /**
     * Configura el banner para mantener su proporción
     * y cubrir completamente el Hero.
     */
    private void configurarBanner() {

        if (imgBanner == null) {
            return;
        }

        if (!(imgBanner.getParent() instanceof StackPane hero)) {
            return;
        }

        imgBanner.setManaged(false);

        imgBanner.setPreserveRatio(true);

        imgBanner.setSmooth(true);

        imgBanner.fitWidthProperty()
                .bind(hero.widthProperty());

        Rectangle clip = new Rectangle();

        clip.widthProperty()
                .bind(hero.widthProperty());

        clip.heightProperty()
                .bind(hero.heightProperty());

        clip.setArcWidth(36);

        clip.setArcHeight(36);

        hero.setClip(clip);
    }


    // =========================================================
    // CLIENTES
    // =========================================================

    @FXML
    private void abrirClientes() {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/co/edu/uniquindio/parcial1gimnasio/clientes-view.fxml"
                            )
                    );

            Scene scene =
                    new Scene(
                            loader.load(),
                            500,
                            600
                    );

            ClientesViewController controller =
                    loader.getController();

            controller.setClienteController(
                    clienteController
            );

            abrirVentana(
                    stage -> {

                        stage.setTitle(
                                "SmartGym - Clientes"
                        );

                        stage.setScene(scene);
                    }
            );

        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    // =========================================================
    // PLANES
    // =========================================================

    @FXML
    private void abrirPlanes() {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/co/edu/uniquindio/parcial1gimnasio/plan-view.fxml"
                            )
                    );

            Scene scene =
                    new Scene(
                            loader.load(),
                            550,
                            650
                    );

            PlanViewController controller =
                    loader.getController();

            controller.setPlanController(
                    planController
            );

            abrirVentana(
                    stage -> {

                        stage.setTitle(
                                "SmartGym - Planes"
                        );

                        stage.setScene(scene);
                    }
            );

        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    // =========================================================
    // ENTRENADORES
    // =========================================================

    @FXML
    private void abrirEntrenadores() {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/co/edu/uniquindio/parcial1gimnasio/entrenadores-view.fxml"
                            )
                    );

            Scene scene =
                    new Scene(
                            loader.load(),
                            550,
                            500
                    );

            EntrenadorViewController controller =
                    loader.getController();

            controller.setEntrenadorController(
                    entrenadorController
            );

            abrirVentana(
                    stage -> {

                        stage.setTitle(
                                "SmartGym - Entrenadores"
                        );

                        stage.setScene(scene);
                    }
            );

        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    // =========================================================
    // SERVICIOS
    // =========================================================

    @FXML
    private void abrirServicios() {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/co/edu/uniquindio/parcial1gimnasio/servicios-view.fxml"
                            )
                    );

            Scene scene =
                    new Scene(
                            loader.load(),
                            500,
                            500
                    );

            ServicioAdicionalViewController controller =
                    loader.getController();

            controller.setServicioController(
                    servicioAdicionalController
            );

            abrirVentana(
                    stage -> {

                        stage.setTitle(
                                "SmartGym - Servicios adicionales"
                        );

                        stage.setScene(scene);
                    }
            );

        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    // =========================================================
    // INSCRIPCIONES
    // =========================================================

    @FXML
    private void abrirInscripciones() {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/co/edu/uniquindio/parcial1gimnasio/inscripciones-view.fxml"
                            )
                    );

            Scene scene =
                    new Scene(
                            loader.load(),
                            550,
                            700
                    );

            InscripcionViewController controller =
                    loader.getController();

            controller.setControllers(
                    clienteController,
                    planController,
                    inscripcionController,
                    entrenadorController,
                    servicioAdicionalController
            );

            abrirVentana(
                    stage -> {

                        stage.setTitle(
                                "SmartGym - Inscripciones"
                        );

                        stage.setScene(scene);
                    }
            );

        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    // =========================================================
    // REPORTES
    // =========================================================

    @FXML
    private void abrirReportes() {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    "/co/edu/uniquindio/parcial1gimnasio/reportes-view.fxml"
                            )
                    );

            Scene scene =
                    new Scene(
                            loader.load(),
                            500,
                            450
                    );

            ReporteViewController controller =
                    loader.getController();

            controller.setInscripcionController(
                    inscripcionController
            );

            abrirVentana(
                    stage -> {

                        stage.setTitle(
                                "SmartGym - Reportes"
                        );

                        stage.setScene(scene);
                    }
            );

        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    // =========================================================
    // VENTANAS
    // =========================================================

    /**
     * Abre una ventana secundaria.
     *
     * Cuando la ventana se cierra,
     * el dashboard se actualiza automáticamente.
     */
    private void abrirVentana(
            java.util.function.Consumer<Stage> configurador) {

        Stage stage = new Stage();

        Image iconoSmartGym =
                new Image(
                        getClass().getResourceAsStream(
                                "/co/edu/uniquindio/parcial1gimnasio/logo_mancuerna_smartgym.png"
                        )
                );

        stage.getIcons().add(iconoSmartGym);

        configurador.accept(stage);

        /*
         * Cuando se cierre la ventana:
         *
         * - actualiza clientes
         * - actualiza planes
         * - actualiza inscripciones
         * - actualiza actividad reciente
         * - actualiza ingresos
         */
        stage.setOnHidden(event ->
                actualizarDashboard()
        );

        stage.show();
    }


    // =========================================================
    // ACTIVIDAD RECIENTE
    // =========================================================

    /**
     * Modelo auxiliar utilizado por la tabla
     * de actividad reciente.
     */
    public static class ActividadReciente {

        private final javafx.beans.property.StringProperty cliente;
        private final javafx.beans.property.StringProperty plan;
        private final javafx.beans.property.StringProperty fecha;
        private final javafx.beans.property.StringProperty valor;


        public ActividadReciente(
                String cliente,
                String plan,
                String fecha,
                String valor) {

            this.cliente =
                    new javafx.beans.property.SimpleStringProperty(
                            cliente
                    );

            this.plan =
                    new javafx.beans.property.SimpleStringProperty(
                            plan
                    );

            this.fecha =
                    new javafx.beans.property.SimpleStringProperty(
                            fecha
                    );

            this.valor =
                    new javafx.beans.property.SimpleStringProperty(
                            valor
                    );
        }


        public javafx.beans.property.StringProperty clienteProperty() {
            return cliente;
        }


        public javafx.beans.property.StringProperty planProperty() {
            return plan;
        }


        public javafx.beans.property.StringProperty fechaProperty() {
            return fecha;
        }


        public javafx.beans.property.StringProperty valorProperty() {
            return valor;
        }
    }
}