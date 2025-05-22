package EventManagerPro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VentanaInformeEntradas extends JFrame {
    private JTable tablaInforme;
    private DefaultTableModel modeloTabla;

    public VentanaInformeEntradas() {
        setTitle("Informe de Entradas Vendidas y Recaudación");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);

        // Columnas de la tabla
        String[] columnas = {"Evento", "Fecha", "Entradas", "Ventas", "Recaudación (€)"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            // Evitar edición de celdas
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaInforme = new JTable(modeloTabla);
        tablaInforme.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tablaInforme.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tablaInforme.setRowHeight(25);

        JScrollPane scroll = new JScrollPane(tablaInforme);

        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelCentral.add(scroll, BorderLayout.CENTER);
        add(panelCentral, BorderLayout.CENTER);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnCerrar);
        add(panelBotones, BorderLayout.SOUTH);

        cargarInforme();

        ImageIcon iconoVentana = new ImageIcon(getClass().getResource("/EventManagerPro/icon.png"));
        setIconImage(iconoVentana.getImage());
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

            modeloTabla.setRowCount(0); // limpiar tabla

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String fecha = rs.getString("fecha");
                int entradas = rs.getInt("total_entradas");
                int ventas = rs.getInt("total_ventas");
                int recaudacion = entradas * PRECIO_ENTRADA;

                Object[] fila = {nombre, fecha, entradas, ventas, recaudacion};
                modeloTabla.addRow(fila);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al generar informe: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
