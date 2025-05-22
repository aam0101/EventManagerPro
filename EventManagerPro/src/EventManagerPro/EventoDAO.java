package EventManagerPro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EventoDAO {

    public static ArrayList<Evento> obtenerEventos() {
        ArrayList<Evento> lista = new ArrayList<>();
        String sql = "SELECT * FROM Evento";

        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Evento e = new Evento(
                        rs.getInt("id_evento"),
                        rs.getString("nombre"),
                        rs.getString("tipo"),
                        rs.getString("fecha"),
                        rs.getString("lugar"),
                        rs.getInt("capacidad_total")
                );
                lista.add(e);
            }

        } catch (Exception e) {
            System.err.println("Error al obtener eventos: " + e.getMessage());
        }

        return lista;
    }

    public static boolean insertarEvento(Evento evento) {
        String sql = "INSERT INTO Evento (nombre, tipo, fecha, lugar, capacidad_total) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, evento.getNombre());
            ps.setString(2, evento.getTipo());
            ps.setString(3, evento.getFecha());
            ps.setString(4, evento.getLugar());
            ps.setInt(5, evento.getCapacidad());

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.err.println("Error al insertar evento: " + e.getMessage());
            return false;
        }
    }
}