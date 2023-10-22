package test;

import main.backend.user.IUserController;
import main.backend.common.Result;
import main.backend.user.entity.User;
import main.backend.user.impl.UserController;


public class UserTest {
    public static void main(String[] args) {
        IUserController iuser = new UserController();
        String username = "js288ca";
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
//        User user = new User("John Smith", "js288c", "male", date, 180, 70, 0);
//        Result res = iuser.save(user);
//        System.out.println(res.getCode());
    }
}
