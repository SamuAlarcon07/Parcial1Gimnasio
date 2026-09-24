package co.edu.uniquindio.parcial1gimnasio.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlanBasicoTest {

    @Test
    void debeCalcularValorCorrectamente() {

        PlanBasico plan = new PlanBasico(
                "P001",
                "Plan Básico",
                "Plan básico del gimnasio",
                3,
                100000
        );

        double resultado = plan.calcularValor();

        assertEquals(300000, resultado);
    }

    @Test
    void debeTenerBeneficioDeAccesoAZonasDeportivas() {

        PlanBasico plan = new PlanBasico(
                "P001",
                "Plan Básico",
                "Plan básico del gimnasio",
                3,
                100000
        );

        assertTrue(
                plan.getBeneficios().contains(
                        BeneficioPlan.ACCESO_ZONAS_DEPORTIVAS
                )
        );
    }
}