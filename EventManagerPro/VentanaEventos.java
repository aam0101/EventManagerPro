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
        
        ImageIcon iconoVentana = new ImageIcon(getClass().getResource("/EventManagerPro/icon.png"));
	    setIconImage(iconoVentana.getImage());
        // Fondo gris claro
        getContentPane().setBackground(new Color(245, 245, 245));
        
        modelo = new DefaultListModel<>();
        listaEventos = new JList<>(modelo);
        listaEventos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(listaEventos);

        JButton btnAgregar = new JButton("Agregar Evento");
        JButton btnRefrescar = new JButton("Refrescar Lista");
        JButton btnEntradas = new JButton("Gestionar Entradas");
        JButton btnInforme = new JButton("Ver Informe");

        // Estilo botones
        JButton[] botones = {btnAgregar, btnRefrescar, btnEntradas, btnInforme};
        for (JButton b : botones) {
            b.setBackground(new Color(0, 120, 215));
            b.setForeground(Color.WHITE);
            b.setFont(new Font("Segoe UI", Font.BOLD, 12));
            b.setFocusPainted(false);
        }

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(245, 245, 245));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelBotones.add(btnAgregar);
        panelBotones.add(btnRefrescar);
        panelBotones.add(btnEntradas);
        panelBotones.add(btnInforme);

        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        cargarEventos();

        btnAgregar.addActionListener(e -> new VentanaAgregarEvento(this).setVisible(true));
        btnRefrescar.addActionListener(e -> cargarEventos());
        btnEntradas.addActionListener(e -> new VentanaEntradas().setVisible(true));
        btnInforme.addActionListener(e -> new VentanaInformeEntradas().setVisible(true));
    }

    private void cargarEventos() {
        modelo.clear();
        ArrayList<Evento> eventos = EventoDAO.obtenerEventos();
        for (Evento e : eventos) {
            modelo.addElement(e);
        }
    }

    public void recargarListaEventos() {
        cargarEventos();
    }

    public static void main(String[] args) {
        // Aplicar Look and Feel Nimbus
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("No se pudo aplicar Nimbus Look and Feel");
        }

        SwingUtilities.invokeLater(() -> new VentanaEventos().setVisible(true));
    }
}
