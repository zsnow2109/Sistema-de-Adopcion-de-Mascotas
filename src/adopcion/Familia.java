package adopcion;

import java.util.List;

public class Familia {
    private String nombre;
    private String contacto;
    private List<String> preferencias;
    private boolean yaAdopto = false; // Nuevo campo

    public Familia(String nombre, String contacto, List<String> preferencias) {
        this.nombre = nombre;
        this.contacto = contacto;
        this.preferencias = preferencias;
    }

    public String getNombre() {
        return nombre;
    }

    public List<String> getPreferencias() {
        return preferencias;
    }

    public boolean haAdoptado() {
        return yaAdopto;
    }

    public void marcarComoAdoptante() {
        this.yaAdopto = true;
    }

    @Override
    public String toString() {
        return String.format("Familia: %s, Contacto: %s, Preferencias: %s, Ya adoptó: %s",
                nombre, contacto, preferencias, yaAdopto ? "Sí" : "No");
    }
}
