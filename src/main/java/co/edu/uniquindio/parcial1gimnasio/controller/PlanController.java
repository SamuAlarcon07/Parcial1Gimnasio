package co.edu.uniquindio.parcial1gimnasio.controller;

import co.edu.uniquindio.parcial1gimnasio.model.Plan;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador encargado de gestionar los planes de SmartGym.
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
     * Elimina un plan.
     *
     * @param plan plan que se desea eliminar.
     */
    public void eliminarPlan(Plan plan) {
        planes.remove(plan);
    }

    public List<Plan> getPlanes() {
        return planes;
    }

    public void setPlanes(List<Plan> planes) {
        this.planes = planes;
    }
}