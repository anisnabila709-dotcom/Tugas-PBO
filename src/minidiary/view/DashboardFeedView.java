package minidiary.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import minidiary.controller.DiaryController;
import minidiary.model.Diary;
import minidiary.util.Session;

public class DashboardFeedView extends JFrame {

    private DiaryController diaryController;
    private JPanel feedPanel;

    public DashboardFeedView() {
        setTitle("Mini Diary");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        diaryController = new DiaryController();

        initUI();
        loadDiaryFeed();

        setVisible(true);
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // ===== TOP BAR =====
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel lblTitle = new JLabel("Mini Diary");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton btnCreate = new JButton("+ Create");
        JButton btnLogout = new JButton("Logout");

        btnCreate.addActionListener(e -> {
            new WriteDiaryView();
            dispose();
        });

        btnLogout.addActionListener(e -> {
            Session.clear();
            new LoginView();
            dispose();
        });

        rightPanel.add(btnCreate);
        rightPanel.add(btnLogout);

        topBar.add(lblTitle, BorderLayout.WEST);
        topBar.add(rightPanel, BorderLayout.EAST);

        add(topBar, BorderLayout.NORTH);

        // ===== FEED PANEL =====
        feedPanel = new JPanel();
        feedPanel.setLayout(new BoxLayout(feedPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(feedPanel);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadDiaryFeed() {
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
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createLineBorder(Color.LIGHT_GRAY)
        ));

        JLabel lblTitle = new JLabel(diary.getTitle());
        lblTitle.setFont(new Font("Arial", Font.BOLD, 14));

        JTextArea txtContent = new JTextArea(diary.getContent());
        txtContent.setLineWrap(true);
        txtContent.setWrapStyleWord(true);
        txtContent.setEditable(false);
        txtContent.setBackground(null);

        JLabel lblFooter = new JLabel(
                "oleh User ID: " + diary.getUserId()
        );
        lblFooter.setFont(new Font("Arial", Font.ITALIC, 11));

        JButton btnComment = new JButton("Komentar");
        btnComment.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    "Fitur komentar akan dibuat");
        });

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(lblFooter, BorderLayout.WEST);
        bottomPanel.add(btnComment, BorderLayout.EAST);

        card.add(lblTitle, BorderLayout.NORTH);
        card.add(txtContent, BorderLayout.CENTER);
        card.add(bottomPanel, BorderLayout.SOUTH);

        return card;
    }
}
