package adopcion;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FamiliaTest {
    
    private Familia familia;
    private List<String> preferencias;
    
    @Before
    public void setUp() {
        preferencias = Arrays.asList("perro", "gato");
        familia = new Familia("Pérez", "555-1234", preferencias);
    }

    @Test
    public void testGetNombre() {
        String resultado = familia.getNombre();
        assertEquals("Pérez", resultado);
    }

    @Test
    public void testGetPreferencias() {
        List<String> resultado = familia.getPreferencias();
        assertEquals(preferencias, resultado);
        assertEquals(2, resultado.size());
        assertTrue(resultado.contains("perro"));
        assertTrue(resultado.contains("gato"));
    }

    @Test
    public void testHaAdoptadoInicialmenteFalse() {
        assertFalse(familia.haAdoptado());
    }

    @Test
    public void testMarcarComoAdoptante() {
        familia.marcarComoAdoptante();
        assertTrue(familia.haAdoptado());
    }

    @Test
    public void testToString() {
        String resultado = familia.toString();
        assertTrue(resultado.contains("Familia: Pérez"));
        assertTrue(resultado.contains("Contacto: 555-1234"));
        assertTrue(resultado.contains("Preferencias: [perro, gato]"));
        assertTrue(resultado.contains("Ya adoptó: No"));
        
        familia.marcarComoAdoptante();
        resultado = familia.toString();
        assertTrue(resultado.contains("Ya adoptó: Sí"));
    }
    
    @Test
    public void testConstructorConPreferenciasVacias() {
        Familia familiaSinPreferencias = new Familia("Gómez", "555-5678", Arrays.asList());
        assertTrue(familiaSinPreferencias.getPreferencias().isEmpty());
    }
}