package minidiary.dao;

import minidiary.model.Comment;
import minidiary.config.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {

    // ==============================
    // HITUNG TOTAL KOMENTAR (DIPAKAI UI)
    // ==============================
    public int getTotalComment(int diaryId) {
        String sql = """
            SELECT COUNT(*) AS total
            FROM comments
            WHERE diary_id = ?
        """;

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, diaryId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("total");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // ==============================
    // HITUNG TOTAL KOMENTAR (OPSIONAL)
    // ==============================
    public int countByDiaryId(int diaryId) {
        return getTotalComment(diaryId);
    }

    // ==============================
    // TAMBAH KOMENTAR
    // ==============================
    public boolean addComment(Comment comment) {
        String sql = """
            INSERT INTO comments (diary_id, user_id, content)
            VALUES (?, ?, ?)
        """;

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, comment.getDiaryId());
            ps.setInt(2, comment.getUserId());
            ps.setString(3, comment.getContent());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ==============================
    // AMBIL KOMENTAR PER DIARY
    // ==============================
    public List<Comment> getByDiaryId(int diaryId) {
        List<Comment> comments = new ArrayList<>();

        String sql = """
            SELECT *
            FROM comments
            WHERE diary_id = ?
            ORDER BY id ASC
        """;

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

    // ==============================
    // HAPUS KOMENTAR
    // ==============================
    public boolean deleteComment(int commentId) {
        String sql = "DELETE FROM comments WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, commentId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
