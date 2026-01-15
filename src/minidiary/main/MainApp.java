package minidiary.main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import minidiary.view.LoginView;

public class MainApp {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException |
                 InstantiationException |
                 IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        new LoginView();
    }
}
