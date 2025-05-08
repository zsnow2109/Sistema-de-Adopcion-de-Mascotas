package adopcion;

public class Mascota {
    private String nombre;
    private String tipo;
    private int edad;
    private boolean disponible;

    public Mascota(String nombre, String tipo, int edad, boolean disponible) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.edad = edad;
        this.disponible = disponible;
    }

    public void marcarComoAdoptada() {
        this.disponible = false;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        String estado = disponible ? "Disponible" : "Adoptada";
        return String.format("Mascota: %s, Tipo: %s, Edad: %d años, Estado: %s", nombre, tipo, edad, estado);
    }
}
