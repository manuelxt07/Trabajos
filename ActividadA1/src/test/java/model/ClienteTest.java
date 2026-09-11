package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

class ClienteTest {
    private static final Logger LOG = Logger.getLogger(ClienteTest.class.getName());

    @Test
    void agregarBicicleta() {
        LOG.info("Inicio Test Agregar Bicicleta");

        Taller taller = new Taller("Taller");
        Cliente cliente = new Cliente("Manuel", taller, "1085915590", "3185454373", "Calle 4 N 4 - 16", new ArrayList<>());
        Bicicleta bici1 = new Bicicleta("Trek", "Rojo", "SOR1", 2022);
        Bicicleta bici2 = new Bicicleta("Giant", "Negro", "SEP2", 2023);

        cliente.agregarBicicleta(bici1);
        cliente.agregarBicicleta(bici2);

        List<Bicicleta> resultadoEsperado = new ArrayList<>();
        resultadoEsperado.add(bici1);
        resultadoEsperado.add(bici2);

        Assertions.assertIterableEquals(resultadoEsperado, cliente.getListaBicicletas());

        LOG.info("Fin Test Agregar Bicicleta");
    }
}