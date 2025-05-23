package EventManagerPro;

import org.json.JSONObject;

/**
 * Representa un evento con sus detalles, incluyendo datos básicos
 * y un campo JSON para detalles complejos.
 */
public class Evento {
    private int id;
    private String nombre;
    private String tipo;
    private String fecha;
    private String lugar;
    private int capacidad;
    private double precio;
    private JSONObject detalles;

    /**
     * Constructor completo para un evento, incluyendo detalles en JSON.
     *
     * @param id          Identificador único del evento.
     * @param nombre      Nombre del evento.
     * @param tipo        Tipo o categoría del evento.
     * @param fecha       Fecha del evento (formato String).
     * @param lugar       Lugar donde se realiza el evento.
     * @param capacidad   Capacidad máxima del evento.
     * @param precio      Precio de la entrada para el evento.
     * @param detallesJson Cadena JSON con detalles complejos del evento (puede ser null).
     */
    public Evento(int id, String nombre, String tipo, String fecha, String lugar,
                  int capacidad, double precio, String detallesJson) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.fecha = fecha;
        this.lugar = lugar;
        this.capacidad = capacidad;
        this.precio = precio;
        if (detallesJson != null && !detallesJson.isEmpty()) {
            this.detalles = new JSONObject(detallesJson);
        } else {
            this.detalles = null;
        }
    }

    /**
     * Constructor para un nuevo evento sin id asignado aún.
     *
     * @param nombre      Nombre del evento.
     * @param tipo        Tipo o categoría del evento.
     * @param fecha       Fecha del evento.
     * @param lugar       Lugar donde se realiza el evento.
     * @param capacidad   Capacidad máxima del evento.
     * @param precio      Precio de la entrada para el evento.
     * @param detallesJson Cadena JSON con detalles complejos del evento (puede ser null).
     */
    public Evento(String nombre, String tipo, String fecha, String lugar,
                  int capacidad, double precio, String detallesJson) {
        this(-1, nombre, tipo, fecha, lugar, capacidad, precio, detallesJson);
    }

    /** @return El ID del evento. */
    public int getId() {
        return id;
    }

    /** @param id Nuevo ID del evento. */
    public void setId(int id) {
        this.id = id;
    }

    /** @return Nombre del evento. */
    public String getNombre() {
        return nombre;
    }

    /** @param nombre Nuevo nombre del evento. */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** @return Tipo o categoría del evento. */
    public String getTipo() {
        return tipo;
    }

    /** @param tipo Nuevo tipo o categoría del evento. */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /** @return Fecha del evento en formato String. */
    public String getFecha() {
        return fecha;
    }

    /** @param fecha Nueva fecha del evento. */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /** @return Lugar donde se realiza el evento. */
    public String getLugar() {
        return lugar;
    }

    /** @param lugar Nuevo lugar del evento. */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    /** @return Capacidad máxima del evento. */
    public int getCapacidad() {
        return capacidad;
    }

    /** @param capacidad Nueva capacidad máxima del evento. */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    /** @return Precio de la entrada para el evento. */
    public double getPrecio() {
        return precio;
    }

    /** @param precio Nuevo precio de la entrada para el evento. */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene detalles complejos en formato JSONObject.
     *
     * @return JSONObject con detalles complejos o null si no hay.
     */
    public JSONObject getDetalles() {
        return detalles;
    }

    /**
     * Establece los detalles complejos del evento.
     *
     * @param detalles JSONObject con detalles complejos.
     */
    public void setDetalles(JSONObject detalles) {
        this.detalles = detalles;
    }

    /**
     * Obtiene los detalles complejos en formato JSON String.
     *
     * @return String JSON con los detalles o null si no hay detalles.
     */
    public String getDetallesJson() {
        return detalles != null ? detalles.toString() : null;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
