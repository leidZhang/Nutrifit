package main.backend.meal.impl;

import main.backend.common.Result;
import main.backend.meal.IMealController;
import main.backend.meal.IMealService;
import main.backend.meal.entity.Meal;
import main.backend.user.entity.User;

import java.sql.Date;

public class MealController implements IMealController {
    private IMealService service = new MealService();

    @Override
    public Result save(Meal meal, User user) {
        try {
            service.save(meal, user);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @Override
    public Result delete(int id) {
        try {
            service.delete(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @Override
    public Result getById(int id) {
        try {
            return Result.success(service.getById(id));
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @Override
    public Result getByUser(User user) {
        try {
            return Result.success(service.getByUser(user));
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @Override
    public Result getByPeriod(User user, Date startDate, Date endDate) {
        try {
            return Result.success(service.getByPeriod(user, startDate, endDate));
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @Override
    public Result getSortedDailyNutrient(User user, Date startDate, Date endDate) {
        try {
            return Result.success(service.getSortedDailyNutrient(user, startDate, endDate));
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @Override
    public Result getMealGroups(User user, Date startDate, Date endDate) {
        try {
            return Result.success(service.getMealGroups(user, startDate, endDate));
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @Override
    public Result getCaloriesByDate(User user, Date startDate, Date endDate) {
        try {
            return Result.success(service.getCaloriesByDate(user, startDate, endDate));
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
