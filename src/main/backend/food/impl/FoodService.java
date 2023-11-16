package main.backend.food.impl;

import main.backend.food.IFoodMapper;
import main.backend.food.IFoodService;
import main.backend.food.entity.Food;
import main.backend.food.entity.Nutrient;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class FoodService implements IFoodService {
    IFoodMapper mapper = new FoodMapper();

    @Override
    public List<Food> getList() throws SQLException {
        return mapper.getList();
    }

    @Override
    public Map<Nutrient, Float> getUnitNutrientValue(int id) throws SQLException {
        return mapper.getUnitNutrientValue(id);
    }

    @Override
    public long getUnitCalories(int id) throws SQLException {
        return mapper.getUnitCalories(id);
    }
}
