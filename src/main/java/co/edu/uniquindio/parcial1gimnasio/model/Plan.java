package co.edu.uniquindio.parcial1gimnasio.model;

import java.util.HashSet;
import java.util.Set;

public abstract class Plan {

    private String codigo;
    private String nombre;
    private String descripcion;
    private int duracionMeses;
    private double valorMensual;
    private EstadoPlan estado;
    private Set<BeneficioPlan> beneficios;

    public Plan(String codigo, String nombre, String descripcion,
                int duracionMeses, double valorMensual) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracionMeses = duracionMeses;
        this.valorMensual = valorMensual;
        this.estado = EstadoPlan.ACTIVO;
        this.beneficios = new HashSet<>();
    }

    public abstract double calcularValor();

    public void agregarBeneficio(BeneficioPlan beneficio) {
        beneficios.add(beneficio);
    }

    public void eliminarBeneficio(BeneficioPlan beneficio) {
        beneficios.remove(beneficio);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDuracionMeses() {
        return duracionMeses;
    }

    public void setDuracionMeses(int duracionMeses) {
        this.duracionMeses = duracionMeses;
    }

    public double getValorMensual() {
        return valorMensual;
    }

    public void setValorMensual(double valorMensual) {
        this.valorMensual = valorMensual;
    }

    public EstadoPlan getEstado() {
        return estado;
    }

    public void setEstado(EstadoPlan estado) {
        this.estado = estado;
    }

    public Set<BeneficioPlan> getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(Set<BeneficioPlan> beneficios) {
        this.beneficios = beneficios;
    }
}