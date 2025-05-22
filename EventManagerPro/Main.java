package EventManagerPro;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Aplicar Look and Feel Nimbus
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("No se pudo aplicar Nimbus Look and Feel");
        }

        // Arrancar la app con la ventana de inicio
        SwingUtilities.invokeLater(() -> {
            new VentanaInicio().setVisible(true);
        });
    }
}
