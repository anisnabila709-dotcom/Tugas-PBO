package minidiary.util;

import minidiary.model.User;

public class Session {

    // user yang sedang login
    private static User currentUser;

    // set user saat login berhasil
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    // ambil user yang sedang login
    public static User getCurrentUser() {
        return currentUser;
    }

    // cek apakah sudah login
    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    // logout / hapus session
    public static void clear() {
        currentUser = null;
    }
}
 