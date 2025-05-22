package EventManagerPro;

public class Evento {
    private int id;
    private String nombre;
    private String tipo;
    private String fecha;
    private String lugar;
    private int capacidad;

    public Evento(int id, String nombre, String tipo, String fecha, String lugar, int capacidad) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.fecha = fecha;
        this.lugar = lugar;
        this.capacidad = capacidad;
    }

    // Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }
    public String getFecha() { return fecha; }
    public String getLugar() { return lugar; }
    public int getCapacidad() { return capacidad; }

    // Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public void setLugar(String lugar) { this.lugar = lugar; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    @Override
    public String toString() {
        return nombre + " (" + fecha + ")";
    }
}
