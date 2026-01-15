package minidiary.dao;

import minidiary.config.Database;
import minidiary.model.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {

    private Connection conn;

    public CommentDAO() {
        conn = Database.getConnection();
    }

    // CREATE
    public boolean insert(Comment comment) {
        String sql = "INSERT INTO comment (diary_id, user_id, content) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
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

    // READ BY DIARY
    public List<Comment> getByDiaryId(int diaryId) {
        List<Comment> list = new ArrayList<>();
        String sql = "SELECT * FROM comment WHERE diary_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, diaryId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Comment c = new Comment();
                c.setId(rs.getInt("id"));
                c.setDiaryId(rs.getInt("diary_id"));
                c.setUserId(rs.getInt("user_id"));
                c.setContent(rs.getString("content"));
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // DELETE
    public boolean delete(int id) {
        String sql = "DELETE FROM comment WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
