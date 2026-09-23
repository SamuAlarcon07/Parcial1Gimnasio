package co.edu.uniquindio.parcial1gimnasio;

<<<<<<< HEAD
import co.edu.uniquindio.parcial1gimnasio.controller.ClienteController;
import co.edu.uniquindio.parcial1gimnasio.controller.EntrenadorController;
import co.edu.uniquindio.parcial1gimnasio.controller.InscripcionController;
import co.edu.uniquindio.parcial1gimnasio.controller.PlanController;
import co.edu.uniquindio.parcial1gimnasio.controller.ServicioAdicionalController;
=======
>>>>>>> a6060bfcdbb2a8e3201381ef2547ced5bede39b3
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
<<<<<<< HEAD
import co.edu.uniquindio.parcial1gimnasio.viewController.MainViewController;
=======
>>>>>>> a6060bfcdbb2a8e3201381ef2547ced5bede39b3

import java.io.IOException;

/**
 * Clase principal que inicia la aplicación SmartGym.
 */
public class App extends Application {

<<<<<<< HEAD
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

=======
>>>>>>> a6060bfcdbb2a8e3201381ef2547ced5bede39b3
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(
                App.class.getResource(
                        "/co/edu/uniquindio/parcial1gimnasio/main-view.fxml"
                )
        );

        Scene scene = new Scene(loader.load(), 500, 500);

<<<<<<< HEAD
        MainViewController controller = loader.getController();

        controller.setControllers(
                clienteController,
                planController,
                inscripcionController,
                entrenadorController,
                servicioAdicionalController
        );

=======
>>>>>>> a6060bfcdbb2a8e3201381ef2547ced5bede39b3
        stage.setTitle("SmartGym");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}