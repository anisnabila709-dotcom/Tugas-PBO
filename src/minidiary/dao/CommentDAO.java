package minidiary.dao;

import minidiary.model.Comment;
import minidiary.config.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {

    // ===== HITUNG TOTAL KOMENTAR PER DIARY =====
    public int countByDiaryId(int diaryId) {
        String sql = "SELECT COUNT(*) FROM comments WHERE diary_id = ?";
        try (Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, diaryId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    // ===== CREATE COMMENT =====
    public boolean addComment(Comment comment) {
        String sql = "INSERT INTO comments (diary_id, user_id, content) VALUES (?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, comment.getDiaryId());
            ps.setInt(2, comment.getUserId());
            ps.setString(3, comment.getContent());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ===== GET COMMENTS BY DIARY =====
    public List<Comment> getByDiaryId(int diaryId) {
        List<Comment> comments = new ArrayList<>();

        String sql = "SELECT * FROM comments WHERE diary_id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, diaryId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Comment c = new Comment();
                c.setId(rs.getInt("id"));
                c.setDiaryId(rs.getInt("diary_id"));
                c.setUserId(rs.getInt("user_id"));
                c.setContent(rs.getString("content"));

                comments.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comments;
    }

    // ===== DELETE COMMENT =====
    public boolean deleteComment(int commentId) {
        String sql = "DELETE FROM comments WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, commentId);
            ps.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
