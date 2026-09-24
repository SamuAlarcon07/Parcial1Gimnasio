package co.edu.uniquindio.parcial1gimnasio.controller;

import co.edu.uniquindio.parcial1gimnasio.model.Cliente;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador encargado de gestionar las operaciones relacionadas
 * con los clientes de SmartGym.
 *
 * Incluye las operaciones CRUD:
 * Crear, consultar, actualizar y eliminar.
 */
public class ClienteController {

    private List<Cliente> clientes;

    public ClienteController() {
        clientes = new ArrayList<>();
    }

    /**
     * Registra un nuevo cliente en el sistema.
     *
     * @param cliente cliente que se desea registrar.
     */
    public void registrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    /**
     * Busca un cliente utilizando su número de teléfono.
     *
     * @param telefono teléfono del cliente.
     * @return cliente encontrado o null si no existe.
     */
    public Cliente buscarPorTelefono(String telefono) {

        for (Cliente cliente : clientes) {

            if (cliente.getTelefono().equals(telefono)) {
                return cliente;
            }
        }

        return null;
    }

    /**
     * Busca un cliente utilizando su documento.
     *
     * @param documento documento del cliente.
     * @return cliente encontrado o null si no existe.
     */
    public Cliente buscarPorDocumento(String documento) {

        for (Cliente cliente : clientes) {

            if (cliente.getDocumento().equals(documento)) {
                return cliente;
            }
        }

        return null;
    }

    /**
     * Verifica si un teléfono ya está registrado por otro cliente.
     *
     * @param telefono teléfono que se desea comprobar.
     * @param clienteActual cliente que se está editando.
     * @return true si el teléfono pertenece a otro cliente.
     */
    public boolean telefonoYaRegistrado(
            String telefono,
            Cliente clienteActual) {

        for (Cliente cliente : clientes) {

            if (cliente != clienteActual
                    && cliente.getTelefono().equals(telefono)) {

                return true;
            }
        }

        return false;
    }

    /**
     * Verifica si un documento ya está registrado por otro cliente.
     *
     * @param documento documento que se desea comprobar.
     * @param clienteActual cliente que se está editando.
     * @return true si el documento pertenece a otro cliente.
     */
    public boolean documentoYaRegistrado(
            String documento,
            Cliente clienteActual) {

        for (Cliente cliente : clientes) {

            if (cliente != clienteActual
                    && cliente.getDocumento().equals(documento)) {

                return true;
            }
        }

        return false;
    }

    /**
     * Actualiza la información de un cliente.
     *
     * @param cliente cliente que se desea actualizar.
     * @param nombreCompleto nuevo nombre.
     * @param documento nuevo documento.
     * @param telefono nuevo teléfono.
     * @param email nuevo correo.
     * @param edad nueva edad.
     */
    public void actualizarCliente(
            Cliente cliente,
            String nombreCompleto,
            String documento,
            String telefono,
            String email,
            int edad) {

        cliente.setNombreCompleto(nombreCompleto);
        cliente.setDocumento(documento);
        cliente.setTelefono(telefono);
        cliente.setEmail(email);
        cliente.setEdad(edad);
    }

    /**
     * Elimina un cliente del sistema.
     *
     * @param cliente cliente que se desea eliminar.
     * @return true si el cliente fue eliminado.
     */
    public boolean eliminarCliente(Cliente cliente) {
        return clientes.remove(cliente);
    }

    /**
     * Determina si un número es perfecto.
     *
     * @param numero número que se desea verificar.
     * @return true si el número es perfecto.
     */
    public boolean esNumeroPerfecto(long numero) {

        if (numero <= 1) {
            return false;
        }

        long sumaDivisores = 1;

        for (long divisor = 2;
             divisor <= numero / 2;
             divisor++) {

            if (numero % divisor == 0) {
                sumaDivisores += divisor;
            }
        }

        return sumaDivisores == numero;
    }

    /**
     * Determina si el teléfono de un cliente corresponde
     * a un número perfecto.
     *
     * @param cliente cliente que se desea evaluar.
     * @return true si el teléfono es un número perfecto.
     */
    public boolean telefonoEsNumeroPerfecto(Cliente cliente) {

        try {

            long telefono =
                    Long.parseLong(
                            cliente.getTelefono()
                    );

            return esNumeroPerfecto(telefono);

        } catch (NumberFormatException e) {

            return false;
        }
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}