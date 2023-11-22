package main.backend.validator.impl;

import main.backend.food.entity.Food;
import main.backend.validator.Validator;

import java.util.Map;

public class FoodValidator extends Validator {
    public FoodValidator(Object obj) {
        super(obj);
    }

    private void validateSingleFood(Map<Food, Float> foodMap, Food food) throws IllegalArgumentException {
        if (food == null) throw new IllegalArgumentException("Food cannot be null");
        if (foodMap.get(food) <= 0) throw new IllegalArgumentException("Quantity must be positive");
    }

    private void checkFoodMap(Map<Food, Float> foodMap) throws IllegalArgumentException {
        for (Food food : foodMap.keySet()) {
            validateSingleFood(foodMap, food);
        }
    }

    @Override
    public void validate() throws IllegalArgumentException {
        Class<Map<Food, Float>> foodMapClass = (Class<Map<Food, Float>>) (Class<?>) Map.class;
        Map<Food, Float> foodMap = foodMapClass.cast(obj);

        if (foodMap != null) {
            if (foodMap.size() == 0) throw new IllegalArgumentException("Food list cannot be empty");

            checkFoodMap(foodMap);
        } else {
            throw new IllegalArgumentException("Invalid Food");
        }
    }
}
