package test.meal;

import main.backend.common.Result;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FoodGetTest extends MealBaseTest {
    @Test
    public void foodListCase1() {
        Result res = foodController.getList();
        assertEquals("200", res.getCode());
    }
}
