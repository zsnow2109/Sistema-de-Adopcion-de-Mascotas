package adopcion;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AdopcionServiceTest {
    
    private AdopcionService servicio;
    private Mascota mascota1;
    private Mascota mascota2;
    private Familia familia1;
    private Familia familia2;
    
    @Before
    public void setUp() {
        servicio = new AdopcionServiceImpl();
        
        mascota1 = new Mascota("Max", "perro", 3, true);
        mascota2 = new Mascota("Luna", "gato", 2, true);
        
        familia1 = new Familia("Pérez", "555-1234", Arrays.asList("perro", "gato"));
        familia2 = new Familia("Gómez", "555-5678", Arrays.asList("loro"));
    }

    @Test
    public void testAgregarMascota() {
        servicio.agregarMascota(mascota1);
        List<String> mascotas = servicio.obtenerMascotasDisponibles();
        assertTrue(mascotas.toString().contains("Max"));
    }

    @Test
    public void testAgregarFamilia() {
        servicio.agregarFamilia(familia1);
        List<String> familias = servicio.obtenerFamilias();
        assertTrue(familias.toString().contains("Pérez"));
    }

    @Test
    public void testObtenerMascotasDisponibles() {
        servicio.agregarMascota(mascota1);
        servicio.agregarMascota(mascota2);
        
        List<String> disponibles = servicio.obtenerMascotasDisponibles();
        assertEquals(2, disponibles.size());
        assertTrue(disponibles.toString().contains("Max"));
        assertTrue(disponibles.toString().contains("Luna"));
    }

    @Test
    public void testObtenerFamilias() {
        servicio.agregarFamilia(familia1);
        servicio.agregarFamilia(familia2);
        
        List<String> familias = servicio.obtenerFamilias();
        assertEquals(2, familias.size());
        assertTrue(familias.toString().contains("Pérez"));
        assertTrue(familias.toString().contains("Gómez"));
    }

    @Test
    public void testObtenerSeguimientoAdopciones() {
        servicio.agregarMascota(mascota1);
        servicio.agregarFamilia(familia1);
        servicio.emparejar(); 
        
        List<String> seguimiento = servicio.obtenerSeguimientoAdopciones();
        assertFalse(seguimiento.isEmpty());
        assertTrue(seguimiento.get(0).contains("ha adoptado a"));
    }

    @Test
    public void testEmparejar() {
        servicio.agregarMascota(mascota1); 
        servicio.agregarMascota(mascota2); 
        servicio.agregarFamilia(familia1); 
        
        String resultado = servicio.emparejar();
        assertTrue(resultado.contains("ha adoptado a"));
        
        List<String> disponibles = servicio.obtenerMascotasDisponibles();
        assertFalse(disponibles.toString().contains(resultado.split("ha adoptado a")[1].trim()));
    }
    
    @Test
    public void testEmparejarSinCompatibilidad() {
        servicio.agregarMascota(mascota1); 
        servicio.agregarFamilia(familia2); 
        
        String resultado = servicio.emparejar();
        assertEquals("No se encontraron emparejamientos disponibles.", resultado);
    }
}

