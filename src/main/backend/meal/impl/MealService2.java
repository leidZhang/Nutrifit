package main.backend.meal.impl;

import main.backend.food.IFoodService;
import main.backend.food.entity.Food;
import main.backend.food.entity.Nutrient;
import main.backend.food.impl.FoodService;
import main.backend.food.util.GroupConverter;
import main.backend.meal.IMealMapper;
import main.backend.meal.IMealService;
import main.backend.meal.entity.Meal;
import main.backend.meal.util.MealUtil;
import main.backend.meal.util.NutrientCalculator;
import main.backend.user.entity.User;

import java.sql.SQLException;
import java.sql.Date;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MealService2 implements IMealService {
    IMealMapper mapper = new MealMapper();
    IFoodService service = new FoodService();
    MealUtil util = new MealUtil(); // replace it with interface
    NutrientCalculator calculator = new NutrientCalculator(); // replace it with interface
    GroupConverter converter = new GroupConverter(); // replace it with interface

    private int calCalories(Map<Food, Float> foodMap) throws SQLException {
        int res = 0;
        for (Map.Entry<Food, Float> entry : foodMap.entrySet()) {
            int id = entry.getKey().getId();
            float weight = entry.getValue();
            res += service.getUnitCalories(id) * weight / 100;
        }

        return res;
    }

    @Override
    public void save(Meal meal, User user) throws SQLException {
        // Meal need to calculate general nutrition
        Map<Food, Float> foodMap = meal.getFoodMap(); // we can load food map in front end
        Map<Nutrient, Float> mealNutrientMap = util.getTotalNutrient(foodMap); // raw nutrient map

        // cal nutrients
        int totalCalories = calCalories(foodMap);
        float vitaminValue = calculator.getTotalVitamin(mealNutrientMap);
        float carbValue = calculator.getTotalCarbs(mealNutrientMap);
        float proteinValue = calculator.getTotalProtein(mealNutrientMap);
        float otherValue = calculator.getTotalOthers(mealNutrientMap);
        // set nutrients
        meal.setTotalVitamins(vitaminValue);
        meal.setTotalProtein(proteinValue);
        meal.setTotalCarbs(carbValue);
        meal.setTotalOthers(otherValue);
        meal.setTotalCalories(totalCalories);

        mapper.save(meal, user);
    }

    @Override
    public void delete(int id) throws SQLException {
        mapper.delete(id);
    }

    @Override
    public List<Meal> getByUser(User user) throws SQLException {
        return mapper.getByUser(user);
    }

    @Override
    public List<Meal> getByPeriod(User user, Date startDate, Date endDate) throws SQLException {
        return mapper.getByPeriod(user, startDate, endDate);
    }

    private Map<Food, Float> getTotalFoodMap(List<Meal> mealList) {
        Map<Food, Float> res = new HashMap<>();
        for (Meal meal : mealList) {
            Map<Food, Float> foodMap = meal.getFoodMap();
            util.mergeFoodMap(res, foodMap);
        }

        return res;
    }

    public Map<Nutrient, Float> getSortedDailyNutrient(User user, Date startDate, Date endDate) throws SQLException {
        // as requested in use case 5, we have to provide a sorted data structure for the pie diagram
        List<Meal> mealList = mapper.getByPeriod(user, startDate, endDate);
        Map<Food, Float> totalFoodMap = getTotalFoodMap(mealList);

        int days = Period.between(startDate.toLocalDate(), endDate.toLocalDate()).getDays();

        return util.getSortedNutrientMap(totalFoodMap, days); // the result should be sorted nutrition with g as unit
    }

    public Map<String, Float> getMealGroups(User user, Date startDate, Date endDate) throws SQLException {
        // as requested in use case 7, we have to provide a data structure with CFG alignment, radar chart is recommended
        List<Meal> mealList = mapper.getByPeriod(user, startDate, endDate);
        Map<Food, Float> totalFoodMap = getTotalFoodMap(mealList);

        Map<String, Float> res = new HashMap<>();
        for (Map.Entry<Food, Float> entry : totalFoodMap.entrySet()) {
            String cnfGroup = entry.getKey().getGroup();
            float value = entry.getValue();
            String cfgGroup = converter.toCFGFoodGroup(cnfGroup);

            res.put(cfgGroup, value + res.getOrDefault(cfgGroup, 0f));
        }

        return util.toPercentageMap(res);
    }
}