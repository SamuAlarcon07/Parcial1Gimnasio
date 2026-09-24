package co.edu.uniquindio.parcial1gimnasio.factory;

import co.edu.uniquindio.parcial1gimnasio.model.Plan;
import co.edu.uniquindio.parcial1gimnasio.model.PlanPremium;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FactoryPlanPremiumTest {

    @Test
    void debeCrearUnPlanPremium() {

        FactoryPlan factory = new FactoryPlanPremium(
                "P002",
                "Plan Premium",
                "Plan premium del gimnasio",
                3,
                150000
        );

        Plan plan = factory.crearPlan();

        assertNotNull(plan);
        assertTrue(plan instanceof PlanPremium);
    }
}