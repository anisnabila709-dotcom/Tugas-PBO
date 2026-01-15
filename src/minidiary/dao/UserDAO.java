package minidiary.dao;

import java.util.ArrayList;
import java.util.List;
import minidiary.model.User;

public class UserDAO {

    // penyimpanan sementara (bisa diganti DB nanti)
    private static List<User> users = new ArrayList<>();

    // CREATE / REGISTER
    public boolean insert(User user) {

        // cek email sudah dipakai
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(user.getEmail())) {
                return false;
            }
        }

        user.setId(users.size() + 1);
        users.add(user);
        return true;
    }

    // digunakan oleh RegisterController
    public boolean register(User user) {
        return insert(user);
    }

    // CEK USERNAME ADA
    public boolean isUsernameExists(String username) {
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    // CEK EMAIL ADA
    public boolean isEmailExists(String email) {
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    // READ - ambil semua user
    public List<User> getAll() {
        return users;
    }

    // digunakan login
    public User getByEmail(String email) {
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }

    // READ - ambil user berdasarkan id
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