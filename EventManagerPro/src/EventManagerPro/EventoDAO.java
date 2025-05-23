package EventManagerPro;

import java.sql.*;
import java.util.ArrayList;

/**
 * Clase DAO para operaciones CRUD y consultas relacionadas con la tabla evento.
 */
public class EventoDAO {

    /**
     * Obtiene todos los eventos ordenados por fecha.
     *
     * @return Lista de objetos Evento.
     */
    public static ArrayList<Evento> obtenerEventos() {
        ArrayList<Evento> eventos = new ArrayList<>();
        String sql = "SELECT id_evento, nombre, tipo, fecha, lugar, capacidad, precio, detalles FROM evento ORDER BY fecha";

        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Evento evento = new Evento(
                        rs.getInt("id_evento"),
                        rs.getString("nombre"),
                        rs.getString("tipo"),
                        rs.getString("fecha"),
                        rs.getString("lugar"),
                        rs.getInt("capacidad"),
                        rs.getDouble("precio"),
                        rs.getString("detalles")
                );
                eventos.add(evento);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventos;
    }

    /**
     * Inserta un nuevo evento en la base de datos.
     *
     * @param evento Evento a insertar.
     * @return true si la inserción fue exitosa, false en caso contrario.
     */
    public static boolean insertarEvento(Evento evento) {
        String sql = "INSERT INTO evento (nombre, tipo, fecha, lugar, capacidad, precio, detalles) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, evento.getNombre());
            ps.setString(2, evento.getTipo());
            ps.setString(3, evento.getFecha());
            ps.setString(4, evento.getLugar());
            ps.setInt(5, evento.getCapacidad());
            ps.setDouble(6, evento.getPrecio());
            ps.setString(7, evento.getDetallesJson());

            int filas = ps.executeUpdate();
            if (filas == 0) return false;

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    evento.setId(generatedKeys.getInt(1));
                }
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Modifica un evento existente en la base de datos.
     *
     * @param evento Evento con los datos actualizados.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public static boolean modificarEvento(Evento evento) {
        String sql = "UPDATE evento SET nombre=?, tipo=?, fecha=?, lugar=?, capacidad=?, precio=?, detalles=? WHERE id_evento=?";
        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, evento.getNombre());
            ps.setString(2, evento.getTipo());
            ps.setString(3, evento.getFecha());
            ps.setString(4, evento.getLugar());
            ps.setInt(5, evento.getCapacidad());
            ps.setDouble(6, evento.getPrecio());
            ps.setString(7, evento.getDetallesJson());
            ps.setInt(8, evento.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Elimina un evento por su ID.
     *
     * @param idEvento ID del evento a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
    public static boolean eliminarEvento(int idEvento) {
        String sql = "DELETE FROM evento WHERE id_evento=?";
        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idEvento);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Invoca el procedimiento almacenado para obtener la capacidad restante de un evento.
     *
     * @param idEvento ID del evento.
     * @return Capacidad restante, o -1 en caso de error.
     */
    public static int obtenerCapacidadRestante(int idEvento) {
        String sql = "{CALL obtener_capacidad_restante(?)}";
        try (Connection conn = Conexion.conectar();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, idEvento);

            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("capacidad_restante");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Llama a la función almacenada para obtener el total de ventas de un evento.
     *
     * @param idEvento ID del evento.
     * @return Total de ventas o 0.0 si no se encuentra.
     */
    public static double obtenerTotalVentas(int idEvento) {
        String sql = "SELECT total_ventas(?) AS total";
        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idEvento);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("total");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}
