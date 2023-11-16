package test;

import main.backend.food.IFoodMapper;
import main.backend.meal.IMealMapper;
import main.backend.meal.entity.Meal;
import main.backend.food.entity.Nutrient;
import main.backend.food.impl.FoodMapper;
import main.backend.meal.impl.MealMapper;
import main.backend.user.IUserMapper;
import main.backend.user.entity.User;
import main.backend.user.impl.UserMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class MealQueryTest {
    public static void main(String[] args) throws SQLException {
        // query food
        IFoodMapper mapper = new FoodMapper();

        // traverse map to get unit nutrient value
        Map<Nutrient, Float> nutrientFloatMap = mapper.getUnitNutrientValue(2);
        for (Map.Entry<Nutrient, Float> entry : nutrientFloatMap.entrySet()) {
            String nutrientName = entry.getKey().getName();
            float nutrientValue = entry.getValue();
            String nutrientUnit = entry.getKey().getUnit();
            System.out.println(nutrientName + ": " + nutrientValue + " " + nutrientUnit);
        }

        // get user
        IUserMapper userMapper = new UserMapper();
        IMealMapper mealMapper = new MealMapper();

        User user = userMapper.getUser("js288c");
        List<Meal> mealList = mealMapper.getByUser(user);
        System.out.println("\nThe user has " + mealList.size() + " meal records");
    }
}
