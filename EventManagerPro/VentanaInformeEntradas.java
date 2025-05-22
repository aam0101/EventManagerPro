package EventManagerPro;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VentanaInformeEntradas extends JFrame {
    private JTextArea areaInforme;

    public VentanaInformeEntradas() {
        setTitle("Informe de Entradas Vendidas y Recaudación");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        areaInforme = new JTextArea();
        areaInforme.setFont(new Font("Consolas", Font.PLAIN, 14));
        areaInforme.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaInforme);
        add(scroll, BorderLayout.CENTER);

        cargarInforme();
    }

    private void cargarInforme() {
        final int PRECIO_ENTRADA = 10; // euros por entrada

        String sql = """
            SELECT e.nombre, e.fecha, 
                   COUNT(en.id_entrada) AS total_ventas, 
                   SUM(en.cantidad) AS total_entradas
            FROM Evento e
            LEFT JOIN Entrada en ON e.id_evento = en.id_evento
            GROUP BY e.id_evento, e.nombre, e.fecha
            ORDER BY e.fecha;
            """;

        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            StringBuilder sb = new StringBuilder();
            sb.append("Evento\t\tFecha\t\tEntradas\tVentas\tRecaudación (€)\n");
            sb.append("------------------------------------------------------------------\n");

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String fecha = rs.getString("fecha");
                int entradas = rs.getInt("total_entradas");
                int ventas = rs.getInt("total_ventas");
                int recaudacion = entradas * PRECIO_ENTRADA;

                sb.append(String.format("%-20s %-12s %-8d %-6d %d\n",
                        nombre, fecha, entradas, ventas, recaudacion));
            }

            areaInforme.setText(sb.toString());

        } catch (Exception e) {
            areaInforme.setText("Error al generar informe: " + e.getMessage());
        }
        ImageIcon iconoVentana = new ImageIcon(getClass().getResource("/EventManagerPro/icon.png"));
	    setIconImage(iconoVentana.getImage());
    }
}
