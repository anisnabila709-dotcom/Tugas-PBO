package minidiary.util;

import minidiary.model.User;

public class Session {

    private static User currentUser;

    // SET user login
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    // GET objek user
    public static User getCurrentUser() {
        return currentUser;
    }

    // GET user id (❗ FIX DI SINI)
    public static int getUserId() {
        return currentUser != null ? currentUser.getId() : 0;
    }

    // GET username
    public static String getUsername() {
        return currentUser != null ? currentUser.getUsername() : null;
    }

    // CEK login
    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    // LOGOUT
    public static void clear() {
        currentUser = null;
    }
}
