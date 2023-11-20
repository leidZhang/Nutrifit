package test.meal;

import main.backend.common.Result;
import main.backend.food.IFoodController;
import main.backend.food.entity.Food;
import main.backend.food.impl.FoodController;
import main.backend.meal.IMealController;
import main.backend.meal.entity.Meal;
import main.backend.food.entity.Nutrient;
import main.backend.meal.impl.MealController;
import main.backend.user.entity.User;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class MealQueryTest {
    IMealController mealController = new MealController();
    IFoodController foodController = new FoodController();
    @Test
    public void mealSaveCase1() {
        //Testing user save Meal
        User user = new User(1, "Jane Doe", "jd123", "1234567", "female", null, 0, 0);
        Meal meal = new Meal(1, Date.valueOf("2020-01-01"), "Breakfast");
        Result res1 = foodController.getList();
        assertEquals("200", res1.getCode());
        Map<Food, Float> foodMap = new HashMap<>();
        if (res1.getCode().equals("200")) {
            for(int i = 0; i < 3; i++){
                foodMap.put(((List<Food>)res1.getData()).get(i), Float.intBitsToFloat(i));
            }
        }
        meal.setFoodMap(foodMap);
        Result res2 =  mealController.save(meal, user);
        assertEquals("200", res2.getCode());
    }
    @Test
    public void mealSaveCase2() {
        //Testing user save Meal with another meal type and more food
        User user = new User(1, "Jane Doe", "jd123", "1234567", "female", null, 0, 0);
        Meal meal = new Meal(1, Date.valueOf("2020-01-01"), "Lunch");
        Result res1 = foodController.getList();
        assertEquals("200", res1.getCode());
        Map<Food, Float> foodMap = new HashMap<>();
        if (res1.getCode().equals("200")) {
            for(int i = 0; i < ((List<Food>)res1.getData()).size(); i++){
                foodMap.put(((List<Food>)res1.getData()).get(i), Float.intBitsToFloat(i));
            }
        }
        meal.setFoodMap(foodMap);
        Result res2 =  mealController.save(meal, user);
        assertEquals("200", res2.getCode());

    }

    @Test
    public void mealSaveCase3() {
        //Testing user save Meal with no food
        User user = new User(1, "Jane Doe", "jd123", "1234567", "female", null, 0, 0);
        Meal meal = new Meal(1, Date.valueOf("2020-01-01"), "Lunch");
        Result res =  mealController.save(meal, user);
        assertNotEquals("200", res.getCode());
    }

    @Test
    public void mealSaveCase4() {
        //Testing user save Meal with no meal type and no date
        User user = new User(1, "Jane Doe", "jd123", "1234567", "female", null, 0, 0);
        Meal meal = new Meal(1, null, null);
        Result res =  mealController.save(meal, user);
        assertNotEquals("200", res.getCode());
    }

    @Test
    public void mealDeleteCase1()  {
        //Testing user delete one Meal
        User user = new User(12, "Mark Lee", "mlee", "000000", "male", null, 0, 0);
        Meal meal = new Meal(1, Date.valueOf("2020-01-01"), "Breakfast");
        Result res1 = foodController.getList();
        assertEquals("200", res1.getCode());
        Map<Food, Float> foodMap = new HashMap<>();
        if (res1.getCode().equals("200")) {
            for(int i = 0; i < 3; i++){
                foodMap.put(((List<Food>)res1.getData()).get(i), Float.intBitsToFloat(i));
            }
        }
        meal.setFoodMap(foodMap);

        Result res2 =  mealController.save(meal, user);
        assertEquals("200", res2.getCode());
        Result res3 =  mealController.save(meal, user);
        assertEquals("200", res3.getCode());
        Result res4 =  mealController.save(meal, user);
        assertEquals("200", res4.getCode());
        Result res5 = mealController.getByUser(user);
        assertEquals("200", res5.getCode());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (res5.getCode().equals("200")) {
            if (((List<Meal>) res5.getData()).size() == 0) {
                System.out.println("please add meals firist");
            }
            Result res6= mealController.delete(((List<Meal>) res5.getData()).get(0).getId());
            assertEquals("200", res6.getCode());
        }

    }

    @Test
    public void mealDeleteCase2(){
        //Testing user delete all Meal
        User user = new User(12, "Mark Lee", "mlee", "000000", "male", null, 0, 0);
        Meal meal = new Meal(1, Date.valueOf("2020-01-01"), "Breakfast");
        Result res1 = foodController.getList();
        assertEquals("200", res1.getCode());
        Map<Food, Float> foodMap = new HashMap<>();
        if (res1.getCode().equals("200")) {
            for(int i = 0; i < 3; i++){
                foodMap.put(((List<Food>)res1.getData()).get(i), Float.intBitsToFloat(i));
            }
        }
        meal.setFoodMap(foodMap);

        Result res2 =  mealController.save(meal, user);
        assertEquals("200", res2.getCode());
        Result res3 =  mealController.save(meal, user);
        assertEquals("200", res3.getCode());
        Result res4 =  mealController.save(meal, user);
        assertEquals("200", res4.getCode());
        Result res5 = mealController.getByUser(user);
        assertEquals("200", res5.getCode());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (res5.getCode().equals("200")) {
            if (((List<Meal>) res5.getData()).size() == 0) {
                System.out.println("please add meals firist");
            }
            for (int i = 0; i < ((List<Meal>) res5.getData()).size(); i++) {
                Result res6= mealController.delete(((List<Meal>) res5.getData()).get(i).getId());
                assertEquals("200", res6.getCode());
            }
        }
    }

    @Test
    public void mealDeleteCase3()  {
        //Testing user delete last Meal
        User user = new User(12, "Mark Lee", "mlee", "000000", "male", null, 0, 0);
        Meal meal = new Meal(1, Date.valueOf("2020-01-01"), "Breakfast");
        Result res1 = foodController.getList();
        assertEquals("200", res1.getCode());
        Map<Food, Float> foodMap = new HashMap<>();
        if (res1.getCode().equals("200")) {
            for(int i = 0; i < 3; i++){
                foodMap.put(((List<Food>)res1.getData()).get(i), Float.intBitsToFloat(i));
            }
        }
        meal.setFoodMap(foodMap);

        Result res2 =  mealController.save(meal, user);
        assertEquals("200", res2.getCode());
        Result res3 =  mealController.save(meal, user);
        assertEquals("200", res3.getCode());
        Result res4 =  mealController.save(meal, user);
        assertEquals("200", res4.getCode());
        Result res5 = mealController.getByUser(user);
        assertEquals("200", res5.getCode());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (res5.getCode().equals("200")) {
            if (((List<Meal>) res5.getData()).size() == 0) {
                System.out.println("please add meals firist");
            }
            Result res6= mealController.delete(((List<Meal>) res5.getData()).get((((List<Meal>) res5.getData()).size() - 1)).getId());
            assertEquals("200", res6.getCode());
        }
    }

    @Test
    public void mealGetByUserCase1(){
        //Testing user get all Meals
        User user = new User(1, "Jane Doe", "jd123", "1234567", "female", null, 0, 0);
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
        Result res = mealController.getByPeriod(user, Date.valueOf("2023-10-25"), Date.valueOf("2024-10-30"));
        assertNotEquals("200", res.getCode());

    }

    @Test
    public void foodListCase1() throws SQLException {
        Result res = foodController.getList();
        assertEquals("200", res.getCode());
        if (res.getCode().equals("200")) {
            List<Food> foods = (List<Food>) res.getData();
            for (Food food : foods) {
                assertNotNull("not null", food.getName());
                assertNotNull("not null", food.getGroup());
            }
        }

    }
}
