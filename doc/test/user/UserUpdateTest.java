package test.user;

import main.backend.common.Result;
import main.backend.user.entity.User;
import org.junit.Test;

import java.sql.Date;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotSame;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;

public class UserUpdateTest extends UserBaseTest {
    @Test
    public void testUpdateUserCase1() {
        //Testing the normal update of user information
        Result result=userController.getUser("mok334");
        User old_user= (User) result.getData();
        String dateOfBirthString = "2002-11-16";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User(old_user.getId(),"Bob Smith",
                "mok334",
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
    public void testUpdateUserCase2() {
        //Testing the sex
        Result result=userController.getUser("mok334");
        User old_user= (User) result.getData();
        String dateOfBirthString = "2002-11-16";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User(old_user.getId(),"Bob Smith",
                "mok334",
                "password",
                "female",
                sqlDate,
                180,
                60);
        Result res = userController.updateUser(user);
        assertEquals("200", res.getCode());
        System.out.println("update success");
    }

    @Test
    public void testUpdateUserCase3() {
        // Testing the boundary values
        Result result=userController.getUser("mok334");
        User old_user= (User) result.getData();
        String dateOfBirthString = "2002-11-16";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User(old_user.getId(),"Bob Smith",
                "mok334",
                "password",
                "female",
                sqlDate,
                0.45,
                2);
        Result res = userController.updateUser(user);
        assertEquals("200", res.getCode());
        System.out.println("update success");
    }

    @Test
    public void testUpdateUserCase4() {
        // Testing the boundary values
        Result result=userController.getUser("mok334");
        User old_user= (User) result.getData();
        String dateOfBirthString = "2002-11-16";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User(old_user.getId(),"Bob Smith",
                "mok334",
                "password",
                "female",
                sqlDate,
                2.8,
                750);
        Result res = userController.updateUser(user);
        assertEquals("200", res.getCode());
        System.out.println("update success");
    }

    @Test
    public void testUpdateUserCase5() {
        //Testing illegal name
        Result result=userController.getUser("mok335");
        User old_user= (User) result.getData();
        String dateOfBirthString = "2002-11-16";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User(old_user.getId(),"###Bob Smith",
                "mok335",
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
    public void testUpdateUserCase6() {
        //Testing illegal username
        Result result=userController.getUser("mok335");
        User old_user= (User) result.getData();
        String dateOfBirthString = "2002-11-16";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User(old_user.getId(),"Bob Smith",
                "#$%mok341",
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
    public void testUpdateUserCase7() {
        //Testing illegal sex
        Result result=userController.getUser("mok335");
        User old_user= (User) result.getData();
        String dateOfBirthString = "2002-11-16";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User(old_user.getId(),"Bob Smith",
                "mok335",
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
    public void testUpdateUserCase8() {
        //Testing illegal height
        Result result=userController.getUser("mok335");
        User old_user= (User) result.getData();
        String dateOfBirthString = "2002-11-16";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User(old_user.getId(),"Bob Smith",
                "mok335",
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
    public void testUpdateUserCase9() {
        //Testing illegal weight
        Result result=userController.getUser("mok335");
        User old_user= (User) result.getData();
        String dateOfBirthString = "2002-11-16";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User(old_user.getId(), "Bob Smith",
                "mok335",
                "password",
                "male",
                sqlDate,
                180,
                -60);
        Result res = userController.updateUser(user);
        assertNotSame("200", res.getCode());
        System.out.println(res.getMessage());
    }

    @Test
    public void testUpdateUserCase10() {
        //Testing illegal date
        Result result=userController.getUser("mok335");
        User old_user= (User) result.getData();
        String dateOfBirthString = "2072-11-16";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User(old_user.getId(), "Bob Smith",
                "mok335",
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
    public void testUpdateUserCase11() {
        //Testing boundary date
        Result result=userController.getUser("mok335");
        User old_user= (User) result.getData();
        Date today = new Date(System.currentTimeMillis());
        User user = new User(old_user.getId(), "Bob Smith",
                "mok335",
                "password",
                "male",
                today,
                180,
                60);
        Result res = userController.updateUser(user);
        assertSame("200", res.getCode());
        System.out.println(res.getMessage());
    }

//    @Test
//    public void testUpdateUserCase12() {
//        //Testing illegal height
//        String dateOfBirthString = "1987-09-15";
//        Date sqlDate = TransformDate(dateOfBirthString);
//
//        // Use a string that cannot be parsed as a float
//        String invalidHeightString = "invalid_height_value";
//
//        try {
//            float invalidHeight = Float.parseFloat(invalidHeightString);
//            Result result=userController.getUser("mok335");
//            User old_user= (User) result.getData();
//            User user = new User(
//                    old_user.getId(),
//                    "Bob Smith",
//                    "mok3344",
//                    "zg%$%@#1",
//                    "male",
//                    sqlDate,
//                    invalidHeight,
//                    65);
//
//            // This line should not be reached, fail the test if it does
//            Result res = userController.updateUser(user);
//            assertNotEquals("200", res.getCode());
//        } catch (NumberFormatException e) {
//            // Catch the expected exception
//            // Perform any additional assertions if needed
//            assertEquals("Expected exception message", "For input string: \"invalid_height_value\"", e.getMessage());
//        }
//    }

//    @Test
//    public void testUpdateUserCase13() {
//        //Testing illegal weight
//        String dateOfBirthString = "1987-09-15";
//        Date sqlDate = TransformDate(dateOfBirthString);
//
//        // Use a string that cannot be parsed as a float
//        String invalidWeightString = "invalid_height_value";
//
//        try {
//            float invalidWeight = Float.parseFloat(invalidWeightString);
//            Result result=userController.getUser("mok335");
//            User old_user= (User) result.getData();
//
//            User user = new User(
//                    old_user.getId(),
//                    "Bob Smith",
//                    "mok3344",
//                    "zg%$%@#1",
//                    "male",
//                    sqlDate,
//                    180,
//                    invalidWeight);
//
//            // This line should not be reached, fail the test if it does
//            Result res = userController.updateUser(user);
//            assertNotEquals("200", res.getCode());
//        } catch (NumberFormatException e) {
//            // Catch the expected exception
//            // Perform any additional assertions if needed
//            assertEquals("Expected exception message", "For input string: \"invalid_height_value\"", e.getMessage());
//        }
//    }

    @Test
    public void testUpdateUserCase14() {
        // invalid user date input
        Result result=userController.getUser("mok335");
        User old_user= (User) result.getData();
        User user = new User(
                old_user.getId(),
                "Bob Smith",
                "mok3344",
                "zg%$%@#1",
                "male",
                null,
                180,
                60);

        Result res = userController.updateUser(user);
        assertNotEquals("200", res.getCode());
    }
}
