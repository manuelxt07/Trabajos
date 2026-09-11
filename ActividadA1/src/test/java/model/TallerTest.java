package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

class TallerTest {
    // 1. Corregido el nombre del Logger
    private static final Logger LOG = Logger.getLogger(TallerTest.class.getName());

    @Test
    void registrarCliente() {
        LOG.info("Inicio Test Registrar Cliente");

        Taller taller = new Taller("Taller");
        Cliente c1 = new Cliente("Manuel", taller, "1085915590", "3185454373", "Caller 4 N 4 - 16", null);
        Cliente c2 = new Cliente("Emily", taller, "1083450", "312345673", "Caller 5 N 3 - 12", null);
        taller.registrarCliente(c1);
        taller.registrarCliente(c2);
        List<Individuo> resultadoEsperado = new ArrayList<>();
        resultadoEsperado.add(c1);
        resultadoEsperado.add(c2);
        Assertions.assertIterableEquals(resultadoEsperado, taller.getListaIndividuos());

        LOG.info("Fin Test Registrar Cliente");
    }
}