package main.backend.exercise;

import main.backend.exercise.entity.Exercise;
import main.backend.user.entity.User;

import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

public interface IExerciseService {
    void save(Exercise exercise, User user);
    void delete(int id);
    List<Exercise> getByUsername(String username) throws SQLException;
    List<Exercise> getByPeriod(String username, Date start, Date end) throws SQLException;
    // void saveExerciseData(ExerciseData data, User user);
}