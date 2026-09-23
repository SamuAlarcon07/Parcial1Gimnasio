package co.edu.uniquindio.parcial1gimnasio;

import co.edu.uniquindio.parcial1gimnasio.factory.FactoryPlan;
import co.edu.uniquindio.parcial1gimnasio.factory.FactoryPlanBasico;
import co.edu.uniquindio.parcial1gimnasio.factory.FactoryPlanPersonalizado;
import co.edu.uniquindio.parcial1gimnasio.factory.FactoryPlanPremium;
import co.edu.uniquindio.parcial1gimnasio.model.BeneficioPlan;
import co.edu.uniquindio.parcial1gimnasio.model.Cliente;
import co.edu.uniquindio.parcial1gimnasio.model.Entrenador;
import co.edu.uniquindio.parcial1gimnasio.model.Gimnasio;
import co.edu.uniquindio.parcial1gimnasio.model.Inscripcion;
import co.edu.uniquindio.parcial1gimnasio.model.Plan;
import co.edu.uniquindio.parcial1gimnasio.model.ServicioAdicional;

import java.time.LocalDate;

/**
 * Clase principal para realizar las pruebas iniciales
 * de la aplicación SmartGym.
 */
public class App {

    public static void main(String[] args) {

        Gimnasio gimnasio = Gimnasio.getInstance(
                "SmartGym",
                "900123456-7",
                "Armenia, Quindío",
                "3001234567",
                "contacto@smartgym.com",
                "www.smartgym.com"
        );

        System.out.println("=== SMARTGYM ===");
        System.out.println("Gimnasio: " + gimnasio.getNombreComercial());
        System.out.println("NIT: " + gimnasio.getNit());

        Cliente cliente = new Cliente(
                "Samuel Alarcón",
                "1091234567",
                "3124567890",
                "samuel@email.com",
                18,
                LocalDate.now()
        );

        FactoryPlan factoryBasico = new FactoryPlanBasico(
                "B001",
                "Plan Básico",
                "Plan básico de entrenamiento",
                3,
                80000
        );

        Plan planBasico = factoryBasico.crearPlan();

        System.out.println("\n=== PLAN BÁSICO ===");
        System.out.println("Nombre: " + planBasico.getNombre());
        System.out.println("Valor: $" + planBasico.calcularValor());

        FactoryPlan factoryPremium = new FactoryPlanPremium(
                "P001",
                "Plan Premium",
                "Plan premium de entrenamiento",
                6,
                150000
        );

        Plan planPremium = factoryPremium.crearPlan();

        System.out.println("\n=== PLAN PREMIUM ===");
        System.out.println("Nombre: " + planPremium.getNombre());
        System.out.println("Valor: $" + planPremium.calcularValor());

        Entrenador entrenador = new Entrenador(
                "E001",
                "Carlos Pérez",
                "Musculación",
                "3109876543",
                30000
        );

        FactoryPlan factoryPersonalizado =
                new FactoryPlanPersonalizado(
                        "PP001",
                        "Plan Personalizado",
                        "Plan adaptado a los objetivos del cliente",
                        3,
                        200000,
                        8,
                        "Musculación",
                        "Aumentar fuerza y mejorar condición física"
                );

        Plan planPersonalizado = factoryPersonalizado.crearPlan();

        ServicioAdicional valoracionFisica =
                new ServicioAdicional(
                        "S001",
                        "Valoración física",
                        "Evaluación física del cliente",
                        40000,
                        true
                );

        Inscripcion inscripcion = new Inscripcion(
                cliente,
                planPersonalizado,
                LocalDate.now()
        );

        inscripcion.asignarEntrenador(entrenador);
        inscripcion.agregarServicio(valoracionFisica);

        cliente.agregarInscripcion(inscripcion);

        System.out.println("\n=== INSCRIPCIÓN ===");
        System.out.println("Cliente: "
                + inscripcion.getCliente().getNombreCompleto());

        System.out.println("Plan: "
                + inscripcion.getPlan().getNombre());

        System.out.println("Entrenador: "
                + inscripcion.getEntrenador().getNombre());

        System.out.println("Valor total: $"
                + inscripcion.calcularValorTotal());

        System.out.println("\n=== BENEFICIOS PLAN BÁSICO ===");

        for (BeneficioPlan beneficio : planBasico.getBeneficios()) {
            System.out.println("- " + beneficio);
        }

        System.out.println("\n=== PRUEBA FINALIZADA ===");
    }
}