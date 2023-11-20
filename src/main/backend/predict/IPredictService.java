package main.backend.predict;

import main.backend.common.Result;
import main.backend.user.entity.User;

import java.sql.Date;
import java.sql.SQLException;

public interface IPredictService {
    float getPredictionByDate(User user, Date date) throws SQLException, IllegalArgumentException;
}
