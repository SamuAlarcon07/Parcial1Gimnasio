package co.edu.uniquindio.parcial1gimnasio.controller;

import co.edu.uniquindio.parcial1gimnasio.model.Entrenador;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador encargado de gestionar los entrenadores de SmartGym.
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
    public Entrenador buscarPorIdentificacion(String identificacion) {

        for (Entrenador entrenador : entrenadores) {

            if (entrenador.getIdentificacion().equals(identificacion)) {
                return entrenador;
            }
        }

        return null;
    }

    /**
     * Elimina un entrenador.
     *
     * @param entrenador entrenador que se desea eliminar.
     */
    public void eliminarEntrenador(Entrenador entrenador) {
        entrenadores.remove(entrenador);
    }

    public List<Entrenador> getEntrenadores() {
        return entrenadores;
    }

    public void setEntrenadores(List<Entrenador> entrenadores) {
        this.entrenadores = entrenadores;
    }
}