package test.user;

import main.backend.common.Result;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotSame;

public class UserGetTest extends UserTest {
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
}
