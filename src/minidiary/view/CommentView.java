package minidiary.view;

import minidiary.controller.CommentController;

import javax.swing.*;
import java.awt.*;

public class CommentView extends JFrame {

    private JTextArea commentArea;
    private int diaryId;

    public CommentView(int diaryId) {
        this.diaryId = diaryId;

        setTitle("Comment");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        commentArea = new JTextArea();
        JButton sendButton = new JButton("Send");

        sendButton.addActionListener(e -> {
            CommentController controller = new CommentController();
            boolean success = controller.addComment(diaryId, commentArea.getText());

            if (success) {
                JOptionPane.showMessageDialog(this, "Komentar terkirim");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Gagal mengirim komentar");
            }
        });

        add(new JScrollPane(commentArea), BorderLayout.CENTER);
        add(sendButton, BorderLayout.SOUTH);
    }
}
