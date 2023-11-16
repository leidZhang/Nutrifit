package main.backend.food;

import main.backend.food.entity.Food;
import main.backend.food.entity.Nutrient;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IFoodMapper {
    List<Food> getList() throws SQLException; // get food list, for the dropdown list in the meal page
    Map<Nutrient, Float> getUnitNutrientValue(int id) throws SQLException; // get nutrition value except energy and 0s
    long getUnitCalories(int id) throws SQLException; // get unit calorie
}
