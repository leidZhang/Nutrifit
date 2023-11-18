package main.backend.exercise.impl;

import main.backend.exercise.ExerciseData;
import main.backend.exercise.IExerciseMapper;
import main.backend.exercise.IExerciseService;
import main.backend.exercise.entity.Exercise;
import main.backend.exercise.impl.ExerciseMapper;
import main.backend.user.entity.User;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;


public class ExerciseService implements IExerciseService {
    private IExerciseMapper exerciseMapper = new ExerciseMapper();

//    public void saveExerciseData(Exercise data, User user) {
//        int burnCalories = calBurnCalories(data, user);
//        Exercise exercise = new Exercise(data.getDate(), data.getType(), data.getIntensity(), data.getDuration(), burnCalories);
//        save(exercise, user);
//    }

    @Override
    public void save(Exercise exercise, User user) {
        try {
            int calories = calBurnCalories(exercise, user);
            System.out.println("Burned: " + calories);
            exercise.setBurnCalories(calories);
            exerciseMapper.save(exercise, user);
        } catch (SQLException e) {
            throw new RuntimeException("Database error: Unable to save exercise.", e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            exerciseMapper.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException("Database error: Unable to delete exercise.", e);
        }
    }

    @Override
    public List<Exercise> getByUsername(String username) {
        try {
            return exerciseMapper.getByUsername(username);
        } catch (SQLException e) {
            throw new RuntimeException("Database error: Unable to retrieve exercises by username.", e);
        }
    }

    @Override
    public List<Exercise> getByPeriod(String username, Date start, Date end) {
        try {
            return exerciseMapper.getByPeriod(username, start, end);
        } catch (SQLException e) {
            throw new RuntimeException("Database error: Unable to retrieve exercises by specific period.", e);
        }
    }

    private int calBurnCalories(Exercise data, User user) {
        System.out.println("pass cal BC");

        int MET;
        long bmr = calBMR(user);
        String intensity = data.getIntensity();
        int duration = data.getDuration();

        MET = switch (intensity) {
            case "Very High" -> 11;
            case "High" -> 8;
            case "Medium" -> 5;
            case "Low" -> 3;
            default -> throw new IllegalStateException("Unexpected value: " + intensity);
        };

        return (int) (bmr * MET / 24.0 * (duration / 60.0));
    }

    private Long calBMR(User user) {
        System.out.println("pass cal BMR");
        //bmr计算似乎放在user中更合理一些
        long bmr;
        double weight = user.getWeight();
        double height = user.getHeight();
        int age = user.getAge();
        String sex = user.getSex();

        if(sex.equalsIgnoreCase("male")) {
            bmr = (long) (weight * 10 + height * 6.25 - age * 5L + 5);
        }else if(sex.equalsIgnoreCase("female")) {
            bmr = (long) (weight * 10 + height * 6.25 - age * 5L - 161);
        }else {
            throw new IllegalArgumentException("Cannot calculate BMR if sex is neither male nor female");
        }

        return bmr;
    }

}

