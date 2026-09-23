module co.edu.uniquindio.parcial1gimnasio {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.parcial1gimnasio to javafx.fxml;
    exports co.edu.uniquindio.parcial1gimnasio;
}