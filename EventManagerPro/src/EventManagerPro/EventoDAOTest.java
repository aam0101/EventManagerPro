package EventManagerPro;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

public class EventoDAOTest {

    @Test
    public void testInsertarYObtenerEvento() {
        // Crear un evento de prueba con capacidadTotal y capacidadDisponible iguales
        Evento evento = new Evento(0, "Evento Test JUnit", "Concierto", "2025-06-15", "Auditorio", 1000, 1000);

        // Insertar el evento
        boolean insertado = EventoDAO.insertarEvento(evento);
        assertTrue("El evento debe insertarse correctamente", insertado);

        // Obtener la lista de eventos y buscar el insertado
        ArrayList<Evento> eventos = EventoDAO.obtenerEventos();
        boolean encontrado = false;
        for (Evento e : eventos) {
            if (e.getNombre().equals("Evento Test JUnit") && e.getFecha().equals("2025-06-15")) {
                encontrado = true;
                break;
            }
        }
        assertTrue("El evento insertado debe estar en la lista de eventos", encontrado);
    }

    @Test
    public void testModificarYEliminarEvento() {
        // Insertar un evento para modificar y eliminar
        Evento evento = new Evento(0, "Evento Modificar", "Deporte", "2025-07-10", "Estadio", 5000, 5000);
        boolean insertado = EventoDAO.insertarEvento(evento);
        assertTrue("Evento para modificar debe insertarse", insertado);

        // Obtener el evento insertado para obtener su id (asumiendo que id es autoincremental)
        ArrayList<Evento> eventos = EventoDAO.obtenerEventos();
        Evento eventoInsertado = null;
        for (Evento e : eventos) {
            if (e.getNombre().equals("Evento Modificar")) {
                eventoInsertado = e;
                break;
            }
        }
        assertNotNull("Evento insertado debe encontrarse", eventoInsertado);

        // Modificar el evento
        eventoInsertado.setLugar("Nuevo Lugar");
        boolean modificado = EventoDAO.modificarEvento(eventoInsertado);
        assertTrue("Evento debe modificarse correctamente", modificado);

        // Verificar la modificación
        eventos = EventoDAO.obtenerEventos();
        Evento modificadoEvento = null;
        for (Evento e : eventos) {
            if (e.getId() == eventoInsertado.getId()) {
                modificadoEvento = e;
                break;
            }
        }
        assertNotNull(modificadoEvento);
        assertEquals("Nuevo Lugar", modificadoEvento.getLugar());

        // Eliminar el evento
        boolean eliminado = EventoDAO.eliminarEvento(eventoInsertado.getId());
        assertTrue("Evento debe eliminarse correctamente", eliminado);

        // Verificar que ya no está
        eventos = EventoDAO.obtenerEventos();
        boolean encontrado = false;
        for (Evento e : eventos) {
            if (e.getId() == eventoInsertado.getId()) {
                encontrado = true;
                break;
            }
        }
        assertFalse("Evento eliminado no debe encontrarse", encontrado);
    }
}
