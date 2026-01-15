package minidiary.view;

import javax.swing.*;
import java.awt.*;
import minidiary.util.MessageUtil;
import minidiary.util.Validator;

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

        // Kirim komentar
        btnSend.addActionListener(e -> {
            String comment = txtComment.getText();

            if (Validator.isEmpty(comment)) {
                MessageUtil.showWarning(this, "Komentar tidak boleh kosong!");
                return;
            }

            // ========== sementara (belum DB) ==========
            MessageUtil.showInfo(this, "Komentar berhasil dikirim! (sementara)");

            txtComment.setText("");
        });

        // Kembali ke dashboard
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