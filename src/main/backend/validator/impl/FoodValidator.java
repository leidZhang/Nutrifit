package main.backend.validator.impl;

import main.backend.food.entity.Food;
import main.backend.validator.Validator;

import java.util.Map;

public class FoodValidator extends Validator {
    public FoodValidator(Object obj) {
        super(obj);
    }

    @Override
    public void validate() throws IllegalArgumentException {
        Class<Map<Food, Float>> foodMapClass = (Class<Map<Food, Float>>) (Class<?>) Map.class;
        Map<Food, Float> foodMap = foodMapClass.cast(obj);

        if (foodMap != null) {
            for (Food food : foodMap.keySet()) {
                if (food == null) throw new IllegalArgumentException("Food cannot be null");
                if (foodMap.get(food) < 0) throw new IllegalArgumentException("Quantity must be positive");
            }
        } else {
            throw new IllegalArgumentException("Invalid Food");
        }
    }
}
