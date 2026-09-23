package co.edu.uniquindio.parcial1gimnasio.model;

public class PlanPersonalizado extends Plan {

    private int cantidadSesiones;
    private String especialidadRequerida;
    private String objetivosCliente;

    public PlanPersonalizado(String codigo, String nombre, String descripcion,
                             int duracionMeses, double valorMensual,
                             int cantidadSesiones, String especialidadRequerida,
                             String objetivosCliente) {

        super(codigo, nombre, descripcion, duracionMeses, valorMensual);

        this.cantidadSesiones = cantidadSesiones;
        this.especialidadRequerida = especialidadRequerida;
        this.objetivosCliente = objetivosCliente;

        agregarBeneficio(BeneficioPlan.ACCESO_ZONAS_DEPORTIVAS);
        agregarBeneficio(BeneficioPlan.ACOMPANAMIENTO_ENTRENADOR);
    }

    @Override
    public double calcularValor() {
        return getValorMensual() * getDuracionMeses();
    }

    public int getCantidadSesiones() {
        return cantidadSesiones;
    }

    public void setCantidadSesiones(int cantidadSesiones) {
        this.cantidadSesiones = cantidadSesiones;
    }

    public String getEspecialidadRequerida() {
        return especialidadRequerida;
    }

    public void setEspecialidadRequerida(String especialidadRequerida) {
        this.especialidadRequerida = especialidadRequerida;
    }

    public String getObjetivosCliente() {
        return objetivosCliente;
    }

    public void setObjetivosCliente(String objetivosCliente) {
        this.objetivosCliente = objetivosCliente;
    }
}