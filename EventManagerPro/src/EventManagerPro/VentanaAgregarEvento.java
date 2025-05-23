package EventManagerPro;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana para agregar o modificar eventos.
 * Permite crear un nuevo evento o editar uno existente mediante un formulario.
 */
public class VentanaAgregarEvento extends JFrame {
    private VentanaEventos ventanaPrincipal;

    private JTextField nombre;
    private JTextField tipo;
    private JTextField fecha;
    private JTextField lugar;
    private JTextField capacidad;
    private JTextField precio;
    private JButton guardar;

    private Evento eventoEditar; // Evento que se está editando, o null para nuevo

    /**
     * Constructor para crear una ventana para agregar un nuevo evento.
     *
     * @param ventanaPrincipal La ventana principal que lista eventos y desde la que se abre esta ventana.
     */
    public VentanaAgregarEvento(VentanaEventos ventanaPrincipal) {
        this(ventanaPrincipal, null);
    }

    /**
     * Constructor para crear una ventana para agregar o modificar un evento.
     *
     * @param ventanaPrincipal La ventana principal que lista eventos.
     * @param eventoEditar     Evento a modificar; si es null se crea uno nuevo.
     */
    public VentanaAgregarEvento(VentanaEventos ventanaPrincipal, Evento eventoEditar) {
        this.ventanaPrincipal = ventanaPrincipal;
        this.eventoEditar = eventoEditar;

        setTitle(eventoEditar == null ? "Nuevo Evento" : "Modificar Evento");
        setSize(350, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        ImageIcon iconoVentana = new ImageIcon(getClass().getResource("/EventManagerPro/icon.png"));
        setIconImage(iconoVentana.getImage());

        JPanel panel = new JPanel(new GridLayout(7, 2, 8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        panel.setBackground(new Color(245, 245, 245));

        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);

        nombre = new JTextField();
        tipo = new JTextField();
        fecha = new JTextField();
        lugar = new JTextField();
        capacidad = new JTextField();
        precio = new JTextField();
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
        panel.add(crearLabel("Precio:", labelFont));
        panel.add(campoConFuente(precio, fieldFont));
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
            precio.setText(String.valueOf(eventoEditar.getPrecio()));
        }

        guardar.addActionListener(e -> guardarEvento());
    }

    /**
     * Valida los campos del formulario y guarda un nuevo evento o modifica uno existente.
     * Muestra mensajes emergentes indicando éxito o error.
     */
    private void guardarEvento() {
        if (nombre.getText().trim().isEmpty() || tipo.getText().trim().isEmpty() ||
            fecha.getText().trim().isEmpty() || lugar.getText().trim().isEmpty() ||
            capacidad.getText().trim().isEmpty() || precio.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, rellena todos los campos.");
            return;
        }

        try {
            int cap = Integer.parseInt(capacidad.getText().trim());
            double prec = Double.parseDouble(precio.getText().trim());

            if (cap <= 0 || prec < 0) {
                JOptionPane.showMessageDialog(this, "Capacidad debe ser > 0 y precio >= 0.");
                return;
            }

            if (eventoEditar == null) {
                Evento nuevo = new Evento(nombre.getText().trim(), tipo.getText().trim(),
                        fecha.getText().trim(), lugar.getText().trim(), cap, prec, null);
                if (EventoDAO.insertarEvento(nuevo)) {
                    JOptionPane.showMessageDialog(this, "Evento agregado correctamente.");
                    ventanaPrincipal.recargarListaEventos();
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(this, "Hubo un error al guardar.");
                }
            } else {
                eventoEditar.setNombre(nombre.getText().trim());
                eventoEditar.setTipo(tipo.getText().trim());
                eventoEditar.setFecha(fecha.getText().trim());
                eventoEditar.setLugar(lugar.getText().trim());
                eventoEditar.setCapacidad(cap);
                eventoEditar.setPrecio(prec);

                if (EventoDAO.modificarEvento(eventoEditar)) {
                    JOptionPane.showMessageDialog(this, "Evento modificado correctamente.");
                    ventanaPrincipal.recargarListaEventos();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Hubo un error al modificar.");
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Capacidad y Precio deben ser números válidos.");
        }
    }

    /**
     * Limpia todos los campos del formulario para ingresar un nuevo evento.
     */
    private void limpiarCampos() {
        nombre.setText("");
        tipo.setText("");
        fecha.setText("");
        lugar.setText("");
        capacidad.setText("");
        precio.setText("");
    }

    /**
     * Crea un JLabel con texto y fuente personalizada.
     *
     * @param texto Texto a mostrar en la etiqueta.
     * @param font  Fuente a aplicar.
     * @return JLabel configurado.
     */
    private JLabel crearLabel(String texto, Font font) {
        JLabel label = new JLabel(texto);
        label.setFont(font);
        return label;
    }

    /**
     * Aplica una fuente personalizada a un JTextField.
     *
     * @param campo JTextField al que aplicar la fuente.
     * @param font  Fuente a aplicar.
     * @return JTextField configurado.
     */
    private JTextField campoConFuente(JTextField campo, Font font) {
        campo.setFont(font);
        return campo;
    }

    /**
     * Aplica un estilo personalizado a un botón.
     *
     * @param btn JButton a estilizar.
     */
    private void estiloBoton(JButton btn) {
        btn.setBackground(new Color(0, 120, 215));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
    }
}
