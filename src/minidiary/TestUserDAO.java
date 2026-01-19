package minidiary;

import minidiary.dao.UserDAO;
import minidiary.model.User;

public class TestUserDAO {

    public static void main(String[] args) {

        UserDAO userDAO = new UserDAO();

        User user = new User();
        user.setUsername("risma_test");
        user.setEmail("risma@test.com");
        user.setPassword("123456");

        boolean result = userDAO.register(user);

        if (result) {
            System.out.println("REGISTER BERHASIL");
        } else {
            System.out.println("REGISTER GAGAL");
        }
    }
}
