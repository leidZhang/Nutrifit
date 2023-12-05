package test.user;

import main.backend.common.Result;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotSame;

public class UserLoginTest extends UserBaseTest {
    @Test
    public void testLoginCase1() {
        // Test normal login
        Result res = userController.login("mok334","zg%$%@#1");
        assertEquals("200", res.getCode());
        System.out.println("login success");
    }

    @Test
    public void testLoginCase2() {
        // Test wrong username
        Result res = userController.login("BobSmith","zg%$%@#1");
        assertNotSame("200", res.getCode());
        System.out.println(res.getMessage());
    }

    @Test
    public void testLoginCase3() {
        // Test wrong password
        Result res = userController.login("mok334","12345678");
        assertNotSame("200", res.getCode());
        System.out.println(res.getMessage());
    }

    @Test
    public void testLoginCase4() {
        // Test illegal username
        Result res = userController.login("^&*mok334","12345678");
        assertNotSame("200", res.getCode());
        System.out.println(res.getMessage());
    }

    @Test
    public void testLoginCase5() {
        // Test illegal password
        Result res = userController.login("mok334","12345");
        assertNotSame("200", res.getCode());
        System.out.println(res.getMessage());
    }
}
