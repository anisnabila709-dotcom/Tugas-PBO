package minidiary.util;

public class Validator {

    // Cek apakah string kosong / null
    public static boolean isEmpty(String text) {
        return text == null || text.trim().isEmpty();
    }

    // Validasi minimal panjang karakter (contoh untuk username & password)
    public static boolean minLength(String text, int length) {
        return text != null && text.trim().length() >= length;
    }

    // Validasi format email sederhana
    public static boolean isValidEmail(String email) {
        if (isEmpty(email)) return false;
        // contoh: nama@domain.com
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    // Password minimal 6 karakter (bisa diperketat lagi nanti kalau mau)
    public static boolean isStrongPassword(String password) {
        return minLength(password, 6);
    }

    // Cek apakah dua teks sama persis
    public static boolean match(String a, String b) {
        if (a == null || b == null) return false;
        return a.equals(b);
    }
}
