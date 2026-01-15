package minidiary.util;

import minidiary.model.User;

/**
 * Session class
 * Menyimpan user yang sedang login
 * Menggunakan static agar bisa diakses dari mana saja
 */
public final class Session {

    // user yang sedang login
    private static User currentUser;

    // constructor dikunci agar tidak bisa dibuat object
    private Session() {
    }

    // set user saat login berhasil
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    // ambil user yang sedang login
    public static User getCurrentUser() {
        return currentUser;
    }

    // cek apakah user sudah login
    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    // logout / hapus session
    public static void clear() {
        currentUser = null;
    }
}
