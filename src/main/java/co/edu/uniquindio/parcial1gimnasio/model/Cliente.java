package co.edu.uniquindio.parcial1gimnasio.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa un cliente registrado en SmartGym.
 *
 * Un cliente puede tener múltiples inscripciones durante
 * su permanencia en el gimnasio.
 */
public class Cliente {

    private String nombreCompleto;
    private String documento;
    private String telefono;
    private String email;
    private int edad;
    private LocalDate fechaRegistro;
    private List<Inscripcion> inscripciones;

    public Cliente(String nombreCompleto, String documento,
                   String telefono, String email, int edad,
                   LocalDate fechaRegistro) {

        this.nombreCompleto = nombreCompleto;
        this.documento = documento;
        this.telefono = telefono;
        this.email = email;
        this.edad = edad;
        this.fechaRegistro = fechaRegistro;
        this.inscripciones = new ArrayList<>();
    }

    /**
     * Registra una nueva inscripción para el cliente.
     *
     * @param inscripcion inscripción que se desea agregar.
     */
    public void agregarInscripcion(Inscripcion inscripcion) {
        inscripciones.add(inscripcion);
    }

    public void eliminarInscripcion(Inscripcion inscripcion) {
        inscripciones.remove(inscripcion);
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }
}