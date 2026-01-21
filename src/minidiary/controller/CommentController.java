package minidiary.controller;

import minidiary.dao.CommentDAO;
import minidiary.model.Comment;
import minidiary.model.User;
import minidiary.util.Session;

import java.util.List;

public class CommentController {

    private CommentDAO commentDAO;

    public CommentController() {
        commentDAO = new CommentDAO();
    }

    // TAMBAH KOMENTAR
    public boolean addComment(int diaryId, String content) {

        // cek login
        User user = Session.getCurrentUser();
        if (user == null) {
            return false;
        }

        // validasi komentar
        if (content == null || content.trim().isEmpty()) {
            return false;
        }

        Comment comment = new Comment();
        comment.setDiaryId(diaryId);
        comment.setUserId(user.getId());
        comment.setContent(content);

        return commentDAO.addComment(comment);
    }

    // AMBIL KOMENTAR PER DIARY
    public List<Comment> getCommentsByDiary(int diaryId) {
        return commentDAO.getByDiaryId(diaryId);
    }

    // HAPUS KOMENTAR (opsional)
    public boolean deleteComment(int commentId) {
        return commentDAO.deleteComment(commentId);
    }

    // HITUNG TOTAL KOMENTAR
    public int getTotalComment(int diaryId) {
        return commentDAO.countByDiaryId(diaryId);
    }

}
