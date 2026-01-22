package minidiary.view;

import minidiary.controller.DiaryController;
import minidiary.model.Diary;

import javax.swing.*;
import java.awt.*;

public class EditDiaryView extends JFrame {

    private DashboardFeedView dashboard;
    private Diary diary;
    private DiaryController diaryController;

    private JTextField txtTitle;
    private JTextArea txtContent;

    public EditDiaryView(DashboardFeedView dashboard, Diary diary) {
        this.dashboard = dashboard;
        this.diary = diary;
        this.diaryController = new DiaryController();

        setTitle("Edit Diary");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initUI();
        setVisible(true);
    }

    private void initUI() {
        setLayout(new BorderLayout(10, 10));

        // ===== HEADER =====
        JLabel lblHeader = new JLabel("✏ Edit Diary");
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblHeader.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        add(lblHeader, BorderLayout.NORTH);

        // ===== FORM =====
        JPanel form = new JPanel(new BorderLayout(10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        txtTitle = new JTextField(diary.getTitle());
        txtTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));

        txtContent = new JTextArea(diary.getContent());
        txtContent.setLineWrap(true);
        txtContent.setWrapStyleWord(true);
        txtContent.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        form.add(txtTitle, BorderLayout.NORTH);
        form.add(new JScrollPane(txtContent), BorderLayout.CENTER);

        add(form, BorderLayout.CENTER);

        // ===== BUTTON =====
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton btnCancel = new JButton("Batal");
        JButton btnSave = new JButton("Update");

        btnCancel.addActionListener(e -> {
            dispose();
            dashboard.setVisible(true);
        });

        btnSave.addActionListener(e -> updateDiary());

        btnPanel.add(btnCancel);
        btnPanel.add(btnSave);

        add(btnPanel, BorderLayout.SOUTH);
    }

    private void updateDiary() {
        String title = txtTitle.getText().trim();
        String content = txtContent.getText().trim();

        if (title.isEmpty() || content.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Judul & isi tidak boleh kosong");
            return;
        }

        diary.setTitle(title);
        diary.setContent(content);

        if (diaryController.updateDiary(diary)) {
            JOptionPane.showMessageDialog(this, "Diary berhasil diperbarui ✨");
            dashboard.loadDiary();
            dispose();
            dashboard.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Gagal memperbarui diary");
        }
    }
}
