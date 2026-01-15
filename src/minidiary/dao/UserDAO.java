package minidiary.dao;

import java.util.ArrayList;
import java.util.List;
import minidiary.model.User;

public class UserDAO {

    // penyimpanan sementara (nanti bisa diganti MySQL)
    private static List<User> users = new ArrayList<>();

    // CREATE / REGISTER
    public boolean insert(User user) {

        // cek email sudah terdaftar atau belum
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(user.getEmail())) {
                return false; // email sudah dipakai
            }
        }

        user.setId(users.size() + 1);
        users.add(user);
        return true;
    }

    // READ - ambil semua user (optional)
    public List<User> getAll() {
        return users;
    }

    // READ - cari user berdasarkan email (untuk login)
    public User getByEmail(String email) {
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }

    // READ - cari user berdasarkan id
    public User getById(int id) {
        for (User u : users) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    // UPDATE
    public boolean update(User user) {
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            if (u.getId() == user.getId()) {
                users.set(i, user);
                return true;
            }
        }
        return false;
    }

    // DELETE
    public boolean delete(int id) {
        User target = null;
        for (User u : users) {
            if (u.getId() == id) {
                target = u;
                break;
            }
        }

        if (target != null) {
            users.remove(target);
            return true;
        }
        return false;
    }
}
