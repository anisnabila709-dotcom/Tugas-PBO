package minidiary.dao;

import minidiary.config.Database;
import minidiary.model.Diary;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiaryDAO {

    // ================= CREATE =================
    public boolean insert(Diary diary) {
        String sql = "INSERT INTO diaries (user_id, title, content) VALUES (?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, diary.getUserId());
            ps.setString(2, diary.getTitle());
            ps.setString(3, diary.getContent());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ================= READ ALL =================
    public List<Diary> getAll() {
        List<Diary> list = new ArrayList<>();
        String sql = "SELECT * FROM diaries ORDER BY id DESC";

        try (Connection conn = Database.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Diary d = new Diary();
                d.setId(rs.getInt("id"));
                d.setUserId(rs.getInt("user_id"));
                d.setTitle(rs.getString("title"));
                d.setContent(rs.getString("content"));
                list.add(d);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ================= READ BY ID =================
    public Diary getById(int id) {
        String sql = "SELECT * FROM diaries WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Diary d = new Diary();
                d.setId(rs.getInt("id"));
                d.setUserId(rs.getInt("user_id"));
                d.setTitle(rs.getString("title"));
                d.setContent(rs.getString("content"));
                return d;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ================= UPDATE =================
    public boolean update(Diary diary) {
        String sql = "UPDATE diaries SET title = ?, content = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, diary.getTitle());
            ps.setString(2, diary.getContent());
            ps.setInt(3, diary.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ================= DELETE =================
    public boolean delete(int id) {
        String sql = "DELETE FROM diaries WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
