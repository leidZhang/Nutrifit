package main.backend.meal;

import main.backend.common.Result;
import main.backend.food.entity.Nutrient;
import main.backend.meal.entity.Meal;
import main.backend.user.entity.User;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Map;

public interface IMealController {
    Result save(Meal meal, User user);
    Result delete(int id);
    Result getById(int id);
    Result getByUser(User user);
    Result getByPeriod(User user, Date startDate, Date endDate);
    Result getSortedDailyNutrient(User user, Date startDate, Date endDate);
    Result getMealGroups(User user, Date startDate, Date endDate);
}
