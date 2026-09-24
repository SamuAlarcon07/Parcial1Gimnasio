package co.edu.uniquindio.parcial1gimnasio.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GimnasioTest {

    @Test
    void UnaUnicaInstancia() {

        Gimnasio gimnasio1 = Gimnasio.getInstance(
                "SmartGym",
                "123456789",
                "Armenia",
                "3001234567",
                "smartgym@gmail.com",
                "www.smartgym.com"
        );

        Gimnasio gimnasio2 = Gimnasio.getInstance(
                "Otro Gimnasio",
                "987654321",
                "Bogotá",
                "3119876543",
                "otro@gmail.com",
                "www.otrogimnasio.com"
        );

        assertSame(gimnasio1, gimnasio2);
    }
}