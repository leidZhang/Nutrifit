package main.backend.predict;

import main.backend.common.Result;
import main.backend.user.entity.User;

import java.sql.Date;

public interface IPredictController {
    Result getPredictionByDate(User user, Date date);
}
