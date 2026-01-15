package minidiary.view;

import javax.swing.*;
import java.awt.*;

public class DashboardView extends JFrame {

    public DashboardView() {
        setTitle("Mini Diary - Dashboard");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton btnWrite = new JButton("Tulis Diary");
        JButton btnRead = new JButton("Lihat Diary");
        JButton btnComment = new JButton("Komentar");
        JButton btnLogout = new JButton("Logout");

        btnWrite.addActionListener(e -> {
            new WriteDiaryView();
            dispose();
        });

        btnComment.addActionListener(e -> {
            new CommentView();
            dispose();
        });

        btnRead.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Fitur baca diary belum dibuat");
        });

        btnLogout.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Logout berhasil");
            dispose();
        });

        setLayout(new GridLayout(4, 1, 10, 10));
        add(btnWrite);
        add(btnRead);
        add(btnComment);
        add(btnLogout);

        setVisible(true);
    }
}
