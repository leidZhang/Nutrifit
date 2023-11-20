package main.backend.validator.impl;

import main.backend.validator.Validator;

import java.util.Arrays;
import java.util.List;

public class MealTypeValidator extends Validator {
    private final List<String> TYPES = Arrays.asList("Lunch", "Dinner", "Breakfast", "Snack");

    public MealTypeValidator(Object obj) {
        super(obj);
    }

    @Override
    public void validate() throws IllegalArgumentException, IllegalStateException {
        if (obj instanceof String input) {
            if (!TYPES.contains(input)) {
                throw new IllegalArgumentException("Meal type must be Lunch, Dinner, Breakfast, or Snack");
            }
        } else {
            throw new IllegalStateException("Type must be String");
        }
    }
}
