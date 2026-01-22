package minidiary.view;

import minidiary.controller.DiaryController;
import minidiary.controller.LikeController;
import minidiary.controller.CommentController;
import minidiary.model.Diary;
import minidiary.util.Session;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DashboardFeedView extends JFrame {

    private DiaryController diaryController;
    private LikeController likeController;
    private CommentController commentController;
    private JPanel feedPanel;

    public DashboardFeedView() {
        diaryController = new DiaryController();
        likeController = new LikeController();
        commentController = new CommentController();

        setTitle("Mini Diary");
        setSize(760, 540);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initUI();
        loadDiary();

        setVisible(true);
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // ===== TOP BAR =====
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(Color.WHITE);
        topBar.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JButton btnCreate = new JButton("+ Create");
        btnCreate.addActionListener(e -> {
            setVisible(false);
            new WriteDiaryView(this);
        });

        JButton btnLogout = new JButton("Logout");
        btnLogout.addActionListener(e -> {
            Session.clear();
            new LoginView();
            dispose();
        });

        JLabel lblTitle = new JLabel("Mini Diary Feed", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));

        topBar.add(btnCreate, BorderLayout.WEST);
        topBar.add(lblTitle, BorderLayout.CENTER);
        topBar.add(btnLogout, BorderLayout.EAST);

        add(topBar, BorderLayout.NORTH);

        // ===== FEED =====
        feedPanel = new JPanel();
        feedPanel.setLayout(new BoxLayout(feedPanel, BoxLayout.Y_AXIS));
        feedPanel.setBackground(new Color(245, 245, 245));

        JScrollPane scrollPane = new JScrollPane(feedPanel);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);
    }

    // ==============================
    // LOAD DIARY
    // ==============================
    public void loadDiary() {
        feedPanel.removeAll();

        List<Diary> diaries = diaryController.getAllDiaries();
        for (Diary d : diaries) {
            feedPanel.add(createDiaryCard(d));
        }

        feedPanel.revalidate();
        feedPanel.repaint();
    }

    private JPanel createDiaryCard(Diary diary) {

        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ===== CONTENT =====
        JTextArea txtContent = new JTextArea(diary.getContent());
        txtContent.setLineWrap(true);
        txtContent.setWrapStyleWord(true);
        txtContent.setEditable(false);
        txtContent.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtContent.setBackground(Color.WHITE);

        // ===== FOOTER =====
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(Color.WHITE);

        JLabel lblUser = new JLabel("- " + diary.getUsername());
        lblUser.setFont(new Font("Segoe UI", Font.ITALIC, 12));

        JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        iconPanel.setBackground(Color.WHITE);

        int diaryId = diary.getId();
        int userId = Session.getUserId();

        // ===== EDIT + DELETE (HANYA MILIK SENDIRI) =====
if (Session.isLoggedIn() && diary.getUserId() == Session.getUserId()) {

    // EDIT
    JButton btnEdit = new JButton("✏");
    styleIconButton(btnEdit);

    btnEdit.addActionListener(e -> {
        setVisible(false);
        new EditDiaryView(this, diary);
    });

    iconPanel.add(btnEdit);

    // DELETE
    JButton btnDelete = new JButton("🗑");
    styleIconButton(btnDelete);

    btnDelete.addActionListener(e -> {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Yakin ingin menghapus diary ini?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            boolean deleted = diaryController.deleteDiary(diary.getId());

            if (deleted) {
                JOptionPane.showMessageDialog(this, "Diary berhasil dihapus!");
                loadDiary(); // refresh feed
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menghapus diary!");
            }
        }
    });

    iconPanel.add(btnDelete);
}


        // ===== LIKE =====
        boolean liked = Session.isLoggedIn() &&
                likeController.isLiked(userId, diaryId);

        int totalLike = likeController.getTotalLike(diaryId);

        JButton btnLike = new JButton();
        styleIconButton(btnLike);
        updateLikeButton(btnLike, liked, totalLike);

        btnLike.addActionListener(e -> {
            if (!Session.isLoggedIn()) {
                JOptionPane.showMessageDialog(this, "Silakan login dulu ❤️");
                return;
            }

            likeController.toggleLike(userId, diaryId);
            loadDiary(); // refresh
        });

        // ===== COMMENT (TIDAK DIUBAH) =====
        int totalComment = commentController.getTotalComment(diaryId);
        JButton btnComment = new JButton("💬 " + totalComment);
        styleIconButton(btnComment);

        btnComment.addActionListener(e -> {
            setVisible(false);
            new CommentView(this, diary);
        });

        iconPanel.add(btnLike);
        iconPanel.add(btnComment);

        footer.add(lblUser, BorderLayout.WEST);
        footer.add(iconPanel, BorderLayout.EAST);

        card.add(txtContent, BorderLayout.CENTER);
        card.add(footer, BorderLayout.SOUTH);

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(new Color(245, 245, 245));
        wrapper.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        wrapper.add(card);

        return wrapper;
    }

    // ==============================
    // HELPER
    // ==============================
    private void updateLikeButton(JButton btn, boolean liked, int total) {
        if (liked) {
            btn.setText("❤️ " + total);
            btn.setForeground(Color.RED);
        } else {
            btn.setText("🤍 " + total);
            btn.setForeground(new Color(120, 120, 120));
        }
    }

    private void styleIconButton(JButton btn) {
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
    }
}
