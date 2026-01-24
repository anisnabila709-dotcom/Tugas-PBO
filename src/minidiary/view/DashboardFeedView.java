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
        scrollPane.getVerticalScrollBar().setUnitIncrement(18);
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

    // ==============================
    // DIARY CARD
    // ==============================
    private JPanel createDiaryCard(Diary diary) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
        ));

        // ===== CONTENT + SELENGKAPNYA =====
        String fullText = diary.getContent();
        String[] words = fullText.split("\\s+");

        int WORD_LIMIT = 50;
        boolean isLong = words.length > WORD_LIMIT;

        String shortText;
        if (isLong) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < WORD_LIMIT; i++) {
                sb.append(words[i]).append(" ");
            }
            shortText = sb.toString().trim() + "...";
        } else {
            shortText = fullText;
        }

        JTextArea txtContent = new JTextArea(shortText);
        txtContent.setLineWrap(true);
        txtContent.setWrapStyleWord(true);
        txtContent.setEditable(false);
        txtContent.setFocusable(false);
        txtContent.setOpaque(false);
        txtContent.setBorder(null);
        txtContent.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JLabel lblToggle = new JLabel(isLong ? "Selengkapnya" : "");
        lblToggle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblToggle.setForeground(new Color(150, 150, 150));
        lblToggle.setCursor(new Cursor(Cursor.HAND_CURSOR));

        final boolean[] expanded = { false };
        lblToggle.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                lblToggle.setForeground(new Color(0, 102, 204));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                lblToggle.setForeground(new Color(150, 150, 150));
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                expanded[0] = !expanded[0];
                txtContent.setText(expanded[0] ? fullText : shortText);
                lblToggle.setText(expanded[0] ? "Lebih sedikit" : "Selengkapnya");
            }
        });

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.add(txtContent, BorderLayout.CENTER);
        if (isLong) contentPanel.add(lblToggle, BorderLayout.SOUTH);

        // ===== FOOTER (USERNAME + ICONS sejajar, dengan jarak tipis) =====
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(Color.WHITE);
        footer.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0)); // jarak tipis dari isi

        JLabel lblUser = new JLabel("- " + diary.getUsername());
        lblUser.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblUser.setForeground(new Color(120, 120, 120));

        JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        iconPanel.setOpaque(false);

        int diaryId = diary.getId();

        if (Session.isLoggedIn() && diary.getUserId() == Session.getUserId()) {
            JButton btnEdit = new JButton("✏");
            styleIconButton(btnEdit);
            btnEdit.addActionListener(e -> {
                setVisible(false);
                new EditDiaryView(this, diary);
            });
            iconPanel.add(btnEdit);

            JButton btnDelete = new JButton("🗑");
            styleIconButton(btnDelete);
            btnDelete.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Yakin ingin menghapus diary ini?",
                        "Konfirmasi",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    diaryController.deleteDiary(diaryId);
                    loadDiary();
                }
            });
            iconPanel.add(btnDelete);
        }

        JButton btnLike = new JButton();
        styleIconButton(btnLike);
        updateLikeButton(
                btnLike,
                Session.isLoggedIn() && likeController.isLiked(Session.getUserId(), diaryId),
                likeController.getTotalLike(diaryId)
        );
        btnLike.addActionListener(e -> {
            if (!Session.isLoggedIn()) {
                JOptionPane.showMessageDialog(this, "Silakan login dulu ❤️");
                return;
            }
            int uid = Session.getUserId();
            likeController.toggleLike(uid, diaryId);
            updateLikeButton(
                    btnLike,
                    likeController.isLiked(uid, diaryId),
                    likeController.getTotalLike(diaryId)
            );
        });

        JButton btnComment = new JButton("💬 " + commentController.getTotalComment(diaryId));
        styleIconButton(btnComment);
        btnComment.addActionListener(e -> {
            setVisible(false);
            new CommentView(this, diary);
        });

        iconPanel.add(btnLike);
        iconPanel.add(btnComment);

        footer.add(lblUser, BorderLayout.WEST);
        footer.add(iconPanel, BorderLayout.EAST);

        // ===== SUSUNAN =====
        card.add(contentPanel, BorderLayout.NORTH);
        card.add(footer, BorderLayout.SOUTH);

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(new Color(245, 245, 245));
        wrapper.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        wrapper.add(card);

        return wrapper;
    }

    // ==============================
    // HELPER METHODS
    // ==============================
    private void updateLikeButton(JButton btn, boolean liked, int total) {
        btn.setText((liked ? "❤️ " : "🤍 ") + total);
        btn.setForeground(liked ? Color.RED : new Color(120, 120, 120));
    }

    private void styleIconButton(JButton btn) {
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
    }
}
