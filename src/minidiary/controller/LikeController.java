package minidiary.controller;

import minidiary.dao.LikeDAO;
import minidiary.model.Like;

public class LikeController {

    private LikeDAO likeDAO;

    public LikeController() {
        this.likeDAO = new LikeDAO();
    }

    // LIKE
    public boolean likeDiary(int userId, int diaryId) {
        Like like = new Like(0, userId, diaryId);
        return likeDAO.insert(like);
    }

    // UNLIKE
    public boolean unlikeDiary(int userId, int diaryId) {
        return likeDAO.delete(diaryId, userId);
    }

    // HITUNG JUMLAH LIKE
    public int getTotalLike(int diaryId) {
        return likeDAO.countByDiaryId(diaryId);
    }
}
