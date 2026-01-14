package minidiary.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import minidiary.controller.UserController;
import minidiary.model.User;
import minidiary.util.Validator;

public class RegisterView extends JFrame {

    private JTextField txtName;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JButton btnRegister;
    private UserController userController;

    public RegisterView() {
        setTitle("Register - Mini Diary");
        setSize(350, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        userController = new UserController();

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblName = new JLabel("Nama:");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblPassword = new JLabel("Password:");

        txtName = new JTextField();
        txtEmail = new JTextField();
        txtPassword = new JPasswordField();

        btnRegister = new JButton("Register");

        panel.add(lblName);
        panel.add(txtName);
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(new JLabel());
        panel.add(btnRegister);

        add(panel);

        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerAction();
            }
        });
    }

    private void registerAction() {
        String name = txtName.getText();
        String email = txtEmail.getText();
        String pass = new String(txtPassword.getPassword());

        if (Validator.isEmpty(name) || Validator.isEmpty(email) || Validator.isEmpty(pass)) {
            JOptionPane.showMessageDialog(this, "Semua field wajib diisi!");
            return;
        }

        if (!Validator.isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Format email tidak valid!");
            return;
        }

        if (!Validator.isStrongPassword(pass)) {
            JOptionPane.showMessageDialog(this, "Password minimal 6 karakter!");
            return;
        }

        User user = new User(0, name, email, pass);

        if (userController.register(user)) {
            JOptionPane.showMessageDialog(this, "Registrasi berhasil!");
            dispose();  
            new LoginView(); // opsional kalau kamu sudah punya login
        } else {
            JOptionPane.showMessageDialog(this, "Registrasi gagal!");
        }
    }
}
