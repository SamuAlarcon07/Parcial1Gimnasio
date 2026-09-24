package co.edu.uniquindio.parcial1gimnasio;

import co.edu.uniquindio.parcial1gimnasio.controller.ClienteController;
import co.edu.uniquindio.parcial1gimnasio.controller.EntrenadorController;
import co.edu.uniquindio.parcial1gimnasio.controller.InscripcionController;
import co.edu.uniquindio.parcial1gimnasio.controller.PlanController;
import co.edu.uniquindio.parcial1gimnasio.controller.ServicioAdicionalController;
import co.edu.uniquindio.parcial1gimnasio.viewController.MainViewController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal de la aplicación SmartGym.
 */
public class App extends Application {

    private ClienteController clienteController;
    private PlanController planController;
    private InscripcionController inscripcionController;
    private EntrenadorController entrenadorController;
    private ServicioAdicionalController servicioAdicionalController;

    @Override
    public void init() {

        clienteController = new ClienteController();
        planController = new PlanController();
        inscripcionController = new InscripcionController();
        entrenadorController = new EntrenadorController();
        servicioAdicionalController =
                new ServicioAdicionalController();
    }

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader =
                new FXMLLoader(
                        App.class.getResource(
                                "/co/edu/uniquindio/parcial1gimnasio/main-view.fxml"
                        )
                );

        Scene scene =
                new Scene(loader.load());

        MainViewController controller =
                loader.getController();

        controller.setControllers(
                clienteController,
                planController,
                inscripcionController,
                entrenadorController,
                servicioAdicionalController
        );

        // =====================================================
        // ICONO DE SMARTGYM
        // =====================================================

        Image iconoSmartGym =
                new Image(
                        App.class.getResourceAsStream(
                                "/co/edu/uniquindio/parcial1gimnasio/logo_mancuerna_smartgym.png"
                        )
                );

        stage.getIcons().add(iconoSmartGym);

        // =====================================================
        // CONFIGURACIÓN DE LA VENTANA
        // =====================================================

        stage.setTitle(
                "SmartGym - Sistema de gestión de gimnasio"
        );

        stage.setScene(scene);

        stage.setWidth(1100);
        stage.setHeight(850);

        stage.setMinWidth(1100);
        stage.setMinHeight(700);

        stage.centerOnScreen();

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}