package main.backend.exercise;

import main.backend.exercise.entity.Exercise;
import main.backend.user.entity.User;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface IExerciseMapper {
    void save(Exercise exercise, User user) throws SQLException;
    void delete(int id) throws SQLException;
    List<Exercise> getByUsername(String username) throws SQLException;
    List<Exercise> getByPeriod(String username, Date startDate, Date endDate) throws SQLException;
}
