package test.user;

import main.backend.common.Result;
import main.backend.user.entity.User;
import org.junit.Test;

import java.sql.Date;

import static junit.framework.TestCase.assertNotSame;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UserRegisterTest extends UserBaseTest { // Need more test case
    @Test
    public void testRegisterCase1() {
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
    public void testRegisterCase2() {
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
    public void testRegisterCase3() {
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
    public void testRegisterCase4() {
        //Testing new user registration
        Date today = new Date(System.currentTimeMillis());
        User user = new User("Bob Smith",
                "mok336",
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
    public void testRegisterCase5() throws InterruptedException {
        //Testing user registration twice
        Thread.sleep(5000); // must execute after test case 2
        String dateOfBirthString = "1987-09-15";
        Date sqlDate = TransformDate(dateOfBirthString);
        User user = new User("Bob Smith",
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
    public void testRegisterCase6() {
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
    public void testRegisterCase7() {
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
    public void testRegisterCase8() {
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
    public void testRegisterCase9() {
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
    public void testRegisterCase10() {
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
    public void testRegisterCase11() {
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
    public void testRegisterCase12() {
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

//    @Test
//    public void testRegisterCase13() {
//        //Testing illegal height
//        String dateOfBirthString = "1987-09-15";
//        Date sqlDate = TransformDate(dateOfBirthString);
//
//        // Use a string that cannot be parsed as a float
//        String invalidHeightString = "invalid_height_value";
//
//        try {
//            float invalidHeight = Float.parseFloat(invalidHeightString);
//
//            User user = new User("Bob Smith",
//                    "mok3344",
//                    "zg%$%@#1",
//                    "male",
//                    sqlDate,
//                    invalidHeight,
//                    65);
//
//            // This line should not be reached, fail the test if it does
//            Result res = userController.save(user);
//            assertNotEquals("200", res.getCode());
//        } catch (NumberFormatException e) {
//            // Catch the expected exception
//            // Perform any additional assertions if needed
//            assertEquals("Expected exception message", "For input string: \"invalid_height_value\"", e.getMessage());
//        }
//    }

//    @Test
//    public void testRegisterCase14() {
//        //Testing illegal height
//        String dateOfBirthString = "1987-09-15";
//        Date sqlDate = TransformDate(dateOfBirthString);
//
//        // Use a string that cannot be parsed as a float
//        String invalidWeightString = "invalid_height_value";
//
//        try {
//            float invalidWeight = Float.parseFloat(invalidWeightString);
//
//            User user = new User("Bob Smith",
//                    "mok3344",
//                    "zg%$%@#1",
//                    "male",
//                    sqlDate,
//                    180,
//                    invalidWeight);
//
//            // This line should not be reached, fail the test if it does
//            Result res = userController.save(user);
//            assertNotEquals("200", res.getCode());
//        } catch (NumberFormatException e) {
//            // Catch the expected exception
//            // Perform any additional assertions if needed
//            assertEquals("Expected exception message", "For input string: \"invalid_height_value\"", e.getMessage());
//        }
//    }

    @Test
    public void testRegisterCase15() {
        // Use a string that cannot be parsed as a float
        User user = new User("Bob Smith",
                    "mok3344",
                    "zg%$%@#1",
                    "male",
                    null,
                    180,
                    60);

        Result res = userController.save(user);
        assertNotEquals("200", res.getCode());
    }
}
