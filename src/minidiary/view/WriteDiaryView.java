package minidiary.view;

import javax.swing.*;
import java.awt.*;
import minidiary.controller.DiaryController;
import minidiary.model.Diary;
import minidiary.util.MessageUtil;
import minidiary.util.Session;
import minidiary.util.Validator;

public class WriteDiaryView extends JFrame {

    private JTextField txtTitle;
    private JTextArea txtContent;
    private DiaryController diaryController;

    public WriteDiaryView() {
        setTitle("Tulis Diary");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        diaryController = new DiaryController();

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        txtTitle = new JTextField();
        txtContent = new JTextArea();

        JButton btnSave = new JButton("Simpan");
        JButton btnBack = new JButton("Kembali");

        btnSave.addActionListener(e -> saveDiary());
        btnBack.addActionListener(e -> {
            new DashboardFeedView();
            dispose();
        });

        JPanel top = new JPanel(new GridLayout(2, 1, 5, 5));
        top.add(new JLabel("Judul:"));
        top.add(txtTitle);

        JPanel bottom = new JPanel();
        bottom.add(btnSave);
        bottom.add(btnBack);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(txtContent), BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    private void saveDiary() {
        if (!Session.isLoggedIn()) {
            MessageUtil.showError(this, "Silakan login terlebih dahulu!");
            return;
        }

        String title = txtTitle.getText();
        String content = txtContent.getText();

        if (Validator.isEmpty(title) || Validator.isEmpty(content)) {
            MessageUtil.showError(this, "Judul dan isi tidak boleh kosong!");
            return;
        }

        Diary diary = new Diary();
        diary.setTitle(title);
        diary.setContent(content);
        diary.setUserId(Session.getCurrentUser().getId());

        boolean success = diaryController.addDiary(diary);

        if (success) {
            MessageUtil.showInfo(this, "Diary berhasil disimpan!");
            txtTitle.setText("");
            txtContent.setText("");
        } else {
            MessageUtil.showError(this, "Gagal menyimpan diary!");
        }
    }
}