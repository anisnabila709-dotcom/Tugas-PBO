package minidiary.view;

import javax.swing.*;
import java.awt.*;
import minidiary.model.User;
import minidiary.util.MessageUtil;
import minidiary.util.Session;

public class ProfileView extends JFrame {

    private JLabel lblName;
    private JLabel lblEmail;
    private JButton btnLogout;

    public ProfileView() {
        setTitle("Profil User");
        setSize(350, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        User user = Session.getCurrentUser();  // FIXED

        if (user == null) {
            MessageUtil.showError(this, "Silakan login terlebih dahulu!");
            new LoginView();
            dispose();
            return;
        }

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        lblName = new JLabel("Nama   : " + user.getName());
        lblEmail = new JLabel("Email  : " + user.getEmail());

        btnLogout = new JButton("Logout");

        panel.add(lblName);
        panel.add(lblEmail);
        panel.add(new JLabel());
        panel.add(btnLogout);

        add(panel);

        btnLogout.addActionListener(e -> logoutAction());
    }

    private void logoutAction() {
        if (MessageUtil.confirm(this, "Yakin ingin logout?")) {
            Session.clear();
            new LoginView();
            dispose();
        }
    }
}