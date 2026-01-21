package minidiary.controller;

import minidiary.dao.LikeDAO;

public class LikeController {

    private LikeDAO dao = new LikeDAO();

    public boolean isLiked(int userId, int diaryId) {
        return dao.isLiked(userId, diaryId);
    }

    // toggle like (IG style)
    public void toggleLike(int userId, int diaryId) {
        if (dao.isLiked(userId, diaryId)) {
            dao.unlike(userId, diaryId);
        } else {
            dao.like(userId, diaryId);
        }
    }

    public int getTotalLike(int diaryId) {
        return dao.getTotalLike(diaryId);
    }
}
