module co.edu.uniquindio.parcial1gimnasio {

    requires javafx.controls;
    requires javafx.fxml;

    opens co.edu.uniquindio.parcial1gimnasio to javafx.fxml;
    opens co.edu.uniquindio.parcial1gimnasio.viewController to javafx.fxml;

    exports co.edu.uniquindio.parcial1gimnasio;
    exports co.edu.uniquindio.parcial1gimnasio.model;
    exports co.edu.uniquindio.parcial1gimnasio.factory;
    exports co.edu.uniquindio.parcial1gimnasio.controller;
    exports co.edu.uniquindio.parcial1gimnasio.viewController;
}