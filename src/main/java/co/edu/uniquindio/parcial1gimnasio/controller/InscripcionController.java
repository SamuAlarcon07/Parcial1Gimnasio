package co.edu.uniquindio.parcial1gimnasio.controller;

import co.edu.uniquindio.parcial1gimnasio.model.Inscripcion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador encargado de gestionar las inscripciones
 * realizadas en SmartGym.
 *
 * Incluye las operaciones CRUD:
 * Crear, consultar, actualizar y eliminar inscripciones.
 */
public class InscripcionController {

    private List<Inscripcion> inscripciones;

    public InscripcionController() {
        inscripciones = new ArrayList<>();
    }

    /**
     * Registra una nueva inscripción.
     *
     * @param inscripcion inscripción que se desea registrar.
     */
    public void registrarInscripcion(
            Inscripcion inscripcion) {

        inscripciones.add(inscripcion);

        if (!inscripcion.getCliente()
                .getInscripciones()
                .contains(inscripcion)) {

            inscripcion.getCliente()
                    .agregarInscripcion(inscripcion);
        }
    }

    /**
     * Elimina una inscripción.
     *
     * También elimina la inscripción de la lista
     * de inscripciones del cliente.
     *
     * @param inscripcion inscripción que se desea eliminar.
     * @return true si fue eliminada correctamente.
     */
    public boolean eliminarInscripcion(
            Inscripcion inscripcion) {

        if (inscripcion == null) {
            return false;
        }

        boolean eliminada =
                inscripciones.remove(inscripcion);

        if (eliminada
                && inscripcion.getCliente() != null) {

            inscripcion.getCliente()
                    .eliminarInscripcion(inscripcion);
        }

        return eliminada;
    }

    /**
     * Calcula los ingresos generados por las inscripciones
     * realizadas dentro de un período determinado.
     *
     * @param fechaInicio fecha inicial del período.
     * @param fechaFin fecha final del período.
     * @return valor total de los ingresos.
     */
    public double calcularIngresosPorPeriodo(
            LocalDate fechaInicio,
            LocalDate fechaFin) {

        double totalIngresos = 0;

        for (Inscripcion inscripcion :
                inscripciones) {

            LocalDate fecha =
                    inscripcion.getFechaInscripcion();

            if (!fecha.isBefore(fechaInicio)
                    && !fecha.isAfter(fechaFin)) {

                totalIngresos +=
                        inscripcion.calcularValorTotal();
            }
        }

        return totalIngresos;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(
            List<Inscripcion> inscripciones) {

        this.inscripciones = inscripciones;
    }
}