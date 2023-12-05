package test.meal;

import main.backend.common.Result;
import main.backend.food.entity.Food;
import main.backend.meal.entity.Meal;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MealSubmitTest extends MealBaseTest {
    private final String TYPE_BREAKFAST = "Breakfast";
    private final String TYPE_LUNCH = "Lunch";
    private final String TYPE_DINNER = "Dinner";
    private final String TYPE_SNACK = "Snack";
    private final Date VALID_DATE = Date.valueOf("2023-11-15");
    private final Date BOUNDARY_DATE = Date.valueOf(LocalDate.now());
    private final float BOUNDARY_QUANTITY = 0.01f;
    private Map<Food, Float> map = new HashMap<>();

    @Test
    public void MealSubmitTestCase1() {
        // General submit case
        Food food = foodList.get(0);
        float quantity = 100;
        map.put(food, quantity);

        Meal meal = new Meal(1, VALID_DATE, TYPE_BREAKFAST);
        meal.setFoodMap(map);
        Result res = mealController.save(meal, user);
        assertEquals(SUCCESS_CODE, res.getCode());
        map.clear(); // clear map
    }

    @Test
    public void MealSubmitTestCase2() {
        // Test boundary food quantity a
        Food food = foodList.get(0);
        float quantity = BOUNDARY_QUANTITY;
        map.put(food, quantity);

        Meal meal = new Meal(1, BOUNDARY_DATE, TYPE_LUNCH);
        meal.setFoodMap(map);
        Result res = mealController.save(meal, user);
        assertEquals(SUCCESS_CODE, res.getCode());
        map.clear(); // clear map
    }

    @Test
    public void MealSubmitTestCase3() {
        // Test date out of boundary
        Food food = foodList.get(0);
        float quantity = 100;
        map.put(food, quantity);

        Meal meal = new Meal(1, Date.valueOf("2077-10-13"), TYPE_LUNCH);
        meal.setFoodMap(map);
        Result res = mealController.save(meal, user);
        assertNotEquals(SUCCESS_CODE, res.getCode());
        map.clear(); // clear map
    }

    @Test
    public void MealSubmitTestCase4() {
        // Test invalid date
        Food food = foodList.get(0);
        float quantity = 100;
        map.put(food, quantity);

        Meal meal = new Meal(1, null, TYPE_BREAKFAST);
        meal.setFoodMap(map);
        Result res = mealController.save(meal, user);
        assertNotEquals(SUCCESS_CODE, res.getCode());
        map.clear(); // clear map
    }

    @Test
    public void MealSubmitTestCase5() {
        // Test invalid type
        Food food = foodList.get(0);
        float quantity = 100;
        map.put(food, quantity);

        Meal meal = new Meal(1, VALID_DATE, "DSFFDS");
        meal.setFoodMap(map);
        Result res = mealController.save(meal, user);
        assertNotEquals(SUCCESS_CODE, res.getCode());
        map.clear(); // clear map
    }

    @Test
    public void MealSubmitTestCase6() {
        // Test invalid food item
        Food food = null;
        float quantity = 100;
        map.put(food, quantity);

        Meal meal = new Meal(1, VALID_DATE, TYPE_DINNER);
        meal.setFoodMap(map);
        Result res = mealController.save(meal, user);
        assertNotEquals(SUCCESS_CODE, res.getCode());
        map.clear(); // clear map
    }

//    @Test
//    public void MealSubmitTestCase7() {
//        // Test invalid food input
//        try {
//            Object invalidFood = "SDFDSF";
//            Food food = (Food) invalidFood;
//            float quantity = 100;
//            map.put(food, quantity);
//
//            Meal meal = new Meal(1, VALID_DATE, TYPE_DINNER);
//            meal.setFoodMap(map);
//            // this line will not reach
//            Result res = mealController.save(meal, user);
//            assertNotEquals(SUCCESS_CODE, res.getCode());
//        } catch (Exception e) {
//            assertEquals(e.getClass(), new ClassCastException().getClass());
//        }
//
//        map.clear(); // clear map
//    }

    @Test
    public void MealSubmitTestCase8() {
        // Test invalid food quantity
        Food food = foodList.get(1);
        float quantity = -8;
        map.put(food, quantity);

        Meal meal = new Meal(1, VALID_DATE, TYPE_DINNER);
        meal.setFoodMap(map);
        Result res = mealController.save(meal, user);
        assertNotEquals(SUCCESS_CODE, res.getCode());
        map.clear(); // clear map
    }

//    @Test
//    public void MealSubmitTestCase9() {
//        // Test invalid quantity input
//        String invalid = "(^&(";
//
//        try {
//            Food food = foodList.get(1);
//            float quantity = Float.parseFloat(invalid);
//            map.put(food, quantity);
//
//            Meal meal = new Meal(1, VALID_DATE, TYPE_DINNER);
//            meal.setFoodMap(map);
//            // this line will not reach
//            Result res = mealController.save(meal, user);
//            assertNotEquals(SUCCESS_CODE, res.getCode());
//        } catch (Exception e) {
//            assertEquals(e.getClass(), new NumberFormatException().getClass());
//        }
//
//        map.clear(); // clear map
//    }

    @Test
    public void MealSubmitTestCase10() {
        // Test invalid food map
        Meal meal = new Meal(1, VALID_DATE, TYPE_DINNER);
        meal.setFoodMap(null);
        Result res = mealController.save(meal, user);
        assertNotEquals(SUCCESS_CODE, res.getCode());
        map.clear(); // clear map
    }

    @Test
    public void MealSubmitTestCase11() {
        // Test empty food map
        Food food = foodList.get(1);
        float quantity = -8;
        // map.put(food, quantity);

        Meal meal = new Meal(1, VALID_DATE, TYPE_DINNER);
        meal.setFoodMap(map);
        Result res = mealController.save(meal, user);
        assertNotEquals(SUCCESS_CODE, res.getCode());
        map.clear(); // clear map
    }

    @Test
    public void MealSubmitTestCase12() throws InterruptedException {
        // Test duplicate meals
        Thread.sleep(5000); // must execute after test case 2
        Food food = foodList.get(0);
        float quantity = BOUNDARY_QUANTITY;
        map.put(food, quantity);

        Meal meal = new Meal(1, BOUNDARY_DATE, TYPE_LUNCH);
        meal.setFoodMap(map);
        Result res = mealController.save(meal, user);
        assertNotEquals(SUCCESS_CODE, res.getCode());
        map.clear(); // clear map
    }
}
