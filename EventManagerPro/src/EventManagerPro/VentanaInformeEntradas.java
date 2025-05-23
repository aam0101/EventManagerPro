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
        setTitle("Informe Detallado de Entradas");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);

        String[] columnas = {"ID Entrada", "ID Evento", "Comprador", "Email", "Cantidad", "Fecha Compra"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // evitar edición
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
        String sql = """
            SELECT id_entrada, id_evento, comprador, email, cantidad, fecha_compra
            FROM Entrada
            ORDER BY fecha_compra DESC;
            """;

        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            modeloTabla.setRowCount(0); // limpiar tabla

            while (rs.next()) {
                int idEntrada = rs.getInt("id_entrada");
                int idEvento = rs.getInt("id_evento");
                String comprador = rs.getString("comprador");
                String email = rs.getString("email");
                int cantidad = rs.getInt("cantidad");
                String fechaCompra = rs.getString("fecha_compra");

                Object[] fila = {idEntrada, idEvento, comprador, email, cantidad, fechaCompra};
                modeloTabla.addRow(fila);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar informe: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
