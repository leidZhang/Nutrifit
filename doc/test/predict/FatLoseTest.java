package test.predict;

import main.backend.common.Result;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FatLoseTest extends PredictBaseTest {
    @Test
    public void FatLoseTestCase1() {
        // Test general case
        Date date = Date.valueOf("2024-05-01");
        Result res = controller.getPredictionByDate(user, date);

        assertEquals(SUCCESS_CODE, res.getCode());
    }

    @Test
    public void FatLoseTestCase2() {
        // Test upper boundary
        Result res = controller.getPredictionByDate(user, Date.valueOf(upperBoundaryDate));

        assertEquals(SUCCESS_CODE, res.getCode());
    }

    @Test
    public void FatLoseTestCase3() {
        // Test lower boundary
        Result res = controller.getPredictionByDate(user, Date.valueOf(lowerBoundaryDate));

        assertEquals(SUCCESS_CODE, res.getCode());
    }

    @Test
    public void FatLoseTestCase4() {
        // Test illegal input
        Result res = controller.getPredictionByDate(user, null);

        assertNotEquals(SUCCESS_CODE, res.getCode());
    }

    @Test
    public void FatLoseTestCase5() {
        // Test out of lower bound
        Result res = controller.getPredictionByDate(user, Date.valueOf("1977-02-11"));

        assertNotEquals(SUCCESS_CODE, res.getCode());
    }

    @Test
    public void FatLoseTestCase6() {
        // Test out of upper bound
        Result res = controller.getPredictionByDate(user, Date.valueOf("2077-02-11"));

        assertNotEquals(SUCCESS_CODE, res.getCode());
    }
}
