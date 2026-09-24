package co.edu.uniquindio.parcial1gimnasio.factory;

import co.edu.uniquindio.parcial1gimnasio.model.Plan;
import co.edu.uniquindio.parcial1gimnasio.model.PlanBasico;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FactoryPlanBasicoTest {

    @Test
    void debeCrearUnPlanBasico() {

        FactoryPlan factory = new FactoryPlanBasico(
                "P001",
                "Plan Básico",
                "Plan básico del gimnasio",
                3,
                100000
        );

        Plan plan = factory.crearPlan();

        assertNotNull(plan);
        assertTrue(plan instanceof PlanBasico);
    }
}