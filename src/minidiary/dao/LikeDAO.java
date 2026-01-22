package minidiary.dao;

import minidiary.config.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LikeDAO {

    // ===== CEK APAKAH USER SUDAH LIKE =====
    public boolean isLiked(int userId, int diaryId) {
        String sql = "SELECT id FROM likes WHERE user_id = ? AND diary_id = ?";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, diaryId);

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ===== LIKE =====
    public boolean like(int userId, int diaryId) {
        String sql = "INSERT INTO likes (user_id, diary_id) VALUES (?, ?)";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, diaryId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace(); // 🔥 biar kelihatan error
        }
        return false;
    }

    // ===== UNLIKE =====
    public boolean unlike(int userId, int diaryId) {
        String sql = "DELETE FROM likes WHERE user_id = ? AND diary_id = ?";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, diaryId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ===== TOTAL LIKE =====
    public int getTotalLike(int diaryId) {
        String sql = "SELECT COUNT(*) FROM likes WHERE diary_id = ?";

        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, diaryId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
