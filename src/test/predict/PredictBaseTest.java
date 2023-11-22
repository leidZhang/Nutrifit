package test.predict;

import main.backend.common.Result;
import main.backend.predict.IPredictController;
import main.backend.predict.impl.PredictController;
import main.backend.user.IUserController;
import main.backend.user.entity.User;
import main.backend.user.impl.UserController;

import java.time.LocalDate;

public abstract class PredictBaseTest {
    protected final String SUCCESS_CODE = "200";
    protected final IPredictController controller = new PredictController();
    protected final IUserController userController = new UserController();
    protected LocalDate today;
    protected LocalDate upperBoundaryDate;
    protected LocalDate lowerBoundaryDate;
    protected User user;

    public PredictBaseTest() {
        initTest();
    }

    private void initTest() {
        today = LocalDate.now();
        lowerBoundaryDate = today.plusDays(1);
        upperBoundaryDate = today.plusYears(1);

        Result userRes = userController.login("js288c", "0000000");
        user = (User) userRes.getData(); // get test user
    }
}
