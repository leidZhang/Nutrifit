package main.backend.meal;

import main.backend.meal.entity.Meal;
import main.backend.user.entity.User;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IMealMapper {
    void save(Meal meal, User user) throws SQLException;
    void delete(int id) throws SQLException;
    Meal getByID(int id) throws SQLException;
    List<Meal> getByUser(User user) throws SQLException;
    List<Meal> getByPeriod(User user, Date startDate, Date endDate) throws SQLException;
    Map<Date, Float> getCaloriesByDate(User user, Date startDate, Date endDate) throws SQLException;
}
