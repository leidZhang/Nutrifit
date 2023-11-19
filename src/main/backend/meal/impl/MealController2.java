package main.backend.meal.impl;

import main.backend.common.Result;
import main.backend.meal.IMealController;
import main.backend.meal.IMealService;
import main.backend.meal.entity.Meal;
import main.backend.user.entity.User;

import java.util.Date;

public class MealController2 implements IMealController {
    IMealService service = new MealService2();

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
        return null;
    }

    @Override
    public Result getByUser(User user) {
        return null;
    }

    @Override
    public Result getByPeriod(User user, Date startDate, Date endDate) {
        return null;
    }
}
