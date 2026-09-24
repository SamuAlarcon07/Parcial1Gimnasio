package co.edu.uniquindio.parcial1gimnasio.controller;

import co.edu.uniquindio.parcial1gimnasio.model.Plan;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador encargado de gestionar los planes de SmartGym.
 *
 * Incluye las operaciones CRUD:
 * Crear, consultar, actualizar y eliminar planes.
 */
public class PlanController {

    private List<Plan> planes;

    public PlanController() {
        planes = new ArrayList<>();
    }

    /**
     * Registra un nuevo plan.
     *
     * @param plan plan que se desea registrar.
     */
    public void registrarPlan(Plan plan) {
        planes.add(plan);
    }

    /**
     * Busca un plan por su código.
     *
     * @param codigo código del plan.
     * @return plan encontrado o null si no existe.
     */
    public Plan buscarPorCodigo(String codigo) {

        for (Plan plan : planes) {

            if (plan.getCodigo().equals(codigo)) {
                return plan;
            }
        }

        return null;
    }

    /**
     * Verifica si un código ya pertenece a otro plan.
     *
     * @param codigo código que se desea comprobar.
     * @param planActual plan que se está editando.
     * @return true si el código pertenece a otro plan.
     */
    public boolean codigoYaRegistrado(
            String codigo,
            Plan planActual) {

        for (Plan plan : planes) {

            if (plan != planActual
                    && plan.getCodigo().equals(codigo)) {

                return true;
            }
        }

        return false;
    }

    /**
     * Actualiza un plan existente.
     *
     * @param plan plan que se desea actualizar.
     */
    public void actualizarPlan(Plan plan) {

        /*
         * El objeto ya contiene los nuevos datos gracias
         * a sus setters.
         *
         * No es necesario eliminarlo y volverlo a agregar.
         */
        if (!planes.contains(plan)) {
            planes.add(plan);
        }
    }

    /**
     * Elimina un plan.
     *
     * @param plan plan que se desea eliminar.
     * @return true si fue eliminado.
     */
    public boolean eliminarPlan(Plan plan) {
        return planes.remove(plan);
    }

    public List<Plan> getPlanes() {
        return planes;
    }

    public void setPlanes(List<Plan> planes) {
        this.planes = planes;
    }
}