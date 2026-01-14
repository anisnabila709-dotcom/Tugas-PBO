package minidiary.controller;

import java.util.List;
import minidiary.dao.DiaryDAO;
import minidiary.model.Diary;

public class DiaryController {

    private DiaryDAO diaryDAO;

    public DiaryController() {
        this.diaryDAO = new DiaryDAO();
    }

    // CREATE
    public boolean addDiary(Diary diary) {
        return diaryDAO.insert(diary);
    }

    // READ ALL
    public List<Diary> getAllDiaries() {
        return diaryDAO.getAll();
    }

    // READ by ID (opsional)
    public Diary getDiaryById(int id) {
        return diaryDAO.getById(id);
    }

    // UPDATE
    public boolean updateDiary(Diary diary) {
        return diaryDAO.update(diary);
    }

    // DELETE
    public boolean deleteDiary(int id) {
        return diaryDAO.delete(id);
    }
}
