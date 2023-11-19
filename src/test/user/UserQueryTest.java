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
        //Testing new user registration
        String dateOfBirthString = "1987-09-15";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User("Bob Smith",
                "mok334",
                "zg%$%@#1",
                "male",
                sqlDate,
                1.85,
                65);
        Result res = userController.save(user);
        assertEquals("200", res.getCode());
    }

    @Test
    public void TestRegisterCase2() {
        //Testing new user registration
        String dateOfBirthString = "1987-09-15";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User("Jane Smith",
                "mok335",
                "zg%$%@#1",
                "female",
                sqlDate,
                0.45,
                2);
        Result res1 = userController.save(user);
        assertEquals("200", res1.getCode());
    }

    @Test
    public void TestRegisterCase3() {
        //Testing new user registration
        String dateOfBirthString = "1987-09-15";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User("Bob Smith",
                "mok336",
                "zg%$%@#1",
                "male",
                sqlDate,
                2.8,
                750);
        Result res1 = userController.save(user);
        assertEquals("200", res1.getCode());
    }

    @Test
    public void TestRegisterCase4() {
        //Testing new user registration
        Date today = new Date(System.currentTimeMillis());
        User user = new User("Bob Smith",
                "mok3362",
                "zg%$%@#1",
                "male",
                today,
                2.8,
                750);
        Result res1 = userController.save(user);
        System.out.println(res1.getMessage());
        assertEquals("200", res1.getCode());
    }

    @Test
    public void TestRegisterCase5() {
        //Testing user registration twice
        String dateOfBirthString = "1987-09-15";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User("1234",
                "mok336",
                "zg%$%@#2",
                "male",
                sqlDate,
                2.8,
                750);
        Result res = userController.save(user);
        assertNotSame("200", res.getCode());
        System.out.println(res.getMessage());
    }

    @Test
    public void TestRegisterCase6() {
        //Test invalid name for registration
        String dateOfBirthString = "1987-09-15";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User("1234",
                "mok338",
                "zg%$%@#1",
                "male",
                sqlDate,
                1.85,
                65);
        Result res = userController.save(user);
        assertNotSame("200", res.getCode());
        System.out.println(res.getMessage());
    }

    @Test
    public void TestRegisterCase7() {
        //Test invalid username for registration
        String dateOfBirthString = "1987-09-15";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User("Bob Smith",
                "###mok338",
                "zg%$%@#1",
                "male",
                sqlDate,
                1.85,
                65);
        Result res = userController.save(user);
        assertNotSame("200", res.getCode());
        System.out.println(res.getMessage());
    }

    @Test
    public void TestRegisterCase8() {
        //Test invalid password for registration
        String dateOfBirthString = "1987-09-15";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User("Bob Smith",
                "mok339",
                "12345",
                "male",
                sqlDate,
                1.85,
                65);
        Result res = userController.save(user);
        assertNotSame("200", res.getCode());
        System.out.println(res.getMessage());
    }

    @Test
    public void TestRegisterCase9() {
        //Test invalid sex for registration
        String dateOfBirthString = "1987-09-15";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User("Bob Smith",
                "mok340",
                "zg%$%@#1",
                "helo",
                sqlDate,
                1.85,
                65);
        Result res = userController.save(user);
        assertNotSame("200", res.getCode());
        System.out.println(res.getMessage());
    }

    @Test
    public void TestRegisterCase10() {
        //Test date out of boundry for registration
        String dateOfBirthString = "2077-10-29";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User("Bob Smith",
                "mok342",
                "zg%$%@#1",
                "male",
                sqlDate,
                1.85,
                65);
        Result res = userController.save(user);
        assertNotSame("200", res.getCode());
        System.out.println(res.getMessage());
    }


    @Test
    public void TestRegisterCase11() {
        //Test invalid height for registration
        String dateOfBirthString = "1987-09-18";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User("Bob Smith",
                "mok343",
                "zg%$%@#1",
                "male",
                sqlDate,
                -1.85,
                65);
        Result res = userController.save(user);
        assertNotSame("200", res.getCode());
        System.out.println(res.getMessage());
    }

    @Test
    public void TestRegisterCase12() {
        //Test invalid weight for registration
        String dateOfBirthString = "1987-09-19";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User("Bob Smith",
                "mok344",
                "zg%$%@#1",
                "male",
                sqlDate,
                1.85,
                -8);
        Result res = userController.save(user);
        assertNotSame("200", res.getCode());
        System.out.println(res.getMessage());
    }

    @Test
    public void TestLoginCase1() {
        //Test normal login
        Result res = userController.login("mok334","zg%$%@#1");
        assertEquals("200", res.getCode());
        System.out.println("login success");
    }

    @Test
    public void TestLoginCase2() {
        //Test wrong username
        Result res = userController.login("Bob Smith","zg%$%@#1");
        assertNotSame("200", res.getCode());
        System.out.println(res.getMessage());
    }

    @Test
    public void TestLoginCase3() {
        //Test wrong password
        Result res = userController.login("Bob Smith","12345");
        assertNotSame("200", res.getCode());
        System.out.println(res.getMessage());
    }

    @Test
    public void TestGetUserCase1() {
        //Test right GetUser
        Result res = userController.getUser("mok334");
        assertEquals("200", res.getCode());
        System.out.println(res.getData());
    }

    @Test
    public void TestGetUserCase2() {
        //Test get non-existence user
        Result res = userController.getUser("mok");
        assertNotSame("200", res.getCode());
        System.out.println(res.getMessage());
    }

    @Test
    public void TestUpdateUserCase1() {
        //Testing the normal update of user information
        Result result=userController.getUser("mok334");
        User old_user= (User) result.getData();
        String dateOfBirthString = "2002-11-16";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User(old_user.getId(),"black",
                "black",
                "password",
                "male",
                sqlDate,
                180,
                60);
        Result res = userController.updateUser(user);
        assertEquals("200", res.getCode());
        System.out.println("update success");
    }

    @Test
    public void TestUpdateUserCase2() {
        //Testing illegal name
        Result result=userController.getUser("mok335");
        User old_user= (User) result.getData();
        String dateOfBirthString = "2002-11-16";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User(old_user.getId(),"",
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
        Result result=userController.getUser("mok335");
        User old_user= (User) result.getData();
        String dateOfBirthString = "2002-11-16";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User(old_user.getId(),"black",
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
        Result result=userController.getUser("mok335");
        User old_user= (User) result.getData();
        String dateOfBirthString = "2002-11-16";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User(old_user.getId(),"black",
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
        Result result=userController.getUser("mok335");
        User old_user= (User) result.getData();
        String dateOfBirthString = "2002-11-16";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User(old_user.getId(),"black",
                "black",
                "password",
                "male",
                sqlDate,
                -180,
                60);
        Result res = userController.updateUser(user);
        assertNotSame("200", res.getCode());
        System.out.println(res.getMessage());
    }

    @Test
    public void TestUpdateUserCase6() {
        //Testing illegal weight
        Result result=userController.getUser("mok335");
        User old_user= (User) result.getData();
        String dateOfBirthString = "2002-11-16";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User(old_user.getId(), "black",
                "black",
                "password",
                "male",
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
