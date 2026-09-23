package co.edu.uniquindio.parcial1gimnasio.viewController;

import co.edu.uniquindio.parcial1gimnasio.controller.ClienteController;
import co.edu.uniquindio.parcial1gimnasio.model.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.time.LocalDate;

/**
 * Controlador de la vista de gestión de clientes.
 */
public class ClientesViewController {

    private ClienteController clienteController;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtDocumento;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtTelefonoBuscar;

    public void setClienteController(
            ClienteController clienteController) {

        this.clienteController = clienteController;
    }

    @FXML
    private void registrarCliente() {

        try {

            String nombre = txtNombre.getText();
            String documento = txtDocumento.getText();
            String telefono = txtTelefono.getText();
            String email = txtEmail.getText();

            int edad = Integer.parseInt(
                    txtEdad.getText()
            );

            if (nombre.isBlank()
                    || documento.isBlank()
                    || telefono.isBlank()
                    || email.isBlank()) {

                mostrarMensaje(
                        "Datos incompletos",
                        "Debe completar todos los campos."
                );

                return;
            }

            Cliente cliente =
                    new Cliente(
                            nombre,
                            documento,
                            telefono,
                            email,
                            edad,
                            LocalDate.now()
                    );

            clienteController.registrarCliente(cliente);

            mostrarMensaje(
                    "Cliente registrado",
                    "Cliente: "
                            + cliente.getNombreCompleto()
                            + "\nDocumento: "
                            + cliente.getDocumento()
                            + "\nTeléfono: "
                            + cliente.getTelefono()
            );

            limpiarCampos();

        } catch (NumberFormatException e) {

            mostrarMensaje(
                    "Dato inválido",
                    "La edad debe ser un número entero."
            );
        }
    }

    @FXML
    private void buscarCliente() {

        String telefono =
                txtTelefonoBuscar.getText();

        if (telefono.isBlank()) {

            mostrarMensaje(
                    "Dato requerido",
                    "Ingrese un número de teléfono."
            );

            return;
        }

        Cliente cliente =
                clienteController.buscarPorTelefono(
                        telefono
                );

        if (cliente == null) {

            mostrarMensaje(
                    "Cliente no encontrado",
                    "No existe un cliente registrado "
                            + "con ese teléfono."
            );

            return;
        }

        mostrarMensaje(
                "Cliente encontrado",
                "Nombre: "
                        + cliente.getNombreCompleto()
                        + "\nDocumento: "
                        + cliente.getDocumento()
                        + "\nTeléfono: "
                        + cliente.getTelefono()
                        + "\nCorreo: "
                        + cliente.getEmail()
                        + "\nEdad: "
                        + cliente.getEdad()
                        + "\nFecha de registro: "
                        + cliente.getFechaRegistro()
        );
    }

    @FXML
    private void comprobarNumeroPerfecto() {

        String telefono =
                txtTelefonoBuscar.getText();

        if (telefono.isBlank()) {

            mostrarMensaje(
                    "Dato requerido",
                    "Ingrese primero un número de teléfono."
            );

            return;
        }

        try {

            long numero =
                    Long.parseLong(telefono);

            boolean esPerfecto =
                    clienteController.esNumeroPerfecto(
                            numero
                    );

            if (esPerfecto) {

                mostrarMensaje(
                        "Número perfecto",
                        "El teléfono " + telefono
                                + " es un número perfecto."
                );

            } else {

                mostrarMensaje(
                        "Número no perfecto",
                        "El teléfono " + telefono
                                + " no es un número perfecto."
                );
            }

        } catch (NumberFormatException e) {

            mostrarMensaje(
                    "Teléfono inválido",
                    "El teléfono debe contener únicamente números."
            );
        }
    }

    private void limpiarCampos() {

        txtNombre.clear();
        txtDocumento.clear();
        txtTelefono.clear();
        txtEmail.clear();
        txtEdad.clear();
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