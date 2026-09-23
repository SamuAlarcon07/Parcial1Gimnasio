package co.edu.uniquindio.parcial1gimnasio.model;

public class PlanPremium extends Plan {

    public PlanPremium(String codigo, String nombre, String descripcion,
                       int duracionMeses, double valorMensual) {

        super(codigo, nombre, descripcion, duracionMeses, valorMensual);

        agregarBeneficio(BeneficioPlan.ACCESO_ZONAS_DEPORTIVAS);
        agregarBeneficio(BeneficioPlan.CLASES_GRUPALES);
        agregarBeneficio(BeneficioPlan.ACOMPANAMIENTO_ENTRENADOR);
    }

    @Override
    public double calcularValor() {
        return getValorMensual() * getDuracionMeses();
    }
}