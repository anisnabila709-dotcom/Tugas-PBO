package minidiary.dao;

import minidiary.config.Database;
import minidiary.model.Diary;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiaryDAO {

    private Connection conn;

    public DiaryDAO() {
        conn = Database.getConnection();
    }

    // CREATE
    public boolean insert(Diary diary) {
        String sql = "INSERT INTO diary (title, content, user_id) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, diary.getTitle());
            ps.setString(2, diary.getContent());
            ps.setInt(3, diary.getUserId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // READ ALL
    public List<Diary> getAll() {
        List<Diary> list = new ArrayList<>();
        String sql = "SELECT * FROM diary";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Diary d = new Diary();
                d.setId(rs.getInt("id"));
                d.setTitle(rs.getString("title"));
                d.setContent(rs.getString("content"));
                d.setUserId(rs.getInt("user_id"));
                list.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // READ BY ID (stub aman)
    public Diary getById(int id) {
        String sql = "SELECT * FROM diary WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Diary d = new Diary();
                d.setId(rs.getInt("id"));
                d.setTitle(rs.getString("title"));
                d.setContent(rs.getString("content"));
                d.setUserId(rs.getInt("user_id"));
                return d;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // UPDATE
    public boolean update(Diary diary) {
        String sql = "UPDATE diary SET title = ?, content = ? WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, diary.getTitle());
            ps.setString(2, diary.getContent());
            ps.setInt(3, diary.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    public boolean delete(int id) {
        String sql = "DELETE FROM diary WHERE id = ?";

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

