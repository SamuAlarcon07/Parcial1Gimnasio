package co.edu.uniquindio.parcial1gimnasio.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlanPremiumTest {

    @Test
    void debeCalcularValorCorrectamente() {

        PlanPremium plan = new PlanPremium(
                "P002",
                "Plan Premium",
                "Plan premium del gimnasio",
                3,
                150000
        );

        double resultado = plan.calcularValor();

        assertEquals(450000, resultado);
    }

    @Test
    void debeTenerLosBeneficiosCorrectos() {

        PlanPremium plan = new PlanPremium(
                "P002",
                "Plan Premium",
                "Plan premium del gimnasio",
                3,
                150000
        );

        assertTrue(
                plan.getBeneficios().contains(
                        BeneficioPlan.ACCESO_ZONAS_DEPORTIVAS
                )
        );

        assertTrue(
                plan.getBeneficios().contains(
                        BeneficioPlan.CLASES_GRUPALES
                )
        );

        assertTrue(
                plan.getBeneficios().contains(
                        BeneficioPlan.ACOMPANAMIENTO_ENTRENADOR
                )
        );
    }
}