package minidiary.dao;

import minidiary.config.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LikeDAO {

    // cek apakah user sudah like diary
    public boolean isLiked(int userId, int diaryId) {
        String sql = "SELECT id FROM likes WHERE users_id = ? AND diaries_id = ?";
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

    // like diary
    public boolean like(int userId, int diaryId) {
        String sql = "INSERT INTO likes (users_id, diaries_id) VALUES (?, ?)";
        try (Connection c = Database.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, diaryId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false; // biasanya karena UNIQUE constraint
        }
    }

    // unlike diary
    public boolean unlike(int userId, int diaryId) {
        String sql = "DELETE FROM likes WHERE users_id = ? AND diaries_id = ?";
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

    // total like per diary
    public int getTotalLike(int diaryId) {
        String sql = "SELECT COUNT(*) FROM likes WHERE diaries_id = ?";
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
