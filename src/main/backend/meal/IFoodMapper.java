package main.backend.meal;

import main.backend.meal.entity.Nutrient;

import java.sql.SQLException;
import java.util.Map;

public interface IFoodMapper {
    Map<Nutrient, Float> getUnitNutrientValue(int id) throws SQLException;
}
