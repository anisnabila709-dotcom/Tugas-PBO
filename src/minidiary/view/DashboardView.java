package minidiary.view;

import minidiary.util.Session;

import javax.swing.*;
import java.awt.*;

public class DashboardView extends JFrame {

    public DashboardView() {
        setTitle("Dashboard");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("Mini Diary Dashboard", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));

        JButton writeButton = new JButton("Write Diary");
        JButton logoutButton = new JButton("Logout");

        writeButton.addActionListener(e -> {
            new WriteDiaryView().setVisible(true);
        });

        logoutButton.addActionListener(e -> {
            Session.clear();
            dispose();
            JOptionPane.showMessageDialog(this, "Logout berhasil");
        });

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(title);
        panel.add(writeButton);
        panel.add(logoutButton);

        add(panel);
    }
}
