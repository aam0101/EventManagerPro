package EventManagerPro;

import javax.swing.*;
import java.awt.*;

public class VentanaInicio extends JFrame {

	public VentanaInicio() {
	    setTitle("Bienvenido");
	    setSize(400, 300);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);

	    // Cargar y establecer icono de la ventana
	    ImageIcon iconoVentana = new ImageIcon(getClass().getResource("/EventManagerPro/icon.png"));
	    setIconImage(iconoVentana.getImage());

	    ImagenFondoPanel fondo = new ImagenFondoPanel("/EventManagerPro/imagen.png");
	    fondo.setLayout(new BorderLayout());
	    setContentPane(fondo);

       
        JPanel panelTitulo = new JPanel();
        panelTitulo.setOpaque(false); 
        panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(60, 0, 0, 0)); 

        // Panel para el botón centrado abajo
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.setOpaque(false);
        JButton btnAcceder = new JButton("Acceder");
        panelBoton.add(btnAcceder);
        fondo.add(panelBoton, BorderLayout.SOUTH);

        btnAcceder.addActionListener(e -> {
            new VentanaEventos().setVisible(true);
            this.dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaInicio().setVisible(true);
        });
    }
}


class ImagenFondoPanel extends JPanel {
    private Image imagen;

    public ImagenFondoPanel(String path) {
        // Carga la imagen usando getResource para que funcione en el JAR
        ImageIcon icono = new ImageIcon(getClass().getResource(path));
        imagen = icono.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibuja la imagen escalada al tamaño del panel
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
    }
}
