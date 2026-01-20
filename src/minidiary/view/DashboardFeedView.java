package minidiary.view;

import minidiary.controller.DiaryController;
import minidiary.model.Diary;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DashboardFeedView extends JFrame {

    private DiaryController diaryController;
    private JPanel feedPanel;

    public DashboardFeedView() {
        diaryController = new DiaryController();

        setTitle("Mini Diary");
        setSize(700, 500);
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
        topBar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- KIRI (CREATE) ---
        JButton btnCreate = new JButton("+ Create");
        btnCreate.addActionListener(e -> {
            // GANTI JIKA VIEW CREATE DIARY KAMU NAMANYA BERBEDA
            new ReadDiaryView();
            dispose();
        });

        // --- KANAN (LOGOUT) ---
        JButton btnLogout = new JButton("Logout");
        btnLogout.addActionListener(e -> {
            new LoginView();
            dispose();
        });

        // --- TENGAH (JUDUL) ---
        JLabel lblTitle = new JLabel("Mini Diary Feed");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

        topBar.add(btnCreate, BorderLayout.WEST);
        topBar.add(lblTitle, BorderLayout.CENTER);
        topBar.add(btnLogout, BorderLayout.EAST);

        add(topBar, BorderLayout.NORTH);

        // ===== FEED =====
        feedPanel = new JPanel();
        feedPanel.setLayout(new BoxLayout(feedPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(feedPanel);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadDiary() {
        feedPanel.removeAll();

        List<Diary> diaries = diaryController.getAllDiaries();

        if (diaries.isEmpty()) {
            JLabel lblEmpty = new JLabel("Belum ada diary");
            lblEmpty.setAlignmentX(Component.CENTER_ALIGNMENT);
            feedPanel.add(lblEmpty);
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
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JLabel lblTitle = new JLabel(diary.getTitle());
        lblTitle.setFont(new Font("Arial", Font.BOLD, 14));

        JTextArea txtContent = new JTextArea(diary.getContent());
        txtContent.setEditable(false);
        txtContent.setLineWrap(true);
        txtContent.setWrapStyleWord(true);
        txtContent.setBackground(null);

        JButton btnComment = new JButton("Komentar");
        btnComment.addActionListener(e -> {
            setVisible(false);
            new CommentView(this, diary);
        });

        card.add(lblTitle, BorderLayout.NORTH);
        card.add(txtContent, BorderLayout.CENTER);
        card.add(btnComment, BorderLayout.SOUTH);

        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        return card;
    }
}
