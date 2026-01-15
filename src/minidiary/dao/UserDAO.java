package minidiary.dao;

import minidiary.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // DATABASE SEMENTARA (IN-MEMORY)
    private static List<User> users = new ArrayList<>();

    // ===== REGISTER =====
    public boolean isUsernameExists(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmailExists(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    public boolean register(User user) {
        users.add(user);
        return true;
    }

    // ===== LOGIN =====
    public User getByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }
}
