package test.UserTest;

import main.backend.common.Result;
import main.backend.user.IUserController;
import main.backend.user.entity.User;
import main.backend.user.impl.UserController;
import main.frontend.session.UserSession;

import java.time.LocalDate;

public class RegistationBackendTest {
    public static void main(String[] args) {
        IUserController userController = new UserController();
        UserSession instance = UserSession.getInstance();
        LocalDate localDate = LocalDate.now();
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
        User testUser=new User("name","username","male", sqlDate,180,60,18);
        Result res = userController.save(testUser);
        String code = res.getCode();
        String message = res.getMessage();
        if (code.equals("200")) {
            System.out.println( testUser.getUsername()+ "register success");
        } else {
            System.out.println(message);
        }

    }
}
