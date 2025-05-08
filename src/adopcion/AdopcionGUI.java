package adopcion;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AdopcionGUI extends JFrame {
    private AdopcionService servicio;

    public AdopcionGUI(AdopcionService servicio) {
        this.servicio = servicio;
        setTitle("Sistema de Adopciones");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnAgregarMascota = new JButton("Agregar Mascota");
        JButton btnAgregarFamilia = new JButton("Agregar Familia");
        JButton btnListarMascotas = new JButton("Listar Mascotas");
        JButton btnListarFamilias = new JButton("Listar Familias");
        JButton btnEmparejar = new JButton("Emparejar Mascota");
        JButton btnSeguimiento = new JButton("Seguimiento");

        JPanel panel = new JPanel(new GridLayout(6, 1));
        panel.add(btnAgregarMascota);
        panel.add(btnAgregarFamilia);
        panel.add(btnListarMascotas);
        panel.add(btnListarFamilias);
        panel.add(btnEmparejar);
        panel.add(btnSeguimiento);
        add(panel);

        btnAgregarMascota.addActionListener(e -> agregarMascota());
        btnAgregarFamilia.addActionListener(e -> agregarFamilia());
        btnListarMascotas.addActionListener(e -> mostrarLista(servicio.obtenerMascotasDisponibles()));
        btnListarFamilias.addActionListener(e -> mostrarLista(servicio.obtenerFamilias()));
        btnEmparejar.addActionListener(e -> mostrarMensaje(servicio.emparejar()));
        btnSeguimiento.addActionListener(e -> mostrarLista(servicio.obtenerSeguimientoAdopciones()));
    }

    private void agregarMascota() {
        String nombre = JOptionPane.showInputDialog("Nombre:");
        String tipo = JOptionPane.showInputDialog("Tipo de mascota (perro, gato o loro):");
        String edadStr = JOptionPane.showInputDialog("Edad (años):");
        try {
            int edad = Integer.parseInt(edadStr);
            servicio.agregarMascota(new Mascota(nombre, tipo, edad, true));
            mostrarMensaje("Mascota agregada.");
        } catch (NumberFormatException e) {
            mostrarMensaje("Edad inválida.");
        }
    }

    private void agregarFamilia() {
        String nombre = JOptionPane.showInputDialog("Nombre de la familia:");
        String contacto = JOptionPane.showInputDialog("Contacto:");
        String preferenciasStr = JOptionPane.showInputDialog("Preferencias (separadas por coma):");
        List<String> preferencias = new ArrayList<>();
        for (String p : preferenciasStr.split(",")) {
            preferencias.add(p.trim());
        }
        servicio.agregarFamilia(new Familia(nombre, contacto, preferencias));
        mostrarMensaje("Familia agregada.");
    }

    private void mostrarLista(List<String> datos) {
        if (datos.isEmpty()) {
            mostrarMensaje("No hay elementos para mostrar.");
        } else {
            mostrarMensaje(String.join("\n", datos));
        }
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdopcionService servicio = new AdopcionServiceImpl();
            new AdopcionGUI(servicio).setVisible(true);
        });
    }
}
