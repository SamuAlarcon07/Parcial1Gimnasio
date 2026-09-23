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
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controlador de la ventana principal de SmartGym.
 */
public class MainViewController {

    private ClienteController clienteController;
    private PlanController planController;
    private InscripcionController inscripcionController;
    private EntrenadorController entrenadorController;
    private ServicioAdicionalController servicioAdicionalController;

    @FXML
    private Label lblBienvenida;

    @FXML
    public void initialize() {
        lblBienvenida.setText("Bienvenido a SmartGym");
    }

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
    private void abrirClientes() {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/parcial1gimnasio/clientes-view.fxml"
                    )
            );

            Scene scene = new Scene(loader.load(), 500, 600);

            ClientesViewController controller =
                    loader.getController();

            controller.setClienteController(clienteController);

            Stage stage = new Stage();

            stage.setTitle("SmartGym - Clientes");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirPlanes() {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/parcial1gimnasio/plan-view.fxml"
                    )
            );

            Scene scene = new Scene(loader.load(), 550, 650);

            PlanViewController controller =
                    loader.getController();

            controller.setPlanController(planController);

            Stage stage = new Stage();

            stage.setTitle("SmartGym - Planes");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirInscripciones() {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/parcial1gimnasio/inscripcion-view.fxml"
                    )
            );

            Scene scene = new Scene(loader.load(), 550, 750);

            InscripcionViewController controller =
                    loader.getController();

            controller.setControllers(
                    clienteController,
                    planController,
                    inscripcionController,
                    entrenadorController,
                    servicioAdicionalController
            );

            Stage stage = new Stage();

            stage.setTitle("SmartGym - Inscripciones");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void abrirServicios() {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/parcial1gimnasio/servicios-view.fxml"
                    )
            );

            Scene scene = new Scene(loader.load(), 500, 500);

            ServicioAdicionalViewController controller =
                    loader.getController();

            controller.setServicioController(
                    servicioAdicionalController
            );

            Stage stage = new Stage();

            stage.setTitle("SmartGym - Servicios adicionales");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    @FXML
    private void abrirEntrenadores() {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/co/edu/uniquindio/parcial1gimnasio/entrenadores-view.fxml"
                    )
            );

            Scene scene = new Scene(loader.load(), 550, 500);

            EntrenadorViewController controller =
                    loader.getController();

            controller.setEntrenadorController(
                    entrenadorController
            );

            Stage stage = new Stage();

            stage.setTitle("SmartGym - Entrenadores");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}