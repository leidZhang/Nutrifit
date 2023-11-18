package test.user;

import main.backend.common.Result;
import main.backend.user.IUserController;
import main.backend.user.entity.User;
import main.backend.user.impl.UserController;
import org.junit.Test;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static junit.framework.TestCase.assertNotSame;
import static junit.framework.TestCase.assertEquals;

public class UserQueryTest { // Need more test case
    protected IUserController userController = new UserController();

    @Test
    public void TestRegisterCase1() {
        // Testing new user registration
        String dateOfBirthString = "2001-11-16";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user1 = new User("red", "red", "password", "male", sqlDate, 180, 60);

        Result res1 = userController.save(user1);
        assertEquals("200", res1.getCode());
    }

    @Test
    public void TestRegisterCase2() {
        //Testing user registration twice
        String dateOfBirthString = "2001-11-16";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User("name", "username", "password", "male", sqlDate, 180, 60);
        Result res1 = userController.save(user);
        assertEquals("200", res1.getCode());
        System.out.println(res1.getMessage());
        Result res2 = userController.save(user);
        assertNotSame("200", res2.getCode());
        System.out.println(res2.getMessage());
    }

    @Test
    public void TestRegisterCase3() {
        //Test name input is empty for registration
        String dateOfBirthString = "2002-11-16";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User("",
                "username",
                "password",
                "male",
                sqlDate,
                180,
                60);
        Result res = userController.save(user);
        assertNotSame("200", res.getCode());
        System.out.println(res.getMessage());
    }

    @Test
    public void TestRegisterCase4() {
        //Test username input is empty for registration
        String dateOfBirthString = "2002-11-16";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User("name",
                "",
                "password",
                "male",
                sqlDate,
                180,
                60);
        Result res = userController.save(user);
        assertNotSame("200", res.getCode());
        System.out.println(res.getMessage());
    }

    @Test
    public void TestRegisterCase5() {
        //Test password input is empty for registration
        String dateOfBirthString = "2002-11-16";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User("name",
                "username",
                "",
                "male",
                sqlDate,
                180,
                60);
        Result res = userController.save(user);
        assertNotSame("200", res.getCode());
        System.out.println(res.getMessage());
    }

    @Test
    public void TestRegisterCase6() {
        //Test sex input is empty for registration
        String dateOfBirthString = "2002-11-16";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User("name",
                "username",
                "password",
                "xxxx",
                sqlDate,
                180,
                60);
        Result res = userController.save(user);
        assertNotSame("200", res.getCode());
        System.out.println(res.getMessage());
    }

    @Test
    public void TestRegisterCase7() {
        //Test height input is empty for registration
        String dateOfBirthString = "2002-11-16";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User("name",
                "username",
                "password",
                "male",
                sqlDate,
                -180,
                60);
        Result res = userController.save(user);
        assertNotSame("200", res.getCode());
        System.out.println(res.getMessage());
    }

    @Test
    public void TestRegisterCase8() {
        //Test weight input is empty for registration
        String dateOfBirthString = "2002-11-16";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User("name",
                "username",
                "password",
                "male",
                sqlDate,
                180,
                -60);
        Result res = userController.save(user);
        assertNotSame("200", res.getCode());
        System.out.println(res.getMessage());
    }

    @Test
    public void TestLoginCase1() {
        //Test normal login
        Result res = userController.login("blue","password");
        assertEquals("200", res.getCode());
    }

    @Test
    public void TestLoginCase2() {
        //Test wrong username
        Result res = userController.login("black","password");
        assertNotSame("200", res.getCode());
        System.out.println(res.getMessage());
    }

    @Test
    public void TestLoginCase3() {
        //Test wrong password
        Result res = userController.login("blue","123456789");
        assertNotSame("200", res.getCode());
        System.out.println(res.getMessage());
    }

    @Test
    public void TestGetUserCase1() {
        //Test right GetUser
        Result res = userController.getUser("blue");
        assertEquals("200", res.getCode());
        System.out.println(res.getData());
    }

    @Test
    public void TestGetUserCase2() {
        //Test get non-existence user
        Result res = userController.getUser("black");
        assertNotSame("200", res.getCode());
        System.out.println(res.getMessage());
    }

    @Test
    public void TestUpdateUserCase1() {
        //Testing the normal update of user information
        //id is needed!!
        String dateOfBirthString = "2002-11-16";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User(16,"black",
                "black",
                "password",
                "male",
                sqlDate,
                180,
                60);
        Result res = userController.updateUser(user);
        assertEquals("200", res.getCode());
        System.out.println(res.getData());
    }

    @Test
    public void TestUpdateUserCase2() {
        //Testing illegal name
        //id is needed!!
        String dateOfBirthString = "2002-11-16";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User(16,"",
                "black",
                "password",
                "male",
                sqlDate,
                180,
                60);
        Result res = userController.updateUser(user);
        assertNotSame("200", res.getCode());
        System.out.println(res.getMessage());
    }

    @Test
    public void TestUpdateUserCase3() {
        //Testing illegal username
        //id is needed!!
        String dateOfBirthString = "2002-11-16";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User(16,"black",
                "",
                "password",
                "male",
                sqlDate,
                180,
                60);
        Result res = userController.updateUser(user);
        assertNotSame("200", res.getCode());
        System.out.println(res.getMessage());
    }

    @Test
    public void TestUpdateUserCase4() {
        //Testing illegal sex
        //id is needed!!
        String dateOfBirthString = "2002-11-16";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User(16,"black",
                "black",
                "password",
                "xxx",
                sqlDate,
                180,
                60);
        Result res = userController.updateUser(user);
        assertNotSame("200", res.getCode());
        System.out.println(res.getMessage());
    }

    @Test
    public void TestUpdateUserCase5() {
        //Testing illegal height
        //id is needed!!
        String dateOfBirthString = "2002-11-16";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User(16,"black",
                "black",
                "password",
                "xxx",
                sqlDate,
                -180,
                60);
        Result res = userController.updateUser(user);
        assertNotSame("200", res.getCode());
        System.out.println(res.getMessage());
    }

    @Test
    public void TestUpdateUserCase6() {
        //Testing illegal height
        //id is needed!!
        String dateOfBirthString = "2002-11-16";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User(16,"black",
                "black",
                "password",
                "xxx",
                sqlDate,
                180,
                -60);
        Result res = userController.updateUser(user);
        assertNotSame("200", res.getCode());
        System.out.println(res.getMessage());
    }

    private Date TransformDate(String dateOfBirthString) {
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
