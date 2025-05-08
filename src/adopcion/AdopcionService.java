package adopcion;

import java.util.List;

public interface AdopcionService {
    void agregarMascota(Mascota mascota);
    void agregarFamilia(Familia familia);
    List<String> obtenerMascotasDisponibles();
    List<String> obtenerFamilias();
    List<String> obtenerSeguimientoAdopciones();
    String emparejar();
}
