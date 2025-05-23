package EventManagerPro;

import java.sql.*;
import java.util.ArrayList;

/**
 * Clase para manejar operaciones CRUD sobre la tabla Evento.
 */
public class EventoDAO {

    /**
     * Obtiene todos los eventos ordenados por fecha.
     *
     * @return Lista de eventos.
     */
    public static ArrayList<Evento> obtenerEventos() {
        ArrayList<Evento> eventos = new ArrayList<>();
        String sql = "SELECT id_evento, nombre, tipo, fecha, lugar, capacidad, precio FROM Evento ORDER BY fecha";

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
                    rs.getDouble("precio")
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
        String sql = "INSERT INTO Evento (nombre, tipo, fecha, lugar, capacidad, precio) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, evento.getNombre());
            ps.setString(2, evento.getTipo());
            ps.setString(3, evento.getFecha());
            ps.setString(4, evento.getLugar());
            ps.setInt(5, evento.getCapacidad());
            ps.setDouble(6, evento.getPrecio());

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Modifica un evento existente.
     *
     * @param evento Evento con los datos actualizados.
     * @return true si la modificación fue exitosa, false en caso contrario.
     */
    public static boolean modificarEvento(Evento evento) {
        String sql = "UPDATE Evento SET nombre = ?, tipo = ?, fecha = ?, lugar = ?, capacidad = ?, precio = ? WHERE id_evento = ?";
        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, evento.getNombre());
            ps.setString(2, evento.getTipo());
            ps.setString(3, evento.getFecha());
            ps.setString(4, evento.getLugar());
            ps.setInt(5, evento.getCapacidad());
            ps.setDouble(6, evento.getPrecio());
            ps.setInt(7, evento.getId());

            int filas = ps.executeUpdate();
            return filas > 0;

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
        String sql = "DELETE FROM Evento WHERE id_evento = ?";
        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idEvento);
            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
