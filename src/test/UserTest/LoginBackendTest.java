package test.UserTest;

import main.backend.common.Result;
import main.backend.user.IUserController;
import main.backend.user.entity.User;
import main.backend.user.impl.UserController;

public class LoginBackendTest {
    public static void main(String[] args) {
        IUserController iuser = new UserController();
        String username = "js288c";
        Result res0 = iuser.getUser(username);
        String code = res0.getCode();
        String message = res0.getMessage();

        if (code.equals("200")) {
            User user = (User) res0.getData();
            System.out.println(user.getName());
            System.out.println(user.getUsername());
            System.out.println(user.getSex());
            System.out.println(user.getDateOfBirth());
        } else {
            System.out.println(message);
        }

    }
}
