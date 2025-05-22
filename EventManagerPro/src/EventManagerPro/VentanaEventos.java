package EventManagerPro;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VentanaEventos extends JFrame {
    private JList<Evento> listaEventos;
    private DefaultListModel<Evento> modelo;

    public VentanaEventos() {
        setTitle("Gestión de Eventos");
        setSize(550, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        modelo = new DefaultListModel<>();
        listaEventos = new JList<>(modelo);
        JScrollPane scroll = new JScrollPane(listaEventos);

        // Botones
        JButton btnAgregar = new JButton("Agregar Evento");
        JButton btnRefrescar = new JButton("Refrescar Lista");
        JButton btnEntradas = new JButton("Gestionar Entradas");
        JButton btnInforme = new JButton("Ver Informe"); // Nuevo botón

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnRefrescar);
        panelBotones.add(btnEntradas);
        panelBotones.add(btnInforme); // Añadido al panel

        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Cargar eventos al iniciar
        cargarEventos();

        // Acciones
        btnAgregar.addActionListener(e -> new VentanaAgregarEvento(this).setVisible(true));
        btnRefrescar.addActionListener(e -> cargarEventos());
        btnEntradas.addActionListener(e -> new VentanaEntradas().setVisible(true));
        btnInforme.addActionListener(e -> new VentanaInformeEntradas().setVisible(true)); // Acción nueva
    }

    // Cargar eventos desde la BD
    private void cargarEventos() {
        modelo.clear();
        ArrayList<Evento> eventos = EventoDAO.obtenerEventos();
        for (Evento e : eventos) {
            modelo.addElement(e);
        }
    }

    // Llamado desde otras ventanas para actualizar la lista
    public void recargarListaEventos() {
        cargarEventos();
    }

    // Método main para probar
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaEventos().setVisible(true));
    }
}
