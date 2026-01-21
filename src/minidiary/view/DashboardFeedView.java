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

        /* 🔴 REALTIME-NYA
        Timer timer = new Timer(3000, e -> loadDiary());
        timer.start();*/ 

        setVisible(true); 

    }

    private void initUI() {
        setLayout(new BorderLayout());

        // ===== TOP BAR =====
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(Color.WHITE);
        topBar.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JButton btnCreate = new JButton("+ Create");
        btnCreate.setFocusPainted(false);
        btnCreate.addActionListener(e -> {
            setVisible(false);
            new WriteDiaryView(this);
        });

        JButton btnLogout = new JButton("Logout");
        btnLogout.setFocusPainted(false);
        btnLogout.addActionListener(e -> {
            Session.clear();
            new LoginView();
            dispose();
        });

        JLabel lblTitle = new JLabel("Mini Diary Feed");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

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
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void loadDiary() {
        feedPanel.removeAll();

        List<Diary> diaries = diaryController.getAllDiaries();

        if (diaries.isEmpty()) {
            JLabel empty = new JLabel("Belum ada diary");
            empty.setFont(new Font("Segoe UI", Font.ITALIC, 14));
            empty.setAlignmentX(Component.CENTER_ALIGNMENT);
            feedPanel.add(Box.createVerticalStrut(20));
            feedPanel.add(empty);
        } else {
            for (Diary d : diaries) {
                feedPanel.add(createDiaryCard(d));
            }
        }

        feedPanel.revalidate();
        feedPanel.repaint();
    }

    private JPanel createDiaryCard(Diary diary) {

        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 210, 210), 1, true),
                BorderFactory.createEmptyBorder(6, 6, 6, 6)
        ));

        // ===== HEADER =====
        JLabel lblTitle = new JLabel(diary.getTitle());
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setBorder(BorderFactory.createEmptyBorder(8, 12, 4, 12));
        header.add(lblTitle, BorderLayout.WEST);
        header.add(new JSeparator(), BorderLayout.SOUTH);

        // ===== CONTENT =====
        String fullContent = diary.getContent();
        int LIMIT = 250;
        boolean isLong = fullContent.length() > LIMIT;

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);

        // TEXT DIARY
        JTextArea txtContent = new JTextArea();
        txtContent.setEditable(false);
        txtContent.setLineWrap(true);
        txtContent.setWrapStyleWord(true);
        txtContent.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtContent.setForeground(Color.BLACK);
        txtContent.setBackground(Color.WHITE);
        txtContent.setBorder(BorderFactory.createEmptyBorder(6, 12, 0, 12));

        String shortText = isLong
                ? fullContent.substring(0, LIMIT) + "..."
                : fullContent;

        txtContent.setText(shortText);
        contentPanel.add(txtContent);

        // ===== SELENGKAPNYA =====
        if (isLong) {
            JLabel lblToggle = new JLabel("selengkapnya");
            lblToggle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            lblToggle.setForeground(new Color(150, 150, 150));
            lblToggle.setCursor(new Cursor(Cursor.HAND_CURSOR));
            lblToggle.setBorder(BorderFactory.createEmptyBorder(2, 12, 6, 12));
            lblToggle.setAlignmentX(Component.LEFT_ALIGNMENT); // ⬅️ PENTING

            final boolean[] expanded = {false};

            lblToggle.addMouseListener(new java.awt.event.MouseAdapter() {

                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    expanded[0] = !expanded[0];
                    txtContent.setText(expanded[0] ? fullContent : shortText);
                    lblToggle.setText(expanded[0]
                            ? "tampilkan lebih sedikit"
                            : "selengkapnya");
                }

                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    lblToggle.setForeground(new Color(30, 144, 255));
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    lblToggle.setForeground(new Color(150, 150, 150));
                }
            });

            contentPanel.add(lblToggle);
        }


        // ===== FOOTER =====
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(Color.WHITE);
        footer.setBorder(BorderFactory.createEmptyBorder(4, 12, 8, 12));

        // Username (kiri)
        String username = diary.getUsername() != null ? diary.getUsername() : "Unknown";
        JLabel lblUser = new JLabel("- " + username);
        lblUser.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblUser.setForeground(new Color(120, 120, 120));

        // Icon panel (kanan bawah)
        JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        iconPanel.setBackground(Color.WHITE);

        int totalLikes = likeController.getTotalLike(diary.getId());
        boolean liked = Session.isLoggedIn() &&
                likeController.isLiked(Session.getUserId(), diary.getId());

        JButton btnLike = new JButton();
        styleIconButton(btnLike);

        if (liked) {
            btnLike.setText("❤️ " + totalLikes);
            btnLike.setForeground(Color.RED);
        } else {
            btnLike.setText("♡ " + totalLikes);
        }

        btnLike.addActionListener(e -> {
            if (!Session.isLoggedIn()) {
                JOptionPane.showMessageDialog(this, "Silakan login dulu.");
                return;
            }

            likeController.toggleLike(Session.getUserId(), diary.getId());

            int updatedLikes = likeController.getTotalLike(diary.getId());
            boolean nowLiked = likeController.isLiked(Session.getUserId(), diary.getId());

            if (nowLiked) {
                btnLike.setText("❤️ " + updatedLikes);
                btnLike.setForeground(Color.RED);
            } else {
                btnLike.setText("♡ " + updatedLikes);
                btnLike.setForeground(new Color(100, 100, 100));
            }
        });

        int totalComments = commentController.getTotalComment(diary.getId());

        JButton btnComment = new JButton("💬 " + totalComments);
        styleIconButton(btnComment);

        btnComment.addActionListener(e -> {
            setVisible(false);
            new CommentView(this, diary);
        });


        iconPanel.add(btnLike);
        iconPanel.add(btnComment);

        footer.add(lblUser, BorderLayout.WEST);
        footer.add(iconPanel, BorderLayout.EAST);

        // ===== ADD =====
        card.add(header, BorderLayout.NORTH);
        card.add(contentPanel, BorderLayout.CENTER);

        card.add(footer, BorderLayout.SOUTH);

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(new Color(245, 245, 245));
        wrapper.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        wrapper.add(card);

        return wrapper;
    }

    private void styleIconButton(JButton btn) {
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        btn.setForeground(new Color(100, 100, 100));
    }
}
