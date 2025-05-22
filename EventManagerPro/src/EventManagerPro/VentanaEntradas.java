package EventManagerPro;

import javax.swing.*;
import java.awt.*;

public class VentanaEntradas extends JFrame {
    public VentanaEntradas() {
        setTitle("Registrar Entrada");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JComboBox<Evento> comboEventos = new JComboBox<>();
        for (Evento e : EventoDAO.obtenerEventos()) {
            comboEventos.addItem(e);
        }

        JTextField comprador = new JTextField();
        JTextField email = new JTextField();
        JTextField cantidad = new JTextField();
        JButton guardar = new JButton("Guardar Entrada");

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Evento:")); panel.add(comboEventos);
        panel.add(new JLabel("Comprador:")); panel.add(comprador);
        panel.add(new JLabel("Email:")); panel.add(email);
        panel.add(new JLabel("Cantidad:")); panel.add(cantidad);
        panel.add(new JLabel()); panel.add(guardar);

        add(panel);

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
}
