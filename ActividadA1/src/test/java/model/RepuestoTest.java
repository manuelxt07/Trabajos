package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


class RepuestoTest {
    private static final Logger LOG = Logger.getLogger(RepuestoTest.class.getName());

    @Test
    void calcularCostoTotal() {
        LOG.info("Inicio Test Calcular Costo Total");

        Taller taller = new Taller("Taller");
        Repuesto repuesto = new Repuesto("Cadena", 30000, 10, 5, taller);

        String resultadoEsperado = "El costo total del repuesto es " + (30000 * 10);
        String resultadoReal = repuesto.calcularCostoTotal();

        Assertions.assertEquals(resultadoEsperado, resultadoReal);

        LOG.info("Fin Test Calcular Costo Total");
    }
}