package minidiary.controller;

import minidiary.dao.UserDAO;
import minidiary.model.User;

public class LoginController {

    private UserDAO userDAO;

    public LoginController() {
        this.userDAO = new UserDAO();
    }

    // LOGIN
    public User login(String email, String password) {
        User user = userDAO.getByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            return user; // login berhasil
        }

        return null; // login gagal
    }
}


