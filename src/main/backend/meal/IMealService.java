package main.backend.meal;

import main.backend.food.entity.Nutrient;
import main.backend.meal.entity.Meal;
import main.backend.user.entity.User;

import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface IMealService {
    void save(Meal meal, User user) throws SQLException;
    void delete(int id) throws SQLException;
    Meal getById(int id) throws SQLException;
    List<Meal> getByUser(User user) throws SQLException;
    List<Meal> getByPeriod(User user, Date startDate, Date endDate) throws SQLException;
    Map<Nutrient, Float> getSortedDailyNutrient(User user, Date startDate, Date endDate) throws SQLException;
    Map<String, Float> getMealGroups(User user, Date startDate, Date endDate) throws SQLException;
}
