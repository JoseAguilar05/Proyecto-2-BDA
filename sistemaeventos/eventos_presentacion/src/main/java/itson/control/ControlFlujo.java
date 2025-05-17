package itson.control;

import javax.swing.JFrame;

import itson.frames.FrmMenuPrincipal;

public class ControlFlujo {

    public static void iniciarFlujo() {
        FrmMenuPrincipal menuPrincipal = new FrmMenuPrincipal();
        mostrarFrame(menuPrincipal);
        menuPrincipal.setTitle("Sistema de Eventos");
    }

    public static void mostrarFrame(JFrame frame) {
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }
}
