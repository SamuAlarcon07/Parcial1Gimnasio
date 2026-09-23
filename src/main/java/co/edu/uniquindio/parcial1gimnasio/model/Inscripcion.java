package co.edu.uniquindio.parcial1gimnasio.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Inscripcion {

    private Cliente cliente;
    private Plan plan;
    private LocalDate fechaInscripcion;
    private List<ServicioAdicional> serviciosAdicionales;
    private Entrenador entrenador;

    public Inscripcion(Cliente cliente, Plan plan, LocalDate fechaInscripcion) {
        this.cliente = cliente;
        this.plan = plan;
        this.fechaInscripcion = fechaInscripcion;
        this.serviciosAdicionales = new ArrayList<>();
    }

    public void agregarServicio(ServicioAdicional servicio) {

        if (!servicio.isDisponible()) {
            throw new IllegalArgumentException(
                    "El servicio adicional no está disponible."
            );
        }

        serviciosAdicionales.add(servicio);
    }

    public void eliminarServicio(ServicioAdicional servicio) {
        serviciosAdicionales.remove(servicio);
    }

    public void asignarEntrenador(Entrenador entrenador) {

        if (!(plan instanceof PlanPersonalizado)) {
            throw new IllegalStateException(
                    "Solo los planes personalizados pueden tener un entrenador asignado."
            );
        }

        this.entrenador = entrenador;
    }

    public double calcularValorTotal() {

        double total = plan.calcularValor();

        for (ServicioAdicional servicio : serviciosAdicionales) {
            total += servicio.getPrecio();
        }

        if (plan instanceof PlanPersonalizado && entrenador != null) {

            PlanPersonalizado planPersonalizado =
                    (PlanPersonalizado) plan;

            total += planPersonalizado.getCantidadSesiones()
                    * entrenador.getTarifaPorSesion();
        }

        return total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public List<ServicioAdicional> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    public void setServiciosAdicionales(
            List<ServicioAdicional> serviciosAdicionales) {

        this.serviciosAdicionales = serviciosAdicionales;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }
}