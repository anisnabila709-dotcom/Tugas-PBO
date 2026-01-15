package minidiary.dao;

import java.util.ArrayList;
import java.util.List;
import minidiary.model.Like;

public class LikeDAO {

    private static List<Like> likes = new ArrayList<>();

    // CREATE – tambah like
    public boolean insert(Like like) {

        // cek apakah user sudah like diary ini (opsional)
        for (Like l : likes) {
            if (l.getDiaryId() == like.getDiaryId() &&
                l.getUserId() == like.getUserId()) {
                return false; // sudah like
            }
        }

        likes.add(like);
        return true;
    }

    // READ – hitung jumlah like berdasarkan diary id
    public int countByDiaryId(int diaryId) {
        int total = 0;
        for (Like like : likes) {
            if (like.getDiaryId() == diaryId) {
                total++;
            }
        }
        return total;
    }

    // DELETE – remove like (unlike)
    public boolean delete(int diaryId, int userId) {
        Like target = null;
        for (Like like : likes) {
            if (like.getDiaryId() == diaryId && like.getUserId() == userId) {
                target = like;
                break;
            }
        }

        if (target != null) {
            likes.remove(target);
            return true;
        }
        return false;
    }
}
