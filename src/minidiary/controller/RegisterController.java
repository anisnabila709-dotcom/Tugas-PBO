package minidiary.controller;

import minidiary.dao.UserDAO;
import minidiary.model.User;
import minidiary.util.MessageUtil;

public class RegisterController {

    private UserDAO userDAO;

    public RegisterController() {
        userDAO = new UserDAO();
    }

    public boolean register(String username, String email, String password, String confirmPassword) {

        if (username == null || username.isEmpty()
                || email == null || email.isEmpty()
                || password == null || password.isEmpty()
                || confirmPassword == null || confirmPassword.isEmpty()) {
            MessageUtil.showError("Semua field wajib diisi!");
            return false;
        }

        if (!email.contains("@")) {
            MessageUtil.showError("Format email tidak valid!");
            return false;
        }

        if (password.length() < 6) {
            MessageUtil.showError("Password minimal 6 karakter!");
            return false;
        }

        if (!password.equals(confirmPassword)) {
            MessageUtil.showError("Password dan konfirmasi tidak sama!");
            return false;
        }

        if (userDAO.isUsernameExists(username)) {
            MessageUtil.showError("Username sudah digunakan!");
            return false;
        }

        if (userDAO.isEmailExists(email)) {
            MessageUtil.showError("Email sudah terdaftar!");
            return false;
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        return userDAO.register(user); // ← HANYA RETURN
    }
}
