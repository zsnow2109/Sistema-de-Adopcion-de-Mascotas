package adopcion;

import java.util.ArrayList;
import java.util.List;

public class AdopcionServiceImpl implements AdopcionService {
    private List<Mascota> mascotas = new ArrayList<>();
    private List<Familia> familias = new ArrayList<>();
    private List<String> adopciones = new ArrayList<>();

    @Override
    public void agregarMascota(Mascota mascota) {
        mascotas.add(mascota);
    }

    @Override
    public void agregarFamilia(Familia familia) {
        familias.add(familia);
    }

    @Override
    public List<String> obtenerMascotasDisponibles() {
        List<String> disponibles = new ArrayList<>();
        for (Mascota m : mascotas) {
            if (m.isDisponible()) {
                disponibles.add(m.toString());
            }
        }
        return disponibles;
    }

    @Override
    public List<String> obtenerFamilias() {
        List<String> datos = new ArrayList<>();
        for (Familia f : familias) {
            datos.add(f.toString());
        }
        return datos;
    }

    @Override
    public List<String> obtenerSeguimientoAdopciones() {
        List<String> seg = new ArrayList<>();
        for (String a : adopciones) {
            seg.add(a + ". Seguimiento necesario.");
        }
        return seg;
    }

    @Override
    public String emparejar() {
        for (Familia familia : familias) {
            if (familia.haAdoptado()) continue; // Evita repetir adopciones

            for (Mascota mascota : mascotas) {
                if (mascota.isDisponible() && familia.getPreferencias().contains(mascota.getTipo())) {
                    mascota.marcarComoAdoptada();
                    familia.marcarComoAdoptante(); // Marca a la familia como adoptante
                    String adopcion = familia.getNombre() + " ha adoptado a " + mascota.getNombre();
                    adopciones.add(adopcion);
                    return adopcion;
                }
            }
        }
        return "No se encontraron emparejamientos disponibles.";
    }
}