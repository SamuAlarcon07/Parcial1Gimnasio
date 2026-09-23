package co.edu.uniquindio.parcial1gimnasio.controller;

import co.edu.uniquindio.parcial1gimnasio.model.ServicioAdicional;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador encargado de gestionar los servicios adicionales
 * de SmartGym.
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
    public void registrarServicio(ServicioAdicional servicio) {
        servicios.add(servicio);
    }

    /**
     * Busca un servicio por su código.
     *
     * @param codigo código del servicio.
     * @return servicio encontrado o null si no existe.
     */
    public ServicioAdicional buscarPorCodigo(String codigo) {

        for (ServicioAdicional servicio : servicios) {

            if (servicio.getCodigo().equals(codigo)) {
                return servicio;
            }
        }

        return null;
    }

    /**
     * Elimina un servicio adicional.
     *
     * @param servicio servicio que se desea eliminar.
     */
    public void eliminarServicio(ServicioAdicional servicio) {
        servicios.remove(servicio);
    }

    public List<ServicioAdicional> getServicios() {
        return servicios;
    }

    public void setServicios(List<ServicioAdicional> servicios) {
        this.servicios = servicios;
    }
}