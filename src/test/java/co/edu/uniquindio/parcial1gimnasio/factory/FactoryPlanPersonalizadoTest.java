package co.edu.uniquindio.parcial1gimnasio.factory;

import co.edu.uniquindio.parcial1gimnasio.model.Plan;
import co.edu.uniquindio.parcial1gimnasio.model.PlanPersonalizado;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FactoryPlanPersonalizadoTest {

    @Test
    void debeCrearUnPlanPersonalizado() {

        FactoryPlan factory = new FactoryPlanPersonalizado(
                "P003",
                "Plan Personalizado",
                "Plan de entrenamiento personalizado",
                3,
                150000,
                10,
                "Musculación",
                "Aumentar masa muscular"
        );

        Plan plan = factory.crearPlan();

        assertNotNull(plan);
        assertTrue(plan instanceof PlanPersonalizado);
    }
}