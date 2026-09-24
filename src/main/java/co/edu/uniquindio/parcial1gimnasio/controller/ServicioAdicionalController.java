package co.edu.uniquindio.parcial1gimnasio.controller;

import co.edu.uniquindio.parcial1gimnasio.model.ServicioAdicional;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador encargado de gestionar los servicios adicionales
 * de SmartGym.
 *
 * Incluye las operaciones CRUD:
 * Crear, consultar, actualizar y eliminar servicios.
 */
public class ServicioAdicionalController {

    private List<ServicioAdicional> servicios;

    public ServicioAdicionalController() {
        servicios = new ArrayList<>();
    }

    /**
     * Registra un nuevo servicio adicional.
     *
     * @param servicio servicio que se desea registrar.
     */
    public void registrarServicio(
            ServicioAdicional servicio) {

        servicios.add(servicio);
    }

    /**
     * Busca un servicio por su código.
     *
     * @param codigo código del servicio.
     * @return servicio encontrado o null si no existe.
     */
    public ServicioAdicional buscarPorCodigo(
            String codigo) {

        for (ServicioAdicional servicio : servicios) {

            if (servicio.getCodigo().equals(codigo)) {
                return servicio;
            }
        }

        return null;
    }

    /**
     * Verifica si un código ya está registrado
     * en otro servicio.
     *
     * @param codigo código que se desea comprobar.
     * @param servicioActual servicio que se está editando.
     * @return true si el código pertenece a otro servicio.
     */
    public boolean codigoYaRegistrado(
            String codigo,
            ServicioAdicional servicioActual) {

        for (ServicioAdicional servicio : servicios) {

            if (servicio != servicioActual
                    && servicio.getCodigo().equals(codigo)) {

                return true;
            }
        }

        return false;
    }

    /**
     * Actualiza los datos de un servicio.
     *
     * @param servicio servicio que se desea actualizar.
     * @param codigo nuevo código.
     * @param nombre nuevo nombre.
     * @param descripcion nueva descripción.
     * @param precio nuevo precio.
     * @param disponible disponibilidad del servicio.
     */
    public void actualizarServicio(
            ServicioAdicional servicio,
            String codigo,
            String nombre,
            String descripcion,
            double precio,
            boolean disponible) {

        servicio.setCodigo(codigo);
        servicio.setNombre(nombre);
        servicio.setDescripcion(descripcion);
        servicio.setPrecio(precio);
        servicio.setDisponible(disponible);
    }

    /**
     * Elimina un servicio adicional.
     *
     * @param servicio servicio que se desea eliminar.
     * @return true si fue eliminado correctamente.
     */
    public boolean eliminarServicio(
            ServicioAdicional servicio) {

        return servicios.remove(servicio);
    }

    public List<ServicioAdicional> getServicios() {
        return servicios;
    }

    public void setServicios(
            List<ServicioAdicional> servicios) {

        this.servicios = servicios;
    }
}