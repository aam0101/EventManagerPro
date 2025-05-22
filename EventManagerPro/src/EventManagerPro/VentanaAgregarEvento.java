package EventManagerPro;

import javax.swing.*;
import java.awt.*;

public class VentanaAgregarEvento extends JFrame {
    private VentanaEventos ventanaPrincipal;

    public VentanaAgregarEvento(VentanaEventos ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        setTitle("Nuevo Evento");
        setSize(300, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 2));
        JTextField nombre = new JTextField();
        JTextField tipo = new JTextField();
        JTextField fecha = new JTextField();
        JTextField lugar = new JTextField();
        JTextField capacidad = new JTextField();
        JButton guardar = new JButton("Guardar");

        panel.add(new JLabel("Nombre:")); panel.add(nombre);
        panel.add(new JLabel("Tipo:")); panel.add(tipo);
        panel.add(new JLabel("Fecha (YYYY-MM-DD):")); panel.add(fecha);
        panel.add(new JLabel("Lugar:")); panel.add(lugar);
        panel.add(new JLabel("Capacidad:")); panel.add(capacidad);
        panel.add(new JLabel()); panel.add(guardar);

        add(panel);

        guardar.addActionListener(e -> {
            if (nombre.getText().isEmpty() || tipo.getText().isEmpty() ||
                fecha.getText().isEmpty() || lugar.getText().isEmpty() || capacidad.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, rellena todos los campos.");
                return;
            }

            try {
                int cap = Integer.parseInt(capacidad.getText());
                Evento nuevo = new Evento(0, nombre.getText(), tipo.getText(), fecha.getText(), lugar.getText(), cap);
                if (EventoDAO.insertarEvento(nuevo)) {
                    JOptionPane.showMessageDialog(this, "Evento agregado correctamente.");
                    ventanaPrincipal.recargarListaEventos();
                    nombre.setText(""); tipo.setText(""); fecha.setText(""); lugar.setText(""); capacidad.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Hubo un error al guardar.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Capacidad debe ser un número.");
            }
        });
    }
}