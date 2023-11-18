package test;

import main.backend.food.IFoodMapper;
import main.backend.food.entity.Food;
import main.backend.meal.IMealMapper;
import main.backend.meal.entity.Meal;
import main.backend.food.entity.Nutrient;
import main.backend.food.impl.FoodMapper;
import main.backend.meal.impl.MealMapper;
import main.backend.meal.util.MealUtil;
import main.backend.user.IUserMapper;
import main.backend.user.entity.User;
import main.backend.user.impl.UserMapper;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MealQueryTest {
    public static void main(String[] args) throws SQLException { // demonstration code, delete before submission
        // query food
         IFoodMapper mapper = new FoodMapper();

        // traverse map to get unit nutrient value
        Map<Nutrient, Float> nutrientFloatMap = mapper.getUnitNutrientValue(2);
//        for (Map.Entry<Nutrient, Float> entry : nutrientFloatMap.entrySet()) {
//            String nutrientName = entry.getKey().getName();
//            float nutrientValue = entry.getValue();
//            String nutrientUnit = entry.getKey().getUnit();
//            System.out.println(nutrientName + ": " + nutrientValue + nutrientUnit);
//        }
//        System.out.println("\n");


        Food food = new Food(2, "test_food");
        food.setNutrientFloatMap(nutrientFloatMap);
        Map<Food, Float> foodMap = new HashMap<>();
        foodMap.put(food, 200.0F);

        MealUtil util = new MealUtil();
        Map<Nutrient, Float> nutrientMap = util.getTotalNutrient(foodMap);
        List<Map.Entry<Nutrient, Float>> nutrientList = util.getNutrientList(nutrientMap);
        List<Map.Entry<Nutrient, Float>> res = util.sortNutrientList(nutrientList, 10);

        for (Map.Entry<Nutrient, Float> entry : res) {
            System.out.println(entry.getKey().getName() + ": " + entry.getValue() + "g");
        }

        // get user
        IUserMapper userMapper = new UserMapper();
        IMealMapper mealMapper = new MealMapper();

        User user = userMapper.getUser("jd123");
        List<Meal> mealList = mealMapper.getByUser(user);
        System.out.println("\nThe user has " + mealList.size() + " meal records\n");

        for (Meal meal : mealList) {
            Date date = meal.getDate();
            String type = meal.getType();
            System.out.println(user.getName() + " " + date + ", " + type);
            Map<Food, Float> map = meal.getFoodMap();

            if (map.size() == 0) break;
            for (Map.Entry<Food, Float> entry : map.entrySet()) {
                food = entry.getKey();
                Float val = entry.getValue();

                System.out.println(food + ": " + val + "g, " + food.getGroup());
            }
        }
    }
}
