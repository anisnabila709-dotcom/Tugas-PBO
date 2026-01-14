package minidiary.main;

import minidiary.view.LoginView;
// atau jika mau langsung register:
// import minidiary.view.RegisterView;

public class MainApp {

    public static void main(String[] args) {

        // Styling opsional (biar Swing lebih modern)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}

        // Mulai dari halaman Login
        new LoginView();

        // Atau kalau kamu belum punya Login:
        // new RegisterView();
        // new ReadView();
    }
}
