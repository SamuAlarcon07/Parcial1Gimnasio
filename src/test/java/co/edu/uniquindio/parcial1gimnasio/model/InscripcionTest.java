package co.edu.uniquindio.parcial1gimnasio.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class InscripcionTest {

    @Test
    void debeAgregarServicioDisponible() {

        Cliente cliente = new Cliente(
                "Samuel Giraldo",
                "123456789",
                "3001234567",
                "samuel@gmail.com",
                20,
                LocalDate.now()
        );

        Plan plan = new PlanBasico(
                "P001",
                "Plan Básico",
                "Plan básico del gimnasio",
                3,
                100000
        );

        Inscripcion inscripcion = new Inscripcion(
                cliente,
                plan,
                LocalDate.now()
        );

        ServicioAdicional servicio = new ServicioAdicional(
                "S001",
                "Sauna",
                "Acceso al sauna",
                20000,
                true
        );

        inscripcion.agregarServicio(servicio);

        assertEquals(1, inscripcion.getServiciosAdicionales().size());
        assertTrue(inscripcion.getServiciosAdicionales().contains(servicio));
    }


    @Test
    void noDebeAgregarServicioNoDisponible() {

        Cliente cliente = new Cliente(
                "Samuel Giraldo",
                "123456789",
                "3001234567",
                "samuel@gmail.com",
                20,
                LocalDate.now()
        );

        Plan plan = new PlanBasico(
                "P001",
                "Plan Básico",
                "Plan básico del gimnasio",
                3,
                100000
        );

        Inscripcion inscripcion = new Inscripcion(
                cliente,
                plan,
                LocalDate.now()
        );

        ServicioAdicional servicio = new ServicioAdicional(
                "S001",
                "Sauna",
                "Acceso al sauna",
                20000,
                false
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> inscripcion.agregarServicio(servicio)
        );
    }


    @Test
    void debeEliminarServicio() {

        Cliente cliente = new Cliente(
                "Samuel Giraldo",
                "123456789",
                "3001234567",
                "samuel@gmail.com",
                20,
                LocalDate.now()
        );

        Plan plan = new PlanBasico(
                "P001",
                "Plan Básico",
                "Plan básico del gimnasio",
                3,
                100000
        );

        Inscripcion inscripcion = new Inscripcion(
                cliente,
                plan,
                LocalDate.now()
        );

        ServicioAdicional servicio = new ServicioAdicional(
                "S001",
                "Sauna",
                "Acceso al sauna",
                20000,
                true
        );

        inscripcion.agregarServicio(servicio);
        inscripcion.eliminarServicio(servicio);

        assertEquals(0, inscripcion.getServiciosAdicionales().size());
        assertFalse(inscripcion.getServiciosAdicionales().contains(servicio));
    }


    @Test
    void debeAsignarEntrenadorAPlanPersonalizado() {

        Cliente cliente = new Cliente(
                "Samuel Giraldo",
                "123456789",
                "3001234567",
                "samuel@gmail.com",
                20,
                LocalDate.now()
        );

        PlanPersonalizado plan = new PlanPersonalizado(
                "P002",
                "Plan Personalizado",
                "Plan personalizado",
                3,
                150000,
                10,
                "Musculación",
                "Aumentar fuerza"
        );

        Inscripcion inscripcion = new Inscripcion(
                cliente,
                plan,
                LocalDate.now()
        );

        Entrenador entrenador = new Entrenador(
                "E001",
                "Carlos",
                "Musculación",
                "3101234567",
                30000
        );

        inscripcion.asignarEntrenador(entrenador);

        assertSame(entrenador, inscripcion.getEntrenador());
    }


    @Test
    void noDebeAsignarEntrenadorAPlanNoPersonalizado() {

        Cliente cliente = new Cliente(
                "Samuel Giraldo",
                "123456789",
                "3001234567",
                "samuel@gmail.com",
                20,
                LocalDate.now()
        );

        Plan plan = new PlanBasico(
                "P001",
                "Plan Básico",
                "Plan básico del gimnasio",
                3,
                100000
        );

        Inscripcion inscripcion = new Inscripcion(
                cliente,
                plan,
                LocalDate.now()
        );

        Entrenador entrenador = new Entrenador(
                "E001",
                "Carlos",
                "Musculación",
                "3101234567",
                30000
        );

        assertThrows(
                IllegalStateException.class,
                () -> inscripcion.asignarEntrenador(entrenador)
        );
    }


    @Test
    void debeCalcularValorTotal() {

        Cliente cliente = new Cliente(
                "Samuel Giraldo",
                "123456789",
                "3001234567",
                "samuel@gmail.com",
                20,
                LocalDate.now()
        );

        Plan plan = new PlanBasico(
                "P001",
                "Plan Básico",
                "Plan básico del gimnasio",
                3,
                100000
        );

        Inscripcion inscripcion = new Inscripcion(
                cliente,
                plan,
                LocalDate.now()
        );

        ServicioAdicional servicio = new ServicioAdicional(
                "S001",
                "Sauna",
                "Acceso al sauna",
                20000,
                true
        );

        inscripcion.agregarServicio(servicio);

        double valorTotal = inscripcion.calcularValorTotal();

        assertEquals(320000, valorTotal);
    }
}