package main.backend.predict.impl;

import main.backend.exercise.IExerciseService;
import main.backend.exercise.impl.ExerciseService;
import main.backend.meal.IMealService;
import main.backend.meal.impl.MealService;
import main.backend.predict.IPredictService;
import main.backend.user.entity.User;
import main.backend.validator.impl.FutureDateValidator;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;

public class PredictService implements IPredictService {
    private final int RATIO = 7700;
    private IMealService mealService = new MealService();
    private IExerciseService exerciseService = new ExerciseService();

    private int calTotalCalorie(Map<Date, Float> calories) {
        int res = 0;
        for (Map.Entry<Date, Float> entry : calories.entrySet()) {
            res += entry.getValue();
        }

        return res;
    }

    private float calFatLose(Date date, Map<Date, Float> intakeMap, Map<Date, Float> expenditureMap) throws IllegalArgumentException {
        int days= Math.max(intakeMap.size(), expenditureMap.size());
        if (days == 0) throw new IllegalArgumentException("Insufficient data");

        LocalDate today = LocalDate.now();
        long dayDiff = ChronoUnit.DAYS.between(today, date.toLocalDate());
        int calorieDiff = calTotalCalorie(intakeMap) - calTotalCalorie(expenditureMap);

        double rate = -1.0 * calorieDiff / days;

        return (float) rate * dayDiff / RATIO;
    }

    @Override
    public float getPredictionByDate(User user, Date date) throws SQLException, IllegalArgumentException {
        // validate date
        FutureDateValidator validator = new FutureDateValidator(date);
        validator.validate();

        LocalDate today = LocalDate.now();
        LocalDate startDate = today.minusDays(14);
        Map<Date, Float> mealCalories = mealService.getCaloriesByDate(user, Date.valueOf(startDate), Date.valueOf(today));
        Map<Date, Float> exerciseCalories = exerciseService.getCaloriesByDate(user, Date.valueOf(startDate), Date.valueOf(today));

        return calFatLose(date, mealCalories, exerciseCalories);
    }
}
