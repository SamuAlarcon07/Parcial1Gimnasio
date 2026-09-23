package co.edu.uniquindio.parcial1gimnasio;


import co.edu.uniquindio.parcial1gimnasio.controller.ClienteController;
import co.edu.uniquindio.parcial1gimnasio.controller.EntrenadorController;
import co.edu.uniquindio.parcial1gimnasio.controller.InscripcionController;
import co.edu.uniquindio.parcial1gimnasio.controller.PlanController;
import co.edu.uniquindio.parcial1gimnasio.controller.ServicioAdicionalController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import co.edu.uniquindio.parcial1gimnasio.viewController.MainViewController;

import java.io.IOException;

/**
 * Clase principal que inicia la aplicación SmartGym.
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
        servicioAdicionalController = new ServicioAdicionalController();
    }

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(
                App.class.getResource(
                        "/co/edu/uniquindio/parcial1gimnasio/main-view.fxml"
                )
        );

        Scene scene = new Scene(loader.load(), 500, 500);


        MainViewController controller = loader.getController();

        controller.setControllers(
                clienteController,
                planController,
                inscripcionController,
                entrenadorController,
                servicioAdicionalController
        );
        
        stage.setTitle("SmartGym");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}