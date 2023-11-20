package main.backend.predict.impl;

import main.backend.common.Result;
import main.backend.predict.IPredictController;
import main.backend.predict.IPredictService;
import main.backend.user.entity.User;

import java.sql.Date;

public class PredictController implements IPredictController {
    IPredictService service = new PredictService();

    @Override
    public Result getPredictionByDate(User user, Date date) {
        try {
            return Result.success(service.getPredictionByDate(user, date));
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
