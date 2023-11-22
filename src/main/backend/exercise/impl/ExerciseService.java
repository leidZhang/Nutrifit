package main.backend.exercise.impl;

import main.backend.common.PeriodValidator;
import main.backend.exercise.IExerciseMapper;
import main.backend.exercise.IExerciseService;
import main.backend.exercise.entity.Exercise;
import main.backend.exercise.util.ExerciseValidator;
import main.backend.user.entity.User;
import main.backend.validator.impl.IdValidator;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public class ExerciseService implements IExerciseService {
    private IExerciseMapper exerciseMapper = new ExerciseMapper();

    @Override
    public void save(Exercise exercise, User user) throws SQLException, IllegalArgumentException {
        ExerciseValidator validator = new ExerciseValidator(exercise);
        validator.validate();

        int calories = calBurnCalories(exercise, user);//calculate burned calories
        System.out.println("Burned: " + calories);
        exercise.setBurnCalories(calories); //set burned calories for exercise
        exerciseMapper.save(exercise, user);
    }

    @Override
    public void delete(int id) throws SQLException {
        IdValidator validator = new IdValidator(id);
        validator.validate();
        exerciseMapper.delete(id);
    }

    @Override
    public List<Exercise> getByUsername(String username) throws SQLException {
        return exerciseMapper.getByUsername(username);
    }

    @Override
    public List<Exercise> getByPeriod(String username, Date startDate, Date endDate) throws SQLException, IllegalArgumentException {
        PeriodValidator validator = new PeriodValidator(startDate, endDate);
        validator.validate();

        return exerciseMapper.getByPeriod(username, startDate, endDate);
    }

    @Override
    public Map<Date, Float> getCaloriesByDate(User user, Date startDate, Date endDate) throws SQLException, IllegalArgumentException {
        PeriodValidator validator = new PeriodValidator(startDate, endDate);
        validator.validate();

        return exerciseMapper.getCaloriesByDate(user, startDate, endDate);
    }

    @Override
    public Map<Date, Integer> getDailyExerciseMinutesByDate(User user, Date startDate, Date endDate) throws SQLException, IllegalArgumentException {
        PeriodValidator validator = new PeriodValidator(startDate, endDate);
        validator.validate();

        return exerciseMapper.getDailyExerciseMinutesByDate(user, startDate, endDate);
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

