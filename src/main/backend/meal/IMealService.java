package main.backend.meal;

import main.backend.meal.entity.Meal;
import main.backend.user.entity.User;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface IMealService {
    void save(Meal meal, User user) throws SQLException;
    void delete(int id) throws SQLException;
    List<Meal> getByUser(User user) throws SQLException;
    List<Meal> getByPeriod(User user, Date startDate, Date endDate) throws SQLException;
}
