package EventManagerPro;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VentanaEventos extends JFrame {
    private JList<Evento> listaEventos;
    private DefaultListModel<Evento> modelo;

    public VentanaEventos() {
        setTitle("Gestión de Eventos");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        ImageIcon iconoVentana = new ImageIcon(getClass().getResource("/EventManagerPro/icon.png"));
        setIconImage(iconoVentana.getImage());
        getContentPane().setBackground(new Color(245, 245, 245));

        modelo = new DefaultListModel<>();
        listaEventos = new JList<>(modelo);
        listaEventos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(listaEventos);

        JButton btnAgregar = new JButton("Agregar Evento");
        JButton btnModificar = new JButton("Modificar Evento");
        JButton btnRefrescar = new JButton("Refrescar Lista");
        JButton btnEntradas = new JButton("Gestionar Entradas");
        JButton btnInforme = new JButton("Ver Informe");
        JButton btnEliminar = new JButton("Eliminar Evento");

        JButton[] botones = {btnAgregar, btnModificar, btnRefrescar, btnEntradas, btnInforme, btnEliminar};
        for (JButton b : botones) {
            b.setBackground(new Color(0, 120, 215));
            b.setForeground(Color.WHITE);
            b.setFont(new Font("Segoe UI", Font.BOLD, 12));
            b.setFocusPainted(false);
        }

        JPanel panelBotones = new JPanel(new GridLayout(2, 3, 10, 10));
        panelBotones.setBackground(new Color(245, 245, 245));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelBotones.add(btnAgregar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnRefrescar);
        panelBotones.add(btnEntradas);
        panelBotones.add(btnInforme);
        panelBotones.add(btnEliminar);

        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        cargarEventos();

        btnAgregar.addActionListener(e -> new VentanaAgregarEvento(this).setVisible(true));
        btnModificar.addActionListener(e -> {
            Evento seleccionado = listaEventos.getSelectedValue();
            if (seleccionado == null) {
                JOptionPane.showMessageDialog(this, "Selecciona un evento para modificar.",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
            VentanaAgregarEvento ventanaEditar = new VentanaAgregarEvento(this, seleccionado);
            ventanaEditar.setVisible(true);
        });
        btnRefrescar.addActionListener(e -> cargarEventos());
        btnEntradas.addActionListener(e -> new VentanaEntradas().setVisible(true));
        btnInforme.addActionListener(e -> new VentanaInformeEntradas().setVisible(true));

        btnEliminar.addActionListener(e -> {
            Evento seleccionado = listaEventos.getSelectedValue();
            if (seleccionado == null) {
                JOptionPane.showMessageDialog(this, "Selecciona un evento para eliminar.",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Seguro que quieres eliminar el evento \"" + seleccionado.getNombre() + "\"?",
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                boolean eliminado = EventoDAO.eliminarEvento(seleccionado.getId());
                if (eliminado) {
                    JOptionPane.showMessageDialog(this, "Evento eliminado correctamente.");
                    cargarEventos();
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo eliminar el evento.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
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
