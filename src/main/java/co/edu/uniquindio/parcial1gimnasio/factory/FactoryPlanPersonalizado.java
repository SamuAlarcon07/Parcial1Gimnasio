package co.edu.uniquindio.parcial1gimnasio.factory;

import co.edu.uniquindio.parcial1gimnasio.model.Plan;
import co.edu.uniquindio.parcial1gimnasio.model.PlanPersonalizado;

/**
 * Factory encargada de crear planes personalizados.
 */
public class FactoryPlanPersonalizado extends FactoryPlan {

    private String codigo;
    private String nombre;
    private String descripcion;
    private int duracionMeses;
    private double valorMensual;
    private int cantidadSesiones;
    private String especialidadRequerida;
    private String objetivosCliente;

    public FactoryPlanPersonalizado(
            String codigo,
            String nombre,
            String descripcion,
            int duracionMeses,
            double valorMensual,
            int cantidadSesiones,
            String especialidadRequerida,
            String objetivosCliente) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracionMeses = duracionMeses;
        this.valorMensual = valorMensual;
        this.cantidadSesiones = cantidadSesiones;
        this.especialidadRequerida = especialidadRequerida;
        this.objetivosCliente = objetivosCliente;
    }

    @Override
    public Plan crearPlan() {
        return new PlanPersonalizado(
                codigo,
                nombre,
                descripcion,
                duracionMeses,
                valorMensual,
                cantidadSesiones,
                especialidadRequerida,
                objetivosCliente
        );
    }
}