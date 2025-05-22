package EventManagerPro;

import javax.swing.*;
import java.awt.*;

public class VentanaAgregarEvento extends JFrame {
    private VentanaEventos ventanaPrincipal;

    private JTextField nombre;
    private JTextField tipo;
    private JTextField fecha;
    private JTextField lugar;
    private JTextField capacidad;
    private JButton guardar;

    private Evento eventoEditar;  // Si no es null, estamos editando

    public VentanaAgregarEvento(VentanaEventos ventanaPrincipal) {
        this(ventanaPrincipal, null);
    }

    public VentanaAgregarEvento(VentanaEventos ventanaPrincipal, Evento eventoEditar) {
        this.ventanaPrincipal = ventanaPrincipal;
        this.eventoEditar = eventoEditar;

        setTitle(eventoEditar == null ? "Nuevo Evento" : "Modificar Evento");
        setSize(350, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        ImageIcon iconoVentana = new ImageIcon(getClass().getResource("/EventManagerPro/icon.png"));
        setIconImage(iconoVentana.getImage());

        JPanel panel = new JPanel(new GridLayout(6, 2, 8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        panel.setBackground(new Color(245, 245, 245));

        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);

        nombre = new JTextField();
        tipo = new JTextField();
        fecha = new JTextField();
        lugar = new JTextField();
        capacidad = new JTextField();
        guardar = new JButton(eventoEditar == null ? "Guardar" : "Guardar Cambios");

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

        panel.add(new JLabel()); 
        panel.add(guardar);

        add(panel, BorderLayout.CENTER);

        estiloBoton(guardar);

        if (eventoEditar != null) {
            nombre.setText(eventoEditar.getNombre());
            tipo.setText(eventoEditar.getTipo());
            fecha.setText(eventoEditar.getFecha());
            lugar.setText(eventoEditar.getLugar());
            capacidad.setText(String.valueOf(eventoEditar.getCapacidad()));
        }

        guardar.addActionListener(e -> guardarEvento());
    }

    private void guardarEvento() {
        if (nombre.getText().isEmpty() || tipo.getText().isEmpty() || fecha.getText().isEmpty() ||
            lugar.getText().isEmpty() || capacidad.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, rellena todos los campos.");
            return;
        }

        try {
            int cap = Integer.parseInt(capacidad.getText());

            if (eventoEditar == null) {
                Evento nuevo = new Evento(0, nombre.getText(), tipo.getText(), fecha.getText(), lugar.getText(), cap);
                if (EventoDAO.insertarEvento(nuevo)) {
                    JOptionPane.showMessageDialog(this, "Evento agregado correctamente.");
                    ventanaPrincipal.recargarListaEventos();
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(this, "Hubo un error al guardar.");
                }
            } else {
                eventoEditar.setNombre(nombre.getText());
                eventoEditar.setTipo(tipo.getText());
                eventoEditar.setFecha(fecha.getText());
                eventoEditar.setLugar(lugar.getText());
                eventoEditar.setCapacidad(cap);

                if (EventoDAO.modificarEvento(eventoEditar)) {
                    JOptionPane.showMessageDialog(this, "Evento modificado correctamente.");
                    ventanaPrincipal.recargarListaEventos();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Hubo un error al modificar.");
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Capacidad debe ser un número.");
        }
    }

    private void limpiarCampos() {
        nombre.setText("");
        tipo.setText("");
        fecha.setText("");
        lugar.setText("");
        capacidad.setText("");
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
