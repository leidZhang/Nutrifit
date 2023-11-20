package test.user;

import main.backend.user.IUserController;
import main.backend.user.impl.UserController;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public abstract class UserTest {
    protected IUserController userController = new UserController();

    protected Date TransformDate(String dateOfBirthString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date sqlDate = null;
        try {
            java.util.Date utilDate = dateFormat.parse(dateOfBirthString);
            sqlDate = new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sqlDate;
    }
}
