package test.meal;

import main.backend.common.Result;
import main.backend.food.entity.Food;
import main.backend.meal.entity.Meal;
import main.backend.user.entity.User;
import org.junit.Test;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotEquals;

public class MealGetByPeriodTest extends MealBaseTest {
    @Test
    public void mealGetByPeriodCase1(){
        //Testing user getMeals by period
        User user = new User(1, "Jane Doe", "jd123", "1234567", "female", null, 0, 0);
        Result res = mealController.getByPeriod(user, Date.valueOf("2023-10-25"), Date.valueOf("2023-10-30"));
        assertEquals("200", res.getCode());
        if (res.getCode().equals("200")) {
            for (Meal meal : (List<Meal>) res.getData()) {
                assertNotNull("not null", meal.getFoodMap());
                for (Map.Entry<Food, Float> entry : meal.getFoodMap().entrySet()) {
                    assertNotNull("not null", entry.getKey());
                    assertNotNull("not null", entry.getValue());
                }
            }
        }

    }

    @Test
    public void mealGetByPeriodCase2(){
        //Testing user getMeals by period with no startDate
        User user = new User(1, "Jane Doe", "jd123", "1234567", "female", null, 0, 0);
        Result res = mealController.getByPeriod(user, null, Date.valueOf("2023-10-30"));
        assertNotEquals("200", res.getCode());
    }

    @Test
    public void mealGetByPeriodCase3(){
        //Testing user getMeals by period with no endDate
        User user = new User(1, "Jane Doe", "jd123", "1234567", "female", null, 0, 0);
        Result res = mealController.getByPeriod(user, Date.valueOf("2023-10-25"), null);
        assertNotEquals("200", res.getCode());

    }

    @Test
    public void mealGetByPeriodCase4(){
        //Testing user getMeals by period with no startDate and endDate
        User user = new User(1, "Jane Doe", "jd123", "1234567", "female", null, 0, 0);
        Result res = mealController.getByPeriod(user, null, null);
        assertNotEquals("200", res.getCode());
    }

    @Test
    public void mealGetByPeriodCase5(){
        //Testing user getMeals by period with startDate after endDate
        User user = new User(1, "Jane Doe", "jd123", "1234567", "female", null, 0, 0);
        Result res = mealController.getByPeriod(user, Date.valueOf("2023-10-30"), Date.valueOf("2023-10-25"));
        assertNotEquals("200", res.getCode());
    }

    @Test
    public void mealGetByPeriodCase6(){
        //Testing user getMeals by period with endtime after currentTime
        User user = new User(1, "Jane Doe", "jd123", "1234567", "female", null, 0, 0);
        Result res = mealController.getByPeriod(user, Date.valueOf("2023-10-25"), Date.valueOf("2077-10-30"));
        assertNotEquals("200", res.getCode());

    }
}
