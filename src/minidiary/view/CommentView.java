package minidiary.view;

import minidiary.controller.CommentController;
import minidiary.model.Comment;
import minidiary.model.Diary;
import minidiary.dao.UserDAO;
import minidiary.model.User;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CommentView extends JFrame {

    private DashboardFeedView dashboard;
    private Diary diary;
    private CommentController commentController;

    private JPanel commentPanel;
    private JTextArea txtComment;

    private boolean commentChanged = false;

    public CommentView(DashboardFeedView dashboard, Diary diary) {
        this.dashboard = dashboard;
        this.diary = diary;
        this.commentController = new CommentController();

        setTitle("Komentar Diary");
        setSize(600, 560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initUI();
        loadComments();

        setVisible(true);
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // ================= HEADER =================
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnBack = new JButton("← Kembali");
        btnBack.addActionListener(e -> {
            dispose();
            if (commentChanged) {
                dashboard.loadDiary();
            }
            dashboard.setVisible(true);
        });
        header.add(btnBack);
        add(header, BorderLayout.NORTH);

        // ================= CENTER =================
        JPanel centerPanel = new JPanel(new BorderLayout());

        // ===== POSTINGAN DIARY =====
        JPanel diaryPanel = new JPanel(new BorderLayout());
        diaryPanel.setBorder(BorderFactory.createTitledBorder("Postingan"));

        JLabel lblTitle = new JLabel(diary.getTitle());
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(4, 6, 4, 6));

        JTextArea txtContent = new JTextArea(diary.getContent());
        txtContent.setEditable(false);
        txtContent.setLineWrap(true);
        txtContent.setWrapStyleWord(true);
        txtContent.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtContent.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        txtContent.setFocusable(false);

        JScrollPane diaryScroll = new JScrollPane(txtContent);
        diaryScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        diaryScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        diaryScroll.setPreferredSize(new Dimension(0, 170));
        diaryScroll.getVerticalScrollBar().setUnitIncrement(16);

        diaryPanel.add(lblTitle, BorderLayout.NORTH);
        diaryPanel.add(diaryScroll, BorderLayout.CENTER);

        // ===== KOMENTAR =====
        commentPanel = new JPanel(new GridBagLayout());

        JScrollPane commentScroll = new JScrollPane(commentPanel);
        commentScroll.setBorder(BorderFactory.createTitledBorder("Komentar"));
        commentScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        commentScroll.getVerticalScrollBar().setUnitIncrement(16);

        centerPanel.add(diaryPanel, BorderLayout.NORTH);
        centerPanel.add(commentScroll, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        // ================= INPUT =================
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

    // ================= LOAD COMMENTS =================
    private void loadComments() {
        commentPanel.removeAll();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 6, 4, 6);

        // ✅ Ambil semua komentar dari controller
        List<Comment> comments = commentController.getCommentsByDiary(diary.getId());

        if (comments.isEmpty()) {
            JLabel lblEmpty = new JLabel("Belum ada komentar");
            lblEmpty.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            gbc.anchor = GridBagConstraints.CENTER;
            commentPanel.add(lblEmpty, gbc);
        } else {
            UserDAO userDAO = new UserDAO();

            for (Comment c : comments) {
                JPanel card = new JPanel(new BorderLayout());
                card.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                        BorderFactory.createEmptyBorder(6, 6, 6, 6)
                ));

                // Ambil username dari user_id
                String username = "Unknown";
                User user = userDAO.getById(c.getUserId());
                if (user != null) {
                    username = user.getUsername();
                }

                JLabel lblUser = new JLabel(username);
                lblUser.setFont(new Font("Segoe UI", Font.BOLD, 12));

                JTextArea txt = new JTextArea(c.getContent());
                txt.setEditable(false);
                txt.setLineWrap(true);
                txt.setWrapStyleWord(true);
                txt.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                txt.setBackground(null);
                txt.setBorder(null);
                txt.setFocusable(false);

                card.add(lblUser, BorderLayout.NORTH);
                card.add(txt, BorderLayout.CENTER);

                commentPanel.add(card, gbc);
                gbc.gridy++;
            }
        }

        // SPACER biar scroll smooth
        gbc.weighty = 1;
        commentPanel.add(Box.createVerticalGlue(), gbc);

        commentPanel.revalidate();
        commentPanel.repaint();
    }

    // ================= SEND COMMENT =================
    private void sendComment() {
        String content = txtComment.getText().trim();
        if (content.isEmpty()) return;

        if (commentController.addComment(diary.getId(), content)) {
            txtComment.setText("");
            loadComments();
            commentChanged = true;
        }
    }
}
