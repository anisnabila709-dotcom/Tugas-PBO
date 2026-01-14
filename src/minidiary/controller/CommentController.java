package minidiary.controller;

import java.util.List;
import minidiary.dao.CommentDAO;
import minidiary.model.Comment;

public class CommentController {

    private CommentDAO commentDAO;

    public CommentController() {
        this.commentDAO = new CommentDAO();
    }

    // CREATE
    public boolean addComment(Comment comment) {
        return commentDAO.insert(comment);
    }

    // READ ALL COMMENT FOR A DIARY (atau paragraph)
    public List<Comment> getCommentsByDiaryId(int diaryId) {
        return commentDAO.getByDiaryId(diaryId);
    }

    // UPDATE
    public boolean updateComment(Comment comment) {
        return commentDAO.update(comment);
    }

    // DELETE
    public boolean deleteComment(int id) {
        return commentDAO.delete(id);
    }
}
