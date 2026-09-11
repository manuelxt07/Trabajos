package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

class InventarioTest {
    private static final Logger LOG = Logger.getLogger(InventarioTest.class.getName());

    @Test
    void obtenerAlertasStock() {
        LOG.info("Inicio Test Obtener Alertas Stock");

        Taller taller = new Taller("Taller");
        Inventario i = new Inventario(taller);
        Repuesto r1 = new Repuesto("Pastillas", 5000, 2, 2, taller);
        Repuesto r2 = new Repuesto("Ruedas", 10000, 5, 2, taller);
        i.registrarRepuesto(r1);
        i.registrarRepuesto(r2);
        List<Repuesto> resultadoEsperado = new ArrayList<>();
        resultadoEsperado.add(r1);
        List<Repuesto> resultadoReal = i.obtenerAlertasStock();

        Assertions.assertIterableEquals(resultadoEsperado, resultadoReal);

        LOG.info("Fin Test Obtener Alertas Stock");
    }
}