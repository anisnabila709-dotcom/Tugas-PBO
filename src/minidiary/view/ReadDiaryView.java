package minidiary.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import minidiary.controller.DiaryController;
import minidiary.model.Diary;

public class ReadView extends JFrame {

    private JTable tableDiary;
    private DefaultTableModel tableModel;
    private DiaryController diaryController;

    public ReadView() {
        setTitle("Mini Diary - Semua Catatan");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        diaryController = new DiaryController();

        initTable();
        loadData();

        setVisible(true);
    }

    private void initTable() {
        String[] columnNames = {"ID", "Judul", "Tanggal", "Konten"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tableDiary = new JTable(tableModel);
        tableDiary.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane scrollPane = new JScrollPane(tableDiary);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadData() {
        tableModel.setRowCount(0);

        List<Diary> diaries = diaryController.getAllDiaries();

        for (Diary d : diaries) {
            Object[] row = {
                d.getId(),
                d.getTitle(),
                d.getDate(),
                d.getContent()
            };
            tableModel.addRow(row);
        }
    }
}
