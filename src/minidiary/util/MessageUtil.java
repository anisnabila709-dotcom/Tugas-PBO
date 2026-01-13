package minidiary.util;

import javax.swing.JOptionPane;

public class MessageUtil {

    public static void showSuccess(String message) {
        JOptionPane.showMessageDialog(null, message, "Sukses", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
