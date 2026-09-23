package co.edu.uniquindio.parcial1gimnasio.factory;

import co.edu.uniquindio.parcial1gimnasio.model.Plan;

/**
 * Clase base del patrón Factory Method para la creación de planes.
 */
public abstract class FactoryPlan {

    /**
     * Crea un tipo concreto de plan.
     *
     * @return plan creado.
     */
    public abstract Plan crearPlan();
}