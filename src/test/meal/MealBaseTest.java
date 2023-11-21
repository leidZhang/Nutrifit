package test.meal;

import main.backend.common.Result;
import main.backend.food.IFoodController;
import main.backend.food.entity.Food;
import main.backend.food.impl.FoodController;
import main.backend.meal.IMealController;
import main.backend.meal.entity.Meal;
import main.backend.meal.impl.MealController;
import main.backend.user.IUserController;
import main.backend.user.entity.User;
import main.backend.user.impl.UserController;

import java.util.List;

public abstract class MealBaseTest {
    protected final String SUCCESS_CODE = "200";
    protected IMealController mealController = new MealController();
    protected IFoodController foodController = new FoodController();
    protected IUserController userController = new UserController();
    protected User user; // this store the user in the test cases
    protected List<Meal> mealList; // store the user meal records here
    protected List<Food> foodList; // store the food list here

    public MealBaseTest() {
        initTest();
    }

    private void initTest() {
        Result userRes = userController.login("js288c", "0000000");
        user = (User) userRes.getData(); // get test user
        Result mealRes = mealController.getByUser(user);
        mealList = (List<Meal>) mealRes.getData();
        Result foodRes = foodController.getList();
        foodList = (List<Food>) foodRes.getData();
    }
}
