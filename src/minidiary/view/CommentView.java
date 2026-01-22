package minidiary.view;

import minidiary.controller.CommentController;
import minidiary.model.Comment;
import minidiary.model.Diary;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CommentView extends JFrame {

    private DashboardFeedView dashboard;
    private Diary diary;
    private CommentController commentController;

    private JPanel commentPanel;
    private JTextArea txtComment;

    // 🔥 FLAG PENANDA ADA PERUBAHAN KOMENTAR
    private boolean commentChanged = false;

    public CommentView(DashboardFeedView dashboard, Diary diary) {
        this.dashboard = dashboard;
        this.diary = diary;
        this.commentController = new CommentController();

        setTitle("Komentar Diary");
        setSize(600, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initUI();
        loadComments();

        setVisible(true);
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // ===== HEADER =====
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnBack = new JButton("← Kembali");
        btnBack.addActionListener(e -> {
            dispose();

            // 🔥 PAKSA DASHBOARD RELOAD JIKA ADA KOMENTAR BARU
            if (commentChanged) {
                dashboard.loadDiary();
            }

            dashboard.setVisible(true);
        });
        header.add(btnBack);
        add(header, BorderLayout.NORTH);

        // ===== CENTER PANEL =====
        JPanel centerPanel = new JPanel(new BorderLayout());

        // --- POSTINGAN ---
        JPanel diaryPanel = new JPanel(new BorderLayout());
        diaryPanel.setBorder(BorderFactory.createTitledBorder("Postingan"));

        JLabel lblTitle = new JLabel(diary.getTitle());
        lblTitle.setFont(new Font("Arial", Font.BOLD, 14));

        JTextArea txtContent = new JTextArea(diary.getContent());
        txtContent.setEditable(false);
        txtContent.setLineWrap(true);
        txtContent.setWrapStyleWord(true);

        diaryPanel.add(lblTitle, BorderLayout.NORTH);
        diaryPanel.add(new JScrollPane(txtContent), BorderLayout.CENTER);

        // --- KOMENTAR ---
        commentPanel = new JPanel();
        commentPanel.setLayout(new BoxLayout(commentPanel, BoxLayout.Y_AXIS));

        JScrollPane commentScroll = new JScrollPane(commentPanel);
        commentScroll.setBorder(BorderFactory.createTitledBorder("Komentar"));

        centerPanel.add(diaryPanel, BorderLayout.NORTH);
        centerPanel.add(commentScroll, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        // ===== INPUT =====
        JPanel inputPanel = new JPanel(new BorderLayout());
        txtComment = new JTextArea(3, 30);
        txtComment.setLineWrap(true);
        txtComment.setWrapStyleWord(true);

        JButton btnSend = new JButton("Kirim");
        btnSend.addActionListener(e -> sendComment());

        inputPanel.add(new JScrollPane(txtComment), BorderLayout.CENTER);
        inputPanel.add(btnSend, BorderLayout.EAST);

        add(inputPanel, BorderLayout.SOUTH);
    }

    private void loadComments() {
        commentPanel.removeAll();

        List<Comment> comments =
                commentController.getCommentsByDiary(diary.getId());

        if (comments.isEmpty()) {
            JLabel lblEmpty = new JLabel("Belum ada komentar");
            lblEmpty.setAlignmentX(Component.CENTER_ALIGNMENT);
            commentPanel.add(lblEmpty);
        } else {
            for (Comment c : comments) {
                JPanel card = new JPanel(new BorderLayout());
                card.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                        BorderFactory.createEmptyBorder(5,5,5,5)
                ));

                JLabel lblUser = new JLabel("User ID: " + c.getUserId());
                lblUser.setFont(new Font("Arial", Font.BOLD, 12));

                JTextArea txt = new JTextArea(c.getContent());
                txt.setEditable(false);
                txt.setLineWrap(true);
                txt.setWrapStyleWord(true);
                txt.setBackground(null);

                card.add(lblUser, BorderLayout.NORTH);
                card.add(txt, BorderLayout.CENTER);

                commentPanel.add(card);
            }
        }

        commentPanel.revalidate();
        commentPanel.repaint();
    }

    private void sendComment() {
        String content = txtComment.getText().trim();
        if (content.isEmpty()) return;

        if (commentController.addComment(diary.getId(), content)) {
            txtComment.setText("");
            loadComments();

            // 🔥 TANDAI ADA PERUBAHAN
            commentChanged = true;
        }
    }
}
