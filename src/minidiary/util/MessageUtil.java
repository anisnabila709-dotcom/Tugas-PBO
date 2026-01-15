package minidiary.util;

import javax.swing.JOptionPane;
import java.awt.Component;

public class MessageUtil {

    // PESAN INFORMASI
    public static void showInfo(Component parent, String message) {
        JOptionPane.showMessageDialog(
            parent,
            message,
            "Informasi",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    // PESAN ERROR
    public static void showError(Component parent, String message) {
        JOptionPane.showMessageDialog(
            parent,
            message,
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }

    // PESAN WARNING
    public static void showWarning(Component parent, String message) {
        JOptionPane.showMessageDialog(
            parent,
            message,
            "Peringatan",
            JOptionPane.WARNING_MESSAGE
        );
    }

    // KONFIRMASI (YES / NO)
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
