package minidiary.dao;

import minidiary.model.Comment;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {

    // DATABASE SEMENTARA
    private static List<Comment> comments = new ArrayList<>();
    private static int autoIncrementId = 1;

    // ===== CREATE =====
    public boolean addComment(Comment comment) {
        comment.setId(autoIncrementId++);
        comments.add(comment);
        return true;
    }

    // ===== READ (BERDASARKAN DIARY) =====
    public List<Comment> getByDiaryId(int diaryId) {
        List<Comment> result = new ArrayList<>();
        for (Comment c : comments) {
            if (c.getDiaryId() == diaryId) {
                result.add(c);
            }
        }
        return result;
    }

    // ===== DELETE =====
    public boolean deleteComment(int commentId) {
        for (Comment c : comments) {
            if (c.getId() == commentId) {
                comments.remove(c);
                return true;
            }
        }
        return false;
    }
}
