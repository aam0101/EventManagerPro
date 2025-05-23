package EventManagerPro;

/**
 * Representa un evento con sus detalles.
 */
public class Evento {
    private int id;
    private String nombre;
    private String tipo;
    private String fecha;
    private String lugar;
    private int capacidad;
    private double precio;

    /**
     * Constructor completo para un evento.
     *
     * @param id        Identificador único del evento.
     * @param nombre    Nombre del evento.
     * @param tipo      Tipo o categoría del evento.
     * @param fecha     Fecha del evento (formato String).
     * @param lugar     Lugar donde se realiza el evento.
     * @param capacidad Capacidad máxima del evento.
     * @param precio    Precio de la entrada para el evento.
     */
    public Evento(int id, String nombre, String tipo, String fecha, String lugar, int capacidad, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.fecha = fecha;
        this.lugar = lugar;
        this.capacidad = capacidad;
        this.precio = precio;
    }

    /**
     * Constructor para un nuevo evento sin id asignado aún.
     *
     * @param nombre    Nombre del evento.
     * @param tipo      Tipo o categoría del evento.
     * @param fecha     Fecha del evento.
     * @param lugar     Lugar donde se realiza el evento.
     * @param capacidad Capacidad máxima del evento.
     * @param precio    Precio de la entrada para el evento.
     */
    public Evento(String nombre, String tipo, String fecha, String lugar, int capacidad, double precio) {
        this(-1, nombre, tipo, fecha, lugar, capacidad, precio);
    }

    // Getters y setters

    /**
     * Obtiene el ID del evento.
     *
     * @return ID del evento.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del evento.
     *
     * @param id Nuevo ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del evento.
     *
     * @return Nombre del evento.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del evento.
     *
     * @param nombre Nuevo nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el tipo o categoría del evento.
     *
     * @return Tipo del evento.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo o categoría del evento.
     *
     * @param tipo Nuevo tipo.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene la fecha del evento.
     *
     * @return Fecha en formato String.
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha del evento.
     *
     * @param fecha Nueva fecha.
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el lugar donde se realiza el evento.
     *
     * @return Lugar del evento.
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * Establece el lugar del evento.
     *
     * @param lugar Nuevo lugar.
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    /**
     * Obtiene la capacidad máxima del evento.
     *
     * @return Capacidad máxima.
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Establece la capacidad máxima del evento.
     *
     * @param capacidad Nueva capacidad.
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * Obtiene el precio de la entrada para el evento.
     *
     * @return Precio de la entrada.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio de la entrada para el evento.
     *
     * @param precio Nuevo precio.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Representación en texto del evento, devuelve su nombre.
     *
     * @return Nombre del evento.
     */
    @Override
    public String toString() {
        return this.nombre;
    }
}
