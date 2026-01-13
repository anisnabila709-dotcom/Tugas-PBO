package minidiary.controller;

import minidiary.dao.UserDAO;
import minidiary.model.User;
import minidiary.util.MessageUtil;
import minidiary.util.Validator;

public class RegisterController {

    private UserDAO userDAO;

    public RegisterController() {
        userDAO = new UserDAO();
    }

    public void register(String username, String email, String password, String confirmPassword) {

        if (!Validator.notEmpty(username, email, password, confirmPassword)) {
            MessageUtil.showError("Semua field wajib diisi!");
            return;
        }

        if (!Validator.isValidEmail(email)) {
            MessageUtil.showError("Format email tidak valid!");
            return;
        }

        if (password.length() < 6) {
            MessageUtil.showError("Password minimal 6 karakter!");
            return;
        }

        if (!password.equals(confirmPassword)) {
            MessageUtil.showError("Password dan konfirmasi tidak sama!");
            return;
        }

        if (userDAO.isUsernameExists(username)) {
            MessageUtil.showError("Username sudah digunakan!");
            return;
        }

        if (userDAO.isEmailExists(email)) {
            MessageUtil.showError("Email sudah terdaftar!");
            return;
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        boolean success = userDAO.register(user);

        if (success) {
            MessageUtil.showSuccess("Registrasi berhasil! Silakan login.");
        } else {
            MessageUtil.showError("Registrasi gagal.");
        }
    }
}
