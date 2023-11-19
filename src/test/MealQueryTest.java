package test;

import main.backend.food.IFoodMapper;
import main.backend.food.entity.Food;
import main.backend.meal.IMealMapper;
import main.backend.meal.entity.Meal;
import main.backend.food.entity.Nutrient;
import main.backend.food.impl.FoodMapper;
import main.backend.meal.impl.MealMapper;
import main.backend.meal.util.MealUtil;
import main.backend.meal.util.NutrientCalculator;
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
        foodMap.put(food, 15.0F);

        MealUtil util = new MealUtil();
        Map<Nutrient, Float> nutrientMap = util.getNutrientMap(foodMap);

        for (Map.Entry<Nutrient, Float> entry : nutrientMap.entrySet()) {
            System.out.println(entry.getKey().getName() + ": " + entry.getValue() + "g");
        }

        NutrientCalculator calculator = new NutrientCalculator(); // replace it with interface
        float vitaminValue = calculator.getTotalVitamin(nutrientMap);
        float carbValue = calculator.getTotalCarbs(nutrientMap);
        float proteinValue = calculator.getTotalProtein(nutrientMap);
        float otherValue = calculator.getTotalOthers(nutrientMap);

        System.out.println("\nVitamin: " + vitaminValue);
        System.out.println("Carb: " + carbValue);
        System.out.println("Protein: " + proteinValue);
        System.out.println("Other: " + otherValue);
//
//        // get user
//        IUserMapper userMapper = new UserMapper();
//        IMealMapper mealMapper = new MealMapper();
//
//        User user = userMapper.getUser("jd123");
//        List<Meal> mealList = mealMapper.getByUser(user);
//        System.out.println("\nThe user has " + mealList.size() + " meal records\n");
//
//        for (Meal meal : mealList) {
//            Date date = meal.getDate();
//            String type = meal.getType();
//            System.out.println(user.getName() + " " + date + ", " + type);
//            Map<Food, Float> map = meal.getFoodMap();
//
//            if (map.size() == 0) break;
//            for (Map.Entry<Food, Float> entry : map.entrySet()) {
//                food = entry.getKey();
//                Float val = entry.getValue();
//
//                System.out.println(food + ": " + val + "g, " + food.getGroup());
//            }
//        }

//        Meal meal2 = mealMapper.getByID(1);
//        System.out.println(meal2);
    }
}
