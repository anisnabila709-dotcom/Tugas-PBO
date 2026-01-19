package minidiary.view;

import javax.swing.*;
import java.awt.*;

public class CommentView extends JFrame {

    private int diaryId;

    public CommentView(int diaryId) {
        this.diaryId = diaryId;

        setTitle("Komentar Diary");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initUI();

        setVisible(true);
    }

    private void initUI() {
        setLayout(new BorderLayout());

        JLabel lblTitle = new JLabel("Komentar untuk Diary ID: " + diaryId);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextArea txtComment = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(txtComment);

        JButton btnSend = new JButton("Kirim Komentar");

        btnSend.addActionListener(e -> {
            if (txtComment.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Komentar tidak boleh kosong");
                return;
            }

            JOptionPane.showMessageDialog(this,
                    "Komentar dikirim (dummy)\nDiary ID: " + diaryId);
            txtComment.setText("");
        });

        add(lblTitle, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(btnSend, BorderLayout.SOUTH);
    }
}
