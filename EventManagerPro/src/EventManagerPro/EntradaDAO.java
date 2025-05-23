package EventManagerPro;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class EntradaDAO {
    public static boolean insertarEntrada(int idEvento, String comprador, String email, int cantidad) {
        String sql = "INSERT INTO Entrada (id_evento, comprador, email, cantidad, fecha_compra) VALUES (?, ?, ?, ?, CURDATE())";

        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idEvento);
            ps.setString(2, comprador);
            ps.setString(3, email);
            ps.setInt(4, cantidad);

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.err.println("Error al insertar entrada: " + e.getMessage());
            return false;
        }
    }
}
