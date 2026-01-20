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
    private DashboardFeedView dashboard;

    public WriteDiaryView(DashboardFeedView dashboard) {
        this.dashboard = dashboard;
        diaryController = new DiaryController();

        setTitle("Mini Diary - Tulis Diary");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initUI();
        setVisible(true);
    }

    private void initUI() {
        JPanel main = new JPanel(new BorderLayout(10, 10));
        main.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel header = new JLabel("Tulis Diary");
        header.setFont(new Font("Segoe UI", Font.BOLD, 20));
        main.add(header, BorderLayout.NORTH);

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));

        form.add(new JLabel("Judul"));
        txtTitle = new JTextField();
        txtTitle.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        form.add(txtTitle);

        form.add(Box.createVerticalStrut(15));

        form.add(new JLabel("Isi Diary"));
        txtContent = new JTextArea(10, 30);
        txtContent.setLineWrap(true);
        txtContent.setWrapStyleWord(true);
        form.add(new JScrollPane(txtContent));

        main.add(form, BorderLayout.CENTER);

        JButton btnSave = new JButton("Simpan");
        JButton btnBack = new JButton("Kembali");

        btnSave.addActionListener(e -> saveDiary());
        btnBack.addActionListener(e -> {
            dispose();
            dashboard.setVisible(true);
        });

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.add(btnSave);
        btnPanel.add(btnBack);

        main.add(btnPanel, BorderLayout.SOUTH);
        add(main);
    }

    private void saveDiary() {
        if (!Session.isLoggedIn()) {
            MessageUtil.showError(this, "Silakan login dulu!");
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

        if (diaryController.addDiary(diary)) {
            MessageUtil.showInfo(this, "Diary berhasil disimpan!");
            dashboard.loadDiary();
            dashboard.setVisible(true);
            dispose();
        } else {
            MessageUtil.showError(this, "Gagal menyimpan diary!");
        }
    }
}
