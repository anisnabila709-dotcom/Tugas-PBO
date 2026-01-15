package minidiary.controller;

import minidiary.dao.UserDAO;
import minidiary.model.User;
import minidiary.util.MessageUtil;

public class RegisterController {

    private UserDAO userDAO;

    public RegisterController() {
        userDAO = new UserDAO();
    }

    public void register(String username, String email, String password, String confirmPassword) {

        // ===== VALIDASI KOSONG =====
        if (username == null || username.isEmpty()
                || email == null || email.isEmpty()
                || password == null || password.isEmpty()
                || confirmPassword == null || confirmPassword.isEmpty()) {
            MessageUtil.showError("Semua field wajib diisi!");
            return;
        }

        // ===== VALIDASI EMAIL =====
        if (!email.contains("@")) {
            MessageUtil.showError("Format email tidak valid!");
            return;
        }

        // ===== VALIDASI PASSWORD =====
        if (password.length() < 6) {
            MessageUtil.showError("Password minimal 6 karakter!");
            return;
        }

        if (!password.equals(confirmPassword)) {
            MessageUtil.showError("Password dan konfirmasi tidak sama!");
            return;
        }

        // ===== CEK DUPLIKASI =====
        if (userDAO.isUsernameExists(username)) {
            MessageUtil.showError("Username sudah digunakan!");
            return;
        }

        if (userDAO.isEmailExists(email)) {
            MessageUtil.showError("Email sudah terdaftar!");
            return;
        }

        // ===== SIMPAN USER =====
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        boolean success = userDAO.register(user);

        if (success) {
            MessageUtil.showError("Registrasi berhasil! Silakan login.");
        } else {
            MessageUtil.showError("Registrasi gagal.");
        }
    }
}
