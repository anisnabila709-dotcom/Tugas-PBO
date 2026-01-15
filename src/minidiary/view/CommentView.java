package minidiary.view;

import javax.swing.*;
import java.awt.*;

public class CommentView extends JFrame {

    private JTextArea txtComment;

    public CommentView() {
        setTitle("Komentar Diary");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        txtComment = new JTextArea();

        JButton btnSend = new JButton("Kirim Komentar");
        JButton btnBack = new JButton("Kembali");

        btnSend.addActionListener(e -> {
            if (txtComment.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Komentar tidak boleh kosong");
            } else {
                JOptionPane.showMessageDialog(this, "Komentar berhasil dikirim (sementara)");
                txtComment.setText("");
            }
        });

        btnBack.addActionListener(e -> {
            new DashboardView();
            dispose();
        });

        JPanel bottom = new JPanel();
        bottom.add(btnSend);
        bottom.add(btnBack);

        add(new JScrollPane(txtComment), BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        setVisible(true);
    }
}
