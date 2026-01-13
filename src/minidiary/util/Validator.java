package minidiary.util;

public class Validator {

    public static boolean notEmpty(String... values) {
        for (String value : values) {
            if (value == null || value.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }
}
