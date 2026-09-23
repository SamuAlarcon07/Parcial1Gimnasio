package co.edu.uniquindio.parcial1gimnasio.model;

public class PlanBasico extends Plan {

    public PlanBasico(String codigo, String nombre, String descripcion,
                      int duracionMeses, double valorMensual) {

        super(codigo, nombre, descripcion, duracionMeses, valorMensual);

        agregarBeneficio(BeneficioPlan.ACCESO_ZONAS_DEPORTIVAS);
    }

    @Override
    public double calcularValor() {
        return getValorMensual() * getDuracionMeses();
    }
}