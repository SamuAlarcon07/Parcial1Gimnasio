package co.edu.uniquindio.parcial1gimnasio.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void debeAgregarUnaInscripcion() {

        Cliente cliente = new Cliente(
                "Samuel Giraldo",
                "123456789",
                "3001234567",
                "samuel@gmail.com",
                20,
                LocalDate.now()
        );

        Inscripcion inscripcion = new Inscripcion(
                cliente,
                null,
                LocalDate.now()
        );

        cliente.agregarInscripcion(inscripcion);

        assertEquals(1, cliente.getInscripciones().size());
        assertTrue(cliente.getInscripciones().contains(inscripcion));
    }

    @Test
    void debeEliminarUnaInscripcion() {

        Cliente cliente = new Cliente(
                "Samuel Giraldo",
                "123456789",
                "3001234567",
                "samuel@gmail.com",
                20,
                LocalDate.now()
        );

        Inscripcion inscripcion = new Inscripcion(
                cliente,
                null,
                LocalDate.now()
        );

        cliente.agregarInscripcion(inscripcion);
        cliente.eliminarInscripcion(inscripcion);

        assertEquals(0, cliente.getInscripciones().size());
        assertFalse(cliente.getInscripciones().contains(inscripcion));
    }
}