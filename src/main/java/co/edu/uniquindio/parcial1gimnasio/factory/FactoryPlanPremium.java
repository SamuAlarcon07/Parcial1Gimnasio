package co.edu.uniquindio.parcial1gimnasio.factory;

import co.edu.uniquindio.parcial1gimnasio.model.Plan;
import co.edu.uniquindio.parcial1gimnasio.model.PlanPremium;

/**
 * Factory encargada de crear planes premium.
 */
public class FactoryPlanPremium extends FactoryPlan {

    private String codigo;
    private String nombre;
    private String descripcion;
    private int duracionMeses;
    private double valorMensual;

    public FactoryPlanPremium(String codigo, String nombre,
                              String descripcion, int duracionMeses,
                              double valorMensual) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracionMeses = duracionMeses;
        this.valorMensual = valorMensual;
    }

    @Override
    public Plan crearPlan() {
        return new PlanPremium(
                codigo,
                nombre,
                descripcion,
                duracionMeses,
                valorMensual
        );
    }
}