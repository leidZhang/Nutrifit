package main.backend.food.impl;

import main.backend.common.Result;
import main.backend.food.IFoodController;
import main.backend.food.IFoodService;
import main.backend.food.entity.Food;

import java.util.List;

public class FoodController implements IFoodController {
    IFoodService service = new FoodService();

    @Override
    public Result getList() {
        try {
            List<Food> list = service.getList();
            return Result.success(list);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
