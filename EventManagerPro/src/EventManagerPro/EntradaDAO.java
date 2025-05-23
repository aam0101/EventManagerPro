package EventManagerPro;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Clase DAO para operaciones relacionadas con la tabla entrada.
 */
public class EntradaDAO {

    /**
     * Inserta una nueva entrada en la base de datos.
     *
     * @param idEvento  ID del evento al que pertenece la entrada.
     * @param comprador Nombre del comprador.
     * @param email     Email del comprador.
     * @param cantidad  Cantidad de entradas.
     * @return true si la inserción fue exitosa, false en caso contrario.
     */
    public static boolean insertarEntrada(int idEvento, String comprador, String email, int cantidad) {
        String sql = "INSERT INTO entrada (id_evento, comprador, email, cantidad, fecha_compra) VALUES (?, ?, ?, ?, CURDATE())";

        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idEvento);
            ps.setString(2, comprador);
            ps.setString(3, email);
            ps.setInt(4, cantidad);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
