package main.backend.exercise;

import main.backend.common.Result;
import main.backend.exercise.entity.Exercise;
import main.backend.user.entity.User;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface IExerciseController {
    Result save(Exercise exercise, User user);
    Result delete(int id);
    Result getByUsername(String name);
    Result getByPeriod(String name, Date startDate, Date endDate);
    Result getCaloriesByDate(User user, Date startDate, Date endDate);
}