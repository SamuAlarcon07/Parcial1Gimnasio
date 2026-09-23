package co.edu.uniquindio.parcial1gimnasio.controller;

import co.edu.uniquindio.parcial1gimnasio.model.Cliente;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador encargado de gestionar las operaciones relacionadas
 * con los clientes de SmartGym.
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
     * Determina si un número es perfecto.
     *
     * Un número perfecto es aquel cuya suma de divisores propios
     * es igual al mismo número.
     *
     * @param numero número que se desea verificar.
     * @return true si el número es perfecto, false en caso contrario.
     */
    public boolean esNumeroPerfecto(long numero) {

        if (numero <= 1) {
            return false;
        }

        long sumaDivisores = 1;

        for (long divisor = 2; divisor <= numero / 2; divisor++) {
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
     * @return true si el teléfono representa un número perfecto.
     */
    public boolean telefonoEsNumeroPerfecto(Cliente cliente) {

        try {
            long telefono = Long.parseLong(cliente.getTelefono());
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