package main.backend.exercise.impl;

import main.backend.exercise.ExerciseData;
import main.backend.exercise.IExerciseController;
import main.backend.exercise.IExerciseService;
import main.backend.exercise.entity.Exercise;
import main.backend.user.entity.User;
import main.backend.common.Result;

import java.sql.Date;
import java.util.List;


public class ExerciseController implements IExerciseController {
    private IExerciseService exerciseService = new ExerciseService();

//    public void saveExerciseData(ExerciseData data, User user) {
//        exerciseService.saveExerciseData(data, user);
//    }

    @Override
    public Result save(Exercise exercise, User user) {
        try {
            exerciseService.save(exercise, user);
            return Result.success("Exercise saved successfully!");
        }catch(Exception e) {
            return Result.error("Error saving exercise: " + e.getMessage());
        }
    }

    @Override
    public Result delete(int id) {
        try {
            exerciseService.delete(id);
            return Result.success("Exercise deleted successfully!");
        }catch(Exception e) {
            return Result.error("Error deleting exercise: " + e.getMessage());
        }
    }

    @Override
    public Result getByUsername(String username) {
        try {
            List<Exercise> exercises = exerciseService.getByUsername(username);
            return Result.success(exercises);
        } catch (Exception e) {
            return Result.error("Error retrieving exercises: " + e.getMessage());
        }
    }

    @Override
    public Result getByPeriod(String username, Date start, Date end) {
        try {
            List<Exercise> exercises = exerciseService.getByPeriod(username, start, end);
            return Result.success(exercises);
        } catch (Exception e) {
            return Result.error("Error retrieving exercises by period: " + e.getMessage());
        }
    }
}

