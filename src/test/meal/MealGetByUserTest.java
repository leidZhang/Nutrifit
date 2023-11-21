package test.meal;

import main.backend.common.Result;
import main.backend.food.entity.Food;
import main.backend.meal.entity.Meal;
import main.backend.user.entity.User;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class MealGetByUserTest extends MealBaseTest {
    @Test
    public void mealGetByUserCase1(){
        //Testing user get all Meals
        Result res = mealController.getByUser(user);
        assertEquals("200", res.getCode());

    }
    @Test
    public void mealGetByUserCase2(){
        //Testing fake user getALl Meals
        User user = new User(1111, "faker", "faker", "0000", "female", null, 0, 0);
        Result res = mealController.getByUser(user);
        assertEquals("200", res.getCode());

    }

    @Test
    public void mealGetByUserCase3() {
        //Testing user get all Meals which foodMap is not null
        User user = new User(1, "Jane Doe", "jd123", "1234567", "female", null, 0, 0);
        Result res1 = mealController.getByUser(user);
        assertEquals("200", res1.getCode());
        if (res1.getCode().equals("200")) {
            for (Meal meal : (List<Meal>) res1.getData()) {
                for (Map.Entry<Food, Float> entry : meal.getFoodMap().entrySet()) {
                    assertNotNull("not null",entry.getKey());
                    assertNotNull("not null",entry.getValue());
                }
            }
        }
    }
}
