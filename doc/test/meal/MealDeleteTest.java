package test.meal;

import main.backend.common.Result;
import main.backend.food.entity.Food;
import main.backend.meal.entity.Meal;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MealDeleteTest extends MealBaseTest {
    private List<Meal> meals;

    @Test
    public void MealDeleteTestCase1() {
        // Test general case
        Result res1 = mealController.getByUser(user);
        meals = (List<Meal>) res1.getData();

        Meal meal = meals.get(0);
        Result res2 = mealController.delete(meal.getId());
        assertEquals(SUCCESS_CODE, res2.getCode());
    }

    @Test
    public void MealDeleteTestCase2() {
        // Test delete newest
        mealController.save(saveMeal(), user); // avoid empty
        Result res1 = mealController.getByUser(user);
        meals = (List<Meal>) res1.getData();

        Meal meal = meals.get(0);
        Result res2 = mealController.delete(meal.getId());
        assertEquals(SUCCESS_CODE, res2.getCode());
    }

    @Test
    public void MealDeleteTestCase3() {
        // Test delete oldest
        mealController.save(saveMeal(), user); // avoid empty
        Result res1 = mealController.getByUser(user);
        meals = (List<Meal>) res1.getData();

        Meal meal = meals.get(meals.size() - 1);
        Result res2 = mealController.delete(meal.getId());
        assertEquals(SUCCESS_CODE, res2.getCode());
    }

    @Test
    public void MealDeleteTestCase4() {
        // Test illegal deletion
        Result res1 = mealController.getByUser(user);
        meals = (List<Meal>) res1.getData();

        Meal meal = meals.get(0);
        Result res2 = mealController.delete(-1);
        assertNotEquals(SUCCESS_CODE, res2.getCode());
    }

    private Meal saveMeal() {
        Food food = foodList.get(0);
        Map<Food, Float> foodMap = new HashMap<>();
        foodMap.put(food, 10.0f);

        Meal meal = new Meal(1, Date.valueOf(LocalDate.now()), "Breakfast");
        meal.setFoodMap(foodMap);
        return meal;
    }
}
