package minidiary.view;

import javax.swing.*;
import java.awt.*;
import minidiary.controller.LoginController;
import minidiary.model.User;
import minidiary.util.MessageUtil;
import minidiary.util.Validator;
import minidiary.util.Session;

public class LoginView extends JFrame {

    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginView() {
        setTitle("Login - Mini Diary");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblEmail = new JLabel("Email:");
        JLabel lblPassword = new JLabel("Password:");

        txtEmail = new JTextField();
        txtPassword = new JPasswordField();

        btnLogin = new JButton("Login");

        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(new JLabel());
        panel.add(btnLogin);

        add(panel);

        btnLogin.addActionListener(e -> loginAction());
    }

    private void loginAction() {
        String email = txtEmail.getText();
        String password = new String(txtPassword.getPassword());

        if (Validator.isEmpty(email) || Validator.isEmpty(password)) {
            MessageUtil.showError(this, "Email dan password wajib diisi!");
            return;
        }

        LoginController loginController = new LoginController();
        User user = loginController.login(email, password);

        if (user != null) {
            Session.setCurrentUser(user);  // FIX HERE
            MessageUtil.showInfo(this, "Login berhasil!");
            new ProfileView(); // nanti diganti DashboardView
            dispose();
        } else {
            MessageUtil.showError(this, "Email atau password salah!");
        }
    }
}