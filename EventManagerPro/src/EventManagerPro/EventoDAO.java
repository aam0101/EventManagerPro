package EventManagerPro;

import java.sql.*;
import java.util.ArrayList;

public class EventoDAO {

    public static ArrayList<Evento> obtenerEventos() {
        ArrayList<Evento> eventos = new ArrayList<>();
        String sql = "SELECT id_evento, nombre, tipo, fecha, lugar, capacidad FROM Evento ORDER BY fecha";

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
                    rs.getInt("capacidad")
                );
                eventos.add(evento);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Aquí podrías mostrar un mensaje o loguear error más elaborado
        }

        return eventos;
    }

    public static boolean insertarEvento(Evento evento) {
        String sql = "INSERT INTO Evento (nombre, tipo, fecha, lugar, capacidad) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, evento.getNombre());
            ps.setString(2, evento.getTipo());
            ps.setString(3, evento.getFecha());
            ps.setString(4, evento.getLugar());
            ps.setInt(5, evento.getCapacidad());

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean modificarEvento(Evento evento) {
        String sql = "UPDATE Evento SET nombre = ?, tipo = ?, fecha = ?, lugar = ?, capacidad = ? WHERE id_evento = ?";
        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, evento.getNombre());
            ps.setString(2, evento.getTipo());
            ps.setString(3, evento.getFecha());
            ps.setString(4, evento.getLugar());
            ps.setInt(5, evento.getCapacidad());
            ps.setInt(6, evento.getId());

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

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
