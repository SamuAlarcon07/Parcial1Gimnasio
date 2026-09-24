package co.edu.uniquindio.parcial1gimnasio.viewController;

import co.edu.uniquindio.parcial1gimnasio.controller.ClienteController;
import co.edu.uniquindio.parcial1gimnasio.model.Cliente;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.time.LocalDate;

/**
 * Controlador de la vista de gestión de clientes.
 *
 * Permite:
 * - Registrar clientes.
 * - Buscar clientes.
 * - Editar clientes.
 * - Eliminar clientes.
 * - Comprobar números perfectos.
 */
public class ClientesViewController {

    private ClienteController clienteController;

    /**
     * Cliente actualmente seleccionado para editar o eliminar.
     */
    private Cliente clienteSeleccionado;


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

    @FXML
    private Button btnRegistrar;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnEliminar;


    /**
     * Recibe el controlador compartido de clientes.
     */
    public void setClienteController(
            ClienteController clienteController) {

        this.clienteController = clienteController;

        clienteSeleccionado = null;

        actualizarEstadoBotones();
    }


    // =========================================================
    // REGISTRAR
    // =========================================================

    /**
     * Registra un nuevo cliente.
     */
    @FXML
    private void registrarCliente() {

        try {

            String nombre =
                    txtNombre.getText().trim();

            String documento =
                    txtDocumento.getText().trim();

            String telefono =
                    txtTelefono.getText().trim();

            String email =
                    txtEmail.getText().trim();

            String edadTexto =
                    txtEdad.getText().trim();


            // -------------------------------------------------
            // VALIDAR CAMPOS
            // -------------------------------------------------

            if (nombre.isBlank()
                    || documento.isBlank()
                    || telefono.isBlank()
                    || email.isBlank()
                    || edadTexto.isBlank()) {

                mostrarMensaje(
                        "Datos incompletos",
                        "Debe completar todos los campos."
                );

                return;
            }


            int edad;

            try {

                edad =
                        Integer.parseInt(
                                edadTexto
                        );

            } catch (NumberFormatException e) {

                mostrarMensaje(
                        "Dato inválido",
                        "La edad debe ser un número entero."
                );

                return;
            }


            if (edad <= 0) {

                mostrarMensaje(
                        "Edad inválida",
                        "La edad debe ser mayor que cero."
                );

                return;
            }


            // -------------------------------------------------
            // VALIDAR DUPLICADOS
            // -------------------------------------------------

            if (clienteController.buscarPorTelefono(
                    telefono
            ) != null) {

                mostrarMensaje(
                        "Teléfono registrado",
                        "Ya existe un cliente registrado "
                                + "con ese número de teléfono."
                );

                return;
            }


            if (clienteController.buscarPorDocumento(
                    documento
            ) != null) {

                mostrarMensaje(
                        "Documento registrado",
                        "Ya existe un cliente registrado "
                                + "con ese documento."
                );

                return;
            }


            // -------------------------------------------------
            // CREAR CLIENTE
            // -------------------------------------------------

            Cliente cliente =
                    new Cliente(
                            nombre,
                            documento,
                            telefono,
                            email,
                            edad,
                            LocalDate.now()
                    );


            clienteController.registrarCliente(
                    cliente
            );


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

        } catch (Exception e) {

            mostrarMensaje(
                    "Error",
                    "No fue posible registrar el cliente.\n"
                            + e.getMessage()
            );
        }
    }


    // =========================================================
    // BUSCAR
    // =========================================================

    /**
     * Busca un cliente utilizando su teléfono.
     *
     * Además de mostrar la información, carga sus datos
     * en el formulario para permitir editarlo o eliminarlo.
     */
    @FXML
    private void buscarCliente() {

        String telefono =
                txtTelefonoBuscar
                        .getText()
                        .trim();


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

            clienteSeleccionado = null;

            actualizarEstadoBotones();

            mostrarMensaje(
                    "Cliente no encontrado",
                    "No existe un cliente registrado "
                            + "con ese teléfono."
            );

            return;
        }


        // -----------------------------------------------------
        // GUARDAR CLIENTE SELECCIONADO
        // -----------------------------------------------------

        clienteSeleccionado = cliente;


        // -----------------------------------------------------
        // CARGAR DATOS EN EL FORMULARIO
        // -----------------------------------------------------

        cargarClienteEnFormulario(
                cliente
        );


        actualizarEstadoBotones();


        mostrarMensaje(
                "Cliente encontrado",
                "Los datos del cliente fueron cargados "
                        + "en el formulario.\n\n"
                        + "Ahora puede editarlos o eliminar "
                        + "el cliente."
        );
    }


    /**
     * Carga los datos de un cliente en el formulario.
     */
    private void cargarClienteEnFormulario(
            Cliente cliente) {

        txtNombre.setText(
                cliente.getNombreCompleto()
        );

        txtDocumento.setText(
                cliente.getDocumento()
        );

        txtTelefono.setText(
                cliente.getTelefono()
        );

        txtEmail.setText(
                cliente.getEmail()
        );

        txtEdad.setText(
                String.valueOf(
                        cliente.getEdad()
                )
        );
    }


    // =========================================================
    // ACTUALIZAR
    // =========================================================

    /**
     * Actualiza el cliente seleccionado.
     */
    @FXML
    private void actualizarCliente() {

        if (clienteSeleccionado == null) {

            mostrarMensaje(
                    "Cliente no seleccionado",
                    "Primero busque un cliente "
                            + "para editarlo."
            );

            return;
        }


        try {

            String nombre =
                    txtNombre.getText().trim();

            String documento =
                    txtDocumento.getText().trim();

            String telefono =
                    txtTelefono.getText().trim();

            String email =
                    txtEmail.getText().trim();

            String edadTexto =
                    txtEdad.getText().trim();


            // -------------------------------------------------
            // VALIDAR CAMPOS
            // -------------------------------------------------

            if (nombre.isBlank()
                    || documento.isBlank()
                    || telefono.isBlank()
                    || email.isBlank()
                    || edadTexto.isBlank()) {

                mostrarMensaje(
                        "Datos incompletos",
                        "Debe completar todos los campos."
                );

                return;
            }


            int edad;

            try {

                edad =
                        Integer.parseInt(
                                edadTexto
                        );

            } catch (NumberFormatException e) {

                mostrarMensaje(
                        "Dato inválido",
                        "La edad debe ser un número entero."
                );

                return;
            }


            if (edad <= 0) {

                mostrarMensaje(
                        "Edad inválida",
                        "La edad debe ser mayor que cero."
                );

                return;
            }


            // -------------------------------------------------
            // VALIDAR DUPLICADOS
            // -------------------------------------------------

            if (clienteController.telefonoYaRegistrado(
                    telefono,
                    clienteSeleccionado
            )) {

                mostrarMensaje(
                        "Teléfono registrado",
                        "Otro cliente ya utiliza "
                                + "ese número de teléfono."
                );

                return;
            }


            if (clienteController.documentoYaRegistrado(
                    documento,
                    clienteSeleccionado
            )) {

                mostrarMensaje(
                        "Documento registrado",
                        "Otro cliente ya utiliza "
                                + "ese documento."
                );

                return;
            }


            // -------------------------------------------------
            // ACTUALIZAR
            // -------------------------------------------------

            clienteController.actualizarCliente(
                    clienteSeleccionado,
                    nombre,
                    documento,
                    telefono,
                    email,
                    edad
            );


            mostrarMensaje(
                    "Cliente actualizado",
                    "La información del cliente "
                            + "fue actualizada correctamente."
            );


            limpiarCampos();


        } catch (Exception e) {

            mostrarMensaje(
                    "Error",
                    "No fue posible actualizar "
                            + "el cliente.\n"
                            + e.getMessage()
            );
        }
    }


    // =========================================================
    // ELIMINAR
    // =========================================================

    /**
     * Elimina el cliente seleccionado.
     */
    @FXML
    private void eliminarCliente() {

        if (clienteSeleccionado == null) {

            mostrarMensaje(
                    "Cliente no seleccionado",
                    "Primero busque un cliente "
                            + "para eliminarlo."
            );

            return;
        }


        // -----------------------------------------------------
        // CONFIRMAR
        // -----------------------------------------------------

        Alert confirmacion =
                new Alert(
                        Alert.AlertType.CONFIRMATION
                );

        confirmacion.setTitle("SmartGym");

        confirmacion.setHeaderText(
                "Eliminar cliente"
        );

        confirmacion.setContentText(
                "¿Está seguro de eliminar al cliente?\n\n"
                        + clienteSeleccionado
                        .getNombreCompleto()
                        + "\n"
                        + clienteSeleccionado
                        .getTelefono()
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


        // -----------------------------------------------------
        // ELIMINAR
        // -----------------------------------------------------

        boolean eliminado =
                clienteController.eliminarCliente(
                        clienteSeleccionado
                );


        if (eliminado) {

            mostrarMensaje(
                    "Cliente eliminado",
                    "El cliente fue eliminado "
                            + "correctamente."
            );

            limpiarCampos();

        } else {

            mostrarMensaje(
                    "No se pudo eliminar",
                    "El cliente no pudo ser eliminado."
            );
        }
    }


    // =========================================================
    // NUMERO PERFECTO
    // =========================================================

    /**
     * Comprueba si el teléfono corresponde
     * a un número perfecto.
     */
    @FXML
    private void comprobarNumeroPerfecto() {

        String telefono =
                txtTelefonoBuscar
                        .getText()
                        .trim();


        if (telefono.isBlank()) {

            mostrarMensaje(
                    "Dato requerido",
                    "Ingrese primero un número de teléfono."
            );

            return;
        }


        try {

            long numero =
                    Long.parseLong(
                            telefono
                    );


            boolean esPerfecto =
                    clienteController.esNumeroPerfecto(
                            numero
                    );


            if (esPerfecto) {

                mostrarMensaje(
                        "Número perfecto",
                        "El teléfono "
                                + telefono
                                + " es un número perfecto."
                );

            } else {

                mostrarMensaje(
                        "Número no perfecto",
                        "El teléfono "
                                + telefono
                                + " no es un número perfecto."
                );
            }

        } catch (NumberFormatException e) {

            mostrarMensaje(
                    "Teléfono inválido",
                    "El teléfono debe contener "
                            + "únicamente números."
            );
        }
    }


    // =========================================================
    // LIMPIAR
    // =========================================================

    private void limpiarCampos() {

        txtNombre.clear();
        txtDocumento.clear();
        txtTelefono.clear();
        txtEmail.clear();
        txtEdad.clear();

        clienteSeleccionado = null;

        actualizarEstadoBotones();
    }


    /**
     * Controla el estado de los botones de edición y eliminación.
     */
    private void actualizarEstadoBotones() {

        boolean habilitados =
                clienteSeleccionado != null;

        if (btnActualizar != null) {
            btnActualizar.setDisable(
                    !habilitados
            );
        }

        if (btnEliminar != null) {
            btnEliminar.setDisable(
                    !habilitados
            );
        }
    }


    // =========================================================
    // VOLVER
    // =========================================================

    /**
     * Regresa al menú principal cerrando la ventana.
     */
    @FXML
    private void volverMenu() {

        Stage stage =
                (Stage) txtNombre
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

        alert.setTitle("SmartGym");

        alert.setHeaderText(titulo);

        alert.setContentText(mensaje);

        aplicarEstiloDialogo(alert);

        alert.showAndWait();
    }


    /**
     * Aplica el estilo visual de SmartGym a los diálogos.
     */
    private void aplicarEstiloDialogo(
            Alert alert) {

        DialogPane dialogPane =
                alert.getDialogPane();


        var recurso =
                getClass().getResource(
                        "/co/edu/uniquindio/parcial1gimnasio/styles.css"
                );


        if (recurso != null) {

            dialogPane.getStylesheets().add(
                    recurso.toExternalForm()
            );
        }


        dialogPane.getStyleClass().add(
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
                            getClass().getResourceAsStream(
                                    "/co/edu/uniquindio/parcial1gimnasio/logo_mancuerna_smartgym.png"
                            )
                    );


            alertStage.getIcons().add(
                    iconoSmartGym
            );
        });
    }
}