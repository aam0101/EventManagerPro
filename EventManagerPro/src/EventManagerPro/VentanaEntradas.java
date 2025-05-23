package EventManagerPro;

import javax.swing.*;
import java.awt.*;

public class VentanaEntradas extends JFrame {

    public VentanaEntradas() {
        setTitle("Registrar Entrada");
        setSize(400, 320);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        ImageIcon iconoVentana = new ImageIcon(getClass().getResource("/EventManagerPro/icon.png"));
	    setIconImage(iconoVentana.getImage());
        JPanel panel = new JPanel(new GridLayout(5, 2, 8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        panel.setBackground(new Color(245, 245, 245));

        JComboBox<Evento> comboEventos = new JComboBox<>();
        for (Evento e : EventoDAO.obtenerEventos()) {
            comboEventos.addItem(e);
        }
        comboEventos.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JTextField comprador = new JTextField();
        comprador.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JTextField email = new JTextField();
        email.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JTextField cantidad = new JTextField();
        cantidad.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JButton guardar = new JButton("Guardar Entrada");

        panel.add(new JLabel("Evento:"));
        panel.add(comboEventos);

        panel.add(new JLabel("Comprador:"));
        panel.add(comprador);

        panel.add(new JLabel("Email:"));
        panel.add(email);

        panel.add(new JLabel("Cantidad:"));
        panel.add(cantidad);

        panel.add(new JLabel());
        panel.add(guardar);

        add(panel, BorderLayout.CENTER);

        estiloBoton(guardar);

        guardar.addActionListener(e -> {
            try {
                Evento seleccionado = (Evento) comboEventos.getSelectedItem();
                int cantidadEntradas = Integer.parseInt(cantidad.getText());
                if (EntradaDAO.insertarEntrada(seleccionado.getId(), comprador.getText(), email.getText(), cantidadEntradas)) {
                    JOptionPane.showMessageDialog(this, "Entrada registrada correctamente.");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al registrar entrada.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero.");
            }
        });
    }

    private void estiloBoton(JButton btn) {
        btn.setBackground(new Color(0, 120, 215));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
    }
}
