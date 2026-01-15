package minidiary.view;

import javax.swing.*;
import java.awt.*;
import minidiary.controller.RegisterController;

public class RegisterView extends JFrame {

    private JTextField txtUsername;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirm;
    private JButton btnRegister;

    private RegisterController registerController;

    public RegisterView() {
        setTitle("Register - Mini Diary");
        setSize(350, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        registerController = new RegisterController();

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Username:"));
        txtUsername = new JTextField();
        panel.add(txtUsername);

        panel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        panel.add(txtEmail);

        panel.add(new JLabel("Password:"));
        txtPassword = new JPasswordField();
        panel.add(txtPassword);

        panel.add(new JLabel("Konfirmasi Password:"));
        txtConfirm = new JPasswordField();
        panel.add(txtConfirm);

        btnRegister = new JButton("Register");
        panel.add(new JLabel());
        panel.add(btnRegister);

        add(panel);

        btnRegister.addActionListener(e -> registerAction());
    }

    private void registerAction() {
        registerController.register(
            txtUsername.getText(),
            txtEmail.getText(),
            new String(txtPassword.getPassword()),
            new String(txtConfirm.getPassword())
        );
    }
}