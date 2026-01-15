package minidiary.view;

import javax.swing.*;
import java.awt.*;

/**
 * CommentView
 * Halaman untuk menulis komentar pada diary
 */
public class CommentView extends JFrame {

    private JTextArea txtComment;

    public CommentView() {
        setTitle("Komentar Diary");
        setSize(400, 300);
        setLocationRelativeTo(null);

        // JANGAN pakai EXIT_ON_CLOSE (nanti nutup seluruh app)
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponent();
        setVisible(true);
    }

    private void initComponent() {
        txtComment = new JTextArea(5, 30);
        txtComment.setLineWrap(true);
        txtComment.setWrapStyleWord(true);

        JLabel lblTitle = new JLabel("Tulis Komentar");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 14));

        JButton btnSend = new JButton("Kirim Komentar");
        JButton btnBack = new JButton("Kembali");

        btnSend.addActionListener(e -> sendComment());
        btnBack.addActionListener(e -> backToDashboard());

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(lblTitle);

        JPanel bottom = new JPanel();
        bottom.add(btnSend);
        bottom.add(btnBack);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(txtComment), BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    private void sendComment() {
        if (txtComment.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Komentar tidak boleh kosong",
                    "Validasi",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // SEMENTARA (nanti diganti Controller)
        JOptionPane.showMessageDialog(
                this,
                "Komentar berhasil dikirim",
                "Sukses",
                JOptionPane.INFORMATION_MESSAGE
        );

        txtComment.setText("");
    }

    private void backToDashboard() {
        try {
            new DashboardView(); // pastikan class ini ada
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Dashboard belum tersedia",
                    "Info",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
}
