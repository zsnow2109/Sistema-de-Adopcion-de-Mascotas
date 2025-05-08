package adopcion;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AdopcionServiceImplTest {
    
    private AdopcionServiceImpl servicio;
    private Mascota mascotaPerro;
    private Mascota mascotaGato;
    private Familia familiaConPreferencias;
    private Familia familiaSinCompatibilidad;
    
    @Before
    public void setUp() {
        servicio = new AdopcionServiceImpl();
        
  
        mascotaPerro = new Mascota("Max", "perro", 3, true);
        mascotaGato = new Mascota("Luna", "gato", 2, true);
        
        familiaConPreferencias = new Familia("Pérez", "555-1234", Arrays.asList("perro", "gato"));
        familiaSinCompatibilidad = new Familia("Gómez", "555-5678", Arrays.asList("loro"));
    }

    @Test
    public void testAgregarMascota() {
        servicio.agregarMascota(mascotaPerro);
        List<String> mascotas = servicio.obtenerMascotasDisponibles();
        assertEquals(1, mascotas.size());
        assertTrue(mascotas.get(0).contains("Max"));
    }

    @Test
    public void testAgregarFamilia() {
        servicio.agregarFamilia(familiaConPreferencias);
        List<String> familias = servicio.obtenerFamilias();
        assertEquals(1, familias.size());
        assertTrue(familias.get(0).contains("Pérez"));
    }

    @Test
    public void testObtenerMascotasDisponibles() {

        List<String> disponibles = servicio.obtenerMascotasDisponibles();
        assertTrue(disponibles.isEmpty());

        servicio.agregarMascota(mascotaPerro);
        disponibles = servicio.obtenerMascotasDisponibles();
        assertEquals(1, disponibles.size());
    }

    @Test
    public void testObtenerFamilias() {

        List<String> familias = servicio.obtenerFamilias();
        assertTrue(familias.isEmpty());
        

        servicio.agregarFamilia(familiaConPreferencias);
        familias = servicio.obtenerFamilias();
        assertEquals(1, familias.size());
    }

    @Test
    public void testObtenerSeguimientoAdopciones() {

        List<String> seguimiento = servicio.obtenerSeguimientoAdopciones();
        assertTrue(seguimiento.isEmpty());

        servicio.agregarMascota(mascotaPerro);
        servicio.agregarFamilia(familiaConPreferencias);
        servicio.emparejar();
        
        seguimiento = servicio.obtenerSeguimientoAdopciones();
        assertEquals(1, seguimiento.size());
        assertTrue(seguimiento.get(0).contains("ha adoptado a"));
    }

    @Test
    public void testEmparejarConExito() {
        servicio.agregarMascota(mascotaPerro);
        servicio.agregarFamilia(familiaConPreferencias);
        
        String resultado = servicio.emparejar();
        assertTrue(resultado.contains("ha adoptado a"));
        
        List<String> disponibles = servicio.obtenerMascotasDisponibles();
        assertTrue(disponibles.isEmpty());
    }
    
    @Test
    public void testEmparejarSinCompatibilidad() {
        servicio.agregarMascota(mascotaPerro);
        servicio.agregarFamilia(familiaSinCompatibilidad);
        
        String resultado = servicio.emparejar();
        assertEquals("No se encontraron emparejamientos disponibles.", resultado);
    }
    
    @Test
    public void testEmparejarCuandoFamiliaYaAdopto() {
        servicio.agregarMascota(mascotaPerro);
        servicio.agregarMascota(mascotaGato);
        servicio.agregarFamilia(familiaConPreferencias);
        
        String primerResultado = servicio.emparejar();
        assertTrue(primerResultado.contains("ha adoptado a"));
        
        String segundoResultado = servicio.emparejar();
        assertEquals("No se encontraron emparejamientos disponibles.", segundoResultado);
    }
}