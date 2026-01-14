package minidiary.dao;

import java.util.ArrayList;
import java.util.List;
import minidiary.model.Paragraph;

public class ParagraphDAO {

    private static List<Paragraph> paragraphs = new ArrayList<>();

    // CREATE
    public boolean insert(Paragraph paragraph) {
        paragraphs.add(paragraph);
        return true;
    }

    // READ – semua paragraf milik 1 diary
    public List<Paragraph> getByDiaryId(int diaryId) {
        List<Paragraph> list = new ArrayList<>();
        for (Paragraph p : paragraphs) {
            if (p.getDiaryId() == diaryId) {
                list.add(p);
            }
        }
        return list;
    }

    // UPDATE
    public boolean update(Paragraph paragraph) {
        for (int i = 0; i < paragraphs.size(); i++) {
            Paragraph p = paragraphs.get(i);
            if (p.getId() == paragraph.getId()) {
                paragraphs.set(i, paragraph);
                return true;
            }
        }
        return false;
    }

    // DELETE
    public boolean delete(int id) {
        Paragraph target = null;
        for (Paragraph p : paragraphs) {
            if (p.getId() == id) {
                target = p;
                break;
            }
        }
        if (target != null) {
            paragraphs.remove(target);
            return true;
        }
        return false;
    }
}
