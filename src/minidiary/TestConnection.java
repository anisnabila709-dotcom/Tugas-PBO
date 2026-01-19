package minidiary;

import minidiary.config.Database;
import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        try (Connection conn = Database.getConnection()) {
            System.out.println("KONEKSI BERHASIL: " + conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

