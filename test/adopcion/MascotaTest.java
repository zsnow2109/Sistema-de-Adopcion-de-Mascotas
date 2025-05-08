package adopcion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MascotaTest {
    
    private Mascota mascotaDisponible;
    private Mascota mascotaNoDisponible;
    
    @Before
    public void setUp() {
        mascotaDisponible = new Mascota("Max", "perro", 3, true);
        mascotaNoDisponible = new Mascota("Luna", "gato", 2, false);
    }

    @Test
    public void testMarcarComoAdoptada() {
        assertTrue(mascotaDisponible.isDisponible());
        mascotaDisponible.marcarComoAdoptada();
        assertFalse(mascotaDisponible.isDisponible());
    }

    @Test
    public void testIsDisponible() {
        assertTrue(mascotaDisponible.isDisponible());
        assertFalse(mascotaNoDisponible.isDisponible());
    }

    @Test
    public void testGetTipo() {
        assertEquals("perro", mascotaDisponible.getTipo());
        assertEquals("gato", mascotaNoDisponible.getTipo());
    }

    @Test
    public void testGetNombre() {
        assertEquals("Max", mascotaDisponible.getNombre());
        assertEquals("Luna", mascotaNoDisponible.getNombre());
    }

    @Test
    public void testToString() {
        String disponibleStr = mascotaDisponible.toString();
        assertTrue(disponibleStr.contains("Mascota: Max"));
        assertTrue(disponibleStr.contains("Tipo: perro"));
        assertTrue(disponibleStr.contains("Edad: 3 años"));
        assertTrue(disponibleStr.contains("Estado: Disponible"));
        
        String noDisponibleStr = mascotaNoDisponible.toString();
        assertTrue(noDisponibleStr.contains("Mascota: Luna"));
        assertTrue(noDisponibleStr.contains("Tipo: gato"));
        assertTrue(noDisponibleStr.contains("Edad: 2 años"));
        assertTrue(noDisponibleStr.contains("Estado: Adoptada"));
    }
    
    @Test
    public void testConstructorConParametros() {

        Mascota mascota = new Mascota("Rex", "perro", 5, true);
        assertEquals("Rex", mascota.getNombre());
        assertEquals("perro", mascota.getTipo());
        assertTrue(mascota.isDisponible());
    }
}