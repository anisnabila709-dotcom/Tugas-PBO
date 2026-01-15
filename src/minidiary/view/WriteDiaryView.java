package minidiary.view;

import javax.swing.*;
import java.awt.*;

public class WriteDiaryView extends JFrame {

    private JTextField txtTitle;
    private JTextArea txtContent;

    public WriteDiaryView() {
        setTitle("Tulis Diary");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        txtTitle = new JTextField();
        txtContent = new JTextArea();

        JButton btnSave = new JButton("Simpan");
        JButton btnBack = new JButton("Kembali");

        btnSave.addActionListener(e -> {
            String title = txtTitle.getText();
            String content = txtContent.getText();

            if (title.isEmpty() || content.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Judul dan isi tidak boleh kosong");
            } else {
                JOptionPane.showMessageDialog(this, "Diary berhasil disimpan (sementara)");
                txtTitle.setText("");
                txtContent.setText("");
            }
        });

        btnBack.addActionListener(e -> {
            new DashboardView();
            dispose();
        });

        JPanel top = new JPanel(new GridLayout(2, 1));
        top.add(new JLabel("Judul:"));
        top.add(txtTitle);

        JPanel bottom = new JPanel();
        bottom.add(btnSave);
        bottom.add(btnBack);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(txtContent), BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        setVisible(true);
    }
}
