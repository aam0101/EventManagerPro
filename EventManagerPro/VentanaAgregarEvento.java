package EventManagerPro;

import javax.swing.*;
import java.awt.*;

public class VentanaAgregarEvento extends JFrame {
    private VentanaEventos ventanaPrincipal;

    public VentanaAgregarEvento(VentanaEventos ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        setTitle("Nuevo Evento");
        setSize(350, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        ImageIcon iconoVentana = new ImageIcon(getClass().getResource("/EventManagerPro/icon.png"));
	    setIconImage(iconoVentana.getImage());
        // Panel principal con padding
        JPanel panel = new JPanel(new GridLayout(6, 2, 8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        panel.setBackground(new Color(245, 245, 245));

        JTextField nombre = new JTextField();
        JTextField tipo = new JTextField();
        JTextField fecha = new JTextField();
        JTextField lugar = new JTextField();
        JTextField capacidad = new JTextField();
        JButton guardar = new JButton("Guardar");

        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);

        // Etiquetas
        panel.add(crearLabel("Nombre:", labelFont));
        panel.add(campoConFuente(nombre, fieldFont));

        panel.add(crearLabel("Tipo:", labelFont));
        panel.add(campoConFuente(tipo, fieldFont));

        panel.add(crearLabel("Fecha (YYYY-MM-DD):", labelFont));
        panel.add(campoConFuente(fecha, fieldFont));

        panel.add(crearLabel("Lugar:", labelFont));
        panel.add(campoConFuente(lugar, fieldFont));

        panel.add(crearLabel("Capacidad:", labelFont));
        panel.add(campoConFuente(capacidad, fieldFont));

        panel.add(new JLabel()); // espacio vacío
        panel.add(guardar);

        add(panel, BorderLayout.CENTER);

        // Estilo botón
        estiloBoton(guardar);

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

    private JLabel crearLabel(String texto, Font font) {
        JLabel label = new JLabel(texto);
        label.setFont(font);
        return label;
    }

    private JTextField campoConFuente(JTextField campo, Font font) {
        campo.setFont(font);
        return campo;
    }

    private void estiloBoton(JButton btn) {
        btn.setBackground(new Color(0, 120, 215));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
    }
}
