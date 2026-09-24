package co.edu.uniquindio.parcial1gimnasio.controller;

import co.edu.uniquindio.parcial1gimnasio.model.Entrenador;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador encargado de gestionar los entrenadores de SmartGym.
 *
 * Incluye las operaciones CRUD:
 * Crear, consultar, actualizar y eliminar entrenadores.
 */
public class EntrenadorController {

    private List<Entrenador> entrenadores;

    public EntrenadorController() {
        entrenadores = new ArrayList<>();
    }

    /**
     * Registra un nuevo entrenador.
     *
     * @param entrenador entrenador que se desea registrar.
     */
    public void registrarEntrenador(Entrenador entrenador) {
        entrenadores.add(entrenador);
    }

    /**
     * Busca un entrenador por su identificación.
     *
     * @param identificacion identificación del entrenador.
     * @return entrenador encontrado o null si no existe.
     */
    public Entrenador buscarPorIdentificacion(
            String identificacion) {

        for (Entrenador entrenador : entrenadores) {

            if (entrenador.getIdentificacion()
                    .equals(identificacion)) {

                return entrenador;
            }
        }

        return null;
    }

    /**
     * Verifica si una identificación ya está registrada
     * en otro entrenador.
     *
     * @param identificacion identificación que se desea comprobar.
     * @param entrenadorActual entrenador que se está editando.
     * @return true si la identificación ya pertenece
     *         a otro entrenador.
     */
    public boolean identificacionYaRegistrada(
            String identificacion,
            Entrenador entrenadorActual) {

        for (Entrenador entrenador : entrenadores) {

            if (entrenador != entrenadorActual
                    && entrenador.getIdentificacion()
                    .equals(identificacion)) {

                return true;
            }
        }

        return false;
    }

    /**
     * Actualiza los datos de un entrenador existente.
     *
     * @param entrenador entrenador que se desea actualizar.
     * @param identificacion nueva identificación.
     * @param nombre nuevo nombre.
     * @param especialidad nueva especialidad.
     * @param telefono nuevo teléfono.
     * @param tarifaPorSesion nueva tarifa por sesión.
     */
    public void actualizarEntrenador(
            Entrenador entrenador,
            String identificacion,
            String nombre,
            String especialidad,
            String telefono,
            double tarifaPorSesion) {

        entrenador.setIdentificacion(identificacion);
        entrenador.setNombre(nombre);
        entrenador.setEspecialidad(especialidad);
        entrenador.setTelefono(telefono);
        entrenador.setTarifaPorSesion(tarifaPorSesion);
    }

    /**
     * Elimina un entrenador.
     *
     * @param entrenador entrenador que se desea eliminar.
     * @return true si fue eliminado correctamente.
     */
    public boolean eliminarEntrenador(
            Entrenador entrenador) {

        return entrenadores.remove(entrenador);
    }

    public List<Entrenador> getEntrenadores() {
        return entrenadores;
    }

    public void setEntrenadores(
            List<Entrenador> entrenadores) {

        this.entrenadores = entrenadores;
    }
}