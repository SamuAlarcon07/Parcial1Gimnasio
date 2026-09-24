package co.edu.uniquindio.parcial1gimnasio.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlanPersonalizadoTest {

    @Test
    void debeCalcularValorCorrectamente() {

        PlanPersonalizado plan = new PlanPersonalizado(
                "P003",
                "Plan Personalizado",
                "Plan de entrenamiento personalizado",
                3,
                150000,
                10,
                "Musculación",
                "Aumentar masa muscular"
        );

        double resultado = plan.calcularValor();

        assertEquals(450000, resultado);
    }

    @Test
    void debeGuardarDatosEspecificosDelPlan() {

        PlanPersonalizado plan = new PlanPersonalizado(
                "P003",
                "Plan Personalizado",
                "Plan de entrenamiento personalizado",
                3,
                150000,
                10,
                "Musculación",
                "Aumentar masa muscular"
        );

        assertEquals(10, plan.getCantidadSesiones());
        assertEquals("Musculación", plan.getEspecialidadRequerida());
        assertEquals(
                "Aumentar masa muscular",
                plan.getObjetivosCliente()
        );
    }

    @Test
    void debeTenerLosBeneficiosCorrectos() {

        PlanPersonalizado plan = new PlanPersonalizado(
                "P003",
                "Plan Personalizado",
                "Plan de entrenamiento personalizado",
                3,
                150000,
                10,
                "Musculación",
                "Aumentar masa muscular"
        );

        assertTrue(
                plan.getBeneficios().contains(
                        BeneficioPlan.ACCESO_ZONAS_DEPORTIVAS
                )
        );

        assertTrue(
                plan.getBeneficios().contains(
                        BeneficioPlan.ACOMPANAMIENTO_ENTRENADOR
                )
        );
    }
}