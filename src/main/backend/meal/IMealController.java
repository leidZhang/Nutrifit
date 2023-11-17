package main.backend.meal;

import main.backend.common.Result;
import main.backend.meal.entity.Meal;
import main.backend.user.entity.User;

import javax.xml.ws.Response;
import java.util.Date;
import java.util.List;

public interface IMealController {
    Result save(Meal meal, User user);
    Result delete(int id);
    Result getByUser(User user);
    Result getByPeriod(User user, Date startDate, Date endDate);
}
