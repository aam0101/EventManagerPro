package EventManagerPro;

public class Main {
    public static void main(String[] args) {
        // Asegurarse de que la GUI se cargue en el hilo de eventos de Swing
        javax.swing.SwingUtilities.invokeLater(() -> {
            new VentanaEventos().setVisible(true);
        });
    }
}
