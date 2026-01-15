package minidiary.util;

import javax.swing.JOptionPane;
import java.awt.Component;

public class MessageUtil {

    // INFO
    public static void showInfo(String message) {
        JOptionPane.showMessageDialog(
            null,
            message,
            "Informasi",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    // INFO dengan parent
    public static void showInfo(Component parent, String message) {
        JOptionPane.showMessageDialog(
            parent,
            message,
            "Informasi",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    // ERROR
    public static void showError(String message) {
        JOptionPane.showMessageDialog(
            null,
            message,
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }

    // ERROR dengan parent
    public static void showError(Component parent, String message) {
        JOptionPane.showMessageDialog(
            parent,
            message,
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }

    // WARNING
    public static void showWarning(String message) {
        JOptionPane.showMessageDialog(
            null,
            message,
            "Peringatan",
            JOptionPane.WARNING_MESSAGE
        );
    }

    // WARNING dengan parent
    public static void showWarning(Component parent, String message) {
        JOptionPane.showMessageDialog(
            parent,
            message,
            "Peringatan",
            JOptionPane.WARNING_MESSAGE
        );
    }

    // KONFIRMASI YES/NO
    public static boolean confirm(String message) {
        int result = JOptionPane.showConfirmDialog(
            null,
            message,
            "Konfirmasi",
            JOptionPane.YES_NO_OPTION
        );
        return result == JOptionPane.YES_OPTION;
    }

    public static boolean confirm(Component parent, String message) {
        int result = JOptionPane.showConfirmDialog(
            parent,
            message,
            "Konfirmasi",
            JOptionPane.YES_NO_OPTION
        );
        return result == JOptionPane.YES_OPTION;
    }
}
