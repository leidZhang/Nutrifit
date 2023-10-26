package test;

import main.backend.user.IUserController;
import main.backend.common.Result;
import main.backend.user.entity.User;
import main.backend.user.impl.UserController;

import java.sql.Date;


public class UserTest {
    public static void main(String[] args) {
        IUserController iuser = new UserController();
        String username = "js288c";
        Result res0 = iuser.getUser(username);
        String code = res0.getCode();
        String message = res0.getMessage();

        if (code.equals("200")) {
            User user = (User) res0.getData();
            System.out.println(user.getName());
        } else {
            System.out.println(message);
        }

//        Date date = new Date(System.currentTimeMillis());
//        User user = new User(5, "Hua Li", "lima2", "male", date, 180, 70, 0);
//        Result res = iuser.updateUser(user);
//        System.out.println(res.getMessage());
    }
}
