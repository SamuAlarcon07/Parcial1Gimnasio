package co.edu.uniquindio.parcial1gimnasio.viewController;

import co.edu.uniquindio.parcial1gimnasio.controller.ClienteController;
import co.edu.uniquindio.parcial1gimnasio.controller.EntrenadorController;
import co.edu.uniquindio.parcial1gimnasio.controller.InscripcionController;
import co.edu.uniquindio.parcial1gimnasio.controller.PlanController;
import co.edu.uniquindio.parcial1gimnasio.controller.ServicioAdicionalController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controlador del dashboard principal de SmartGym.
 */
public class MainViewController {

    private ClienteController clienteController;
    private PlanController planController;
    private InscripcionController inscripcionController;
    private EntrenadorController entrenadorController;
    private ServicioAdicionalController servicioAdicionalController;

    @FXML
    private Label lblTotalClientes;

    @FXML
    private Label lblTotalPlanes;

    @FXML
    private Label lblTotalInscripciones;

    @FXML
    private ImageView imgBanner;


    /**
     * Recibe los controladores principales de la aplicación.
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

        actualizarEstadisticas();
    }


    /**
     * Configura el banner para que mantenga su proporción
     * y cubra completamente el Hero sin deformarse.
     */
    private void configurarBanner() {

        if (imgBanner == null) {
            return;
        }

        if (!(imgBanner.getParent() instanceof StackPane hero)) {
            return;
        }


        /*
         * La imagen no debe determinar el tamaño
         * del layout.
         */
        imgBanner.setManaged(false);


        /*
         * Conservamos la proporción original
         * de la fotografía.
         */
        imgBanner.setPreserveRatio(true);

        imgBanner.setSmooth(true);


        /*
         * La imagen se ajusta al ancho del Hero.
         *
         * Como la fotografía mantiene su proporción,
         * su altura será mayor que la del Hero.
         *
         * Posteriormente recortamos el sobrante.
         */
        imgBanner.fitWidthProperty()
                .bind(hero.widthProperty());


        /*
         * Creamos un clip del mismo tamaño que el Hero.
         *
         * Esto produce el efecto:
         *
         * background-size: cover;
         */
        Rectangle clip = new Rectangle();

        clip.widthProperty()
                .bind(hero.widthProperty());

        clip.heightProperty()
                .bind(hero.heightProperty());

        clip.setArcWidth(36);

        clip.setArcHeight(36);

        hero.setClip(clip);
    }


    /**
     * Actualiza las estadísticas del dashboard.
     */
    private void actualizarEstadisticas() {

        if (clienteController != null
                && lblTotalClientes != null) {

            lblTotalClientes.setText(
                    String.valueOf(
                            clienteController
                                    .getClientes()
                                    .size()
                    )
            );
        }


        if (planController != null
                && lblTotalPlanes != null) {

            lblTotalPlanes.setText(
                    String.valueOf(
                            planController
                                    .getPlanes()
                                    .size()
                    )
            );
        }


        if (inscripcionController != null
                && lblTotalInscripciones != null) {

            lblTotalInscripciones.setText(
                    String.valueOf(
                            inscripcionController
                                    .getInscripciones()
                                    .size()
                    )
            );
        }
    }


    /* =====================================================
       CLIENTES
       ===================================================== */

    @FXML
    private void abrirClientes() {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/parcial1gimnasio/clientes-view.fxml"
                    )
            );

            Scene scene =
                    new Scene(loader.load(), 500, 600);

            ClientesViewController controller =
                    loader.getController();

            controller.setClienteController(
                    clienteController
            );

            abrirVentana(stage -> {

                stage.setTitle(
                        "SmartGym - Clientes"
                );

                stage.setScene(scene);

            });

        } catch (IOException e) {

            e.printStackTrace();

        }
    }


    /* =====================================================
       PLANES
       ===================================================== */

    @FXML
    private void abrirPlanes() {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/parcial1gimnasio/plan-view.fxml"
                    )
            );

            Scene scene =
                    new Scene(loader.load(), 550, 650);

            PlanViewController controller =
                    loader.getController();

            controller.setPlanController(
                    planController
            );

            abrirVentana(stage -> {

                stage.setTitle(
                        "SmartGym - Planes"
                );

                stage.setScene(scene);

            });

        } catch (IOException e) {

            e.printStackTrace();

        }
    }


    /* =====================================================
       ENTRENADORES
       ===================================================== */

    @FXML
    private void abrirEntrenadores() {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/parcial1gimnasio/entrenadores-view.fxml"
                    )
            );

            Scene scene =
                    new Scene(loader.load(), 550, 500);

            EntrenadorViewController controller =
                    loader.getController();

            controller.setEntrenadorController(
                    entrenadorController
            );

            abrirVentana(stage -> {

                stage.setTitle(
                        "SmartGym - Entrenadores"
                );

                stage.setScene(scene);

            });

        } catch (IOException e) {

            e.printStackTrace();

        }
    }


    /* =====================================================
       SERVICIOS
       ===================================================== */

    @FXML
    private void abrirServicios() {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/parcial1gimnasio/servicios-view.fxml"
                    )
            );

            Scene scene =
                    new Scene(loader.load(), 500, 500);

            ServicioAdicionalViewController controller =
                    loader.getController();

            controller.setServicioController(
                    servicioAdicionalController
            );

            abrirVentana(stage -> {

                stage.setTitle(
                        "SmartGym - Servicios adicionales"
                );

                stage.setScene(scene);

            });

        } catch (IOException e) {

            e.printStackTrace();

        }
    }


    /* =====================================================
       INSCRIPCIONES
       ===================================================== */

    @FXML
    private void abrirInscripciones() {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/parcial1gimnasio/inscripcion-view.fxml"
                    )
            );

            Scene scene =
                    new Scene(loader.load(), 550, 700);

            InscripcionViewController controller =
                    loader.getController();

            controller.setControllers(
                    clienteController,
                    planController,
                    inscripcionController,
                    entrenadorController,
                    servicioAdicionalController
            );

            abrirVentana(stage -> {

                stage.setTitle(
                        "SmartGym - Inscripciones"
                );

                stage.setScene(scene);

            });

        } catch (IOException e) {

            e.printStackTrace();

        }
    }


    /* =====================================================
       REPORTES
       ===================================================== */

    @FXML
    private void abrirReportes() {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/parcial1gimnasio/reportes-view.fxml"
                    )
            );

            Scene scene =
                    new Scene(loader.load(), 500, 450);

            ReporteViewController controller =
                    loader.getController();

            controller.setInscripcionController(
                    inscripcionController
            );

            abrirVentana(stage -> {

                stage.setTitle(
                        "SmartGym - Reportes"
                );

                stage.setScene(scene);

            });

        } catch (IOException e) {

            e.printStackTrace();

        }
    }


    /* =====================================================
       VENTANAS
       ===================================================== */

    /**
     * Abre una ventana secundaria.
     */
    private void abrirVentana(
            java.util.function.Consumer<Stage> configurador) {

        Stage stage = new Stage();

        configurador.accept(stage);

        stage.show();
    }
}