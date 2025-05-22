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

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }
    public String getFecha() { return fecha; }
    public String getLugar() { return lugar; }
    public int getCapacidad() { return capacidad; }

    @Override
    public String toString() {
        return nombre + " (" + fecha + ") - " + lugar;
    }
}