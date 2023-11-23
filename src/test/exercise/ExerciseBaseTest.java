package test.exercise;

import main.backend.common.Result;
import main.backend.exercise.IExerciseController;
import main.backend.exercise.entity.Exercise;
import main.backend.exercise.impl.ExerciseController;
import main.backend.user.IUserController;
import main.backend.user.entity.User;
import main.backend.user.impl.UserController;

import java.util.List;

public class ExerciseBaseTest {
    protected final String SUCCESS_CODE = "200";
    protected IExerciseController exerciseController = new ExerciseController();
    protected IUserController userController = new UserController();
    protected User user; // this store the user in the test cases
    protected List<Exercise> exerciseList;

    public ExerciseBaseTest() { initTest(); }

    private void initTest() {
        Result userRes = userController.login("jd123", "1234567");
        user = (User) userRes.getData(); // get test user
        Result exerciseRes = exerciseController.getByUsername(user.getUsername());
        exerciseList = (List<Exercise>) exerciseRes.getData();
    }
}
