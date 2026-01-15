package minidiary.util;

import minidiary.model.User;

public class Session {

    // user yang sedang login
    private static User currentUser;

    // SET user
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    // GET objek user penuh
    public static User getCurrentUser() {
        return currentUser;
    }

    // GET user id
    public static int getUserId() {
        return currentUser != null ? currentUser.getId() : -1;
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