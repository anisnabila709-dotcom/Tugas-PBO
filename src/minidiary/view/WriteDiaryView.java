package minidiary.view;

import minidiary.controller.DiaryController;

import javax.swing.*;
import java.awt.*;

public class WriteDiaryView extends JFrame {

    private JTextField titleField;
    private JTextArea contentArea;

    public WriteDiaryView() {
        setTitle("Write Diary");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel titleLabel = new JLabel("Title:");
        JLabel contentLabel = new JLabel("Content:");

        titleField = new JTextField();
        contentArea = new JTextArea();
        contentArea.setLineWrap(true);

        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        saveButton.addActionListener(e -> {
            DiaryController controller = new DiaryController();
            boolean success = controller.createDiary(
                    titleField.getText(),
                    contentArea.getText()
            );

            if (success) {
                JOptionPane.showMessageDialog(this, "Diary berhasil disimpan");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan diary");
            }
        });

        cancelButton.addActionListener(e -> dispose());

        JPanel form = new JPanel(new GridLayout(4, 1, 5, 5));
        form.add(titleLabel);
        form.add(titleField);
        form.add(contentLabel);
        form.add(new JScrollPane(contentArea));

        JPanel buttons = new JPanel();
        buttons.add(saveButton);
        buttons.add(cancelButton);

        add(form, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
    }
}
