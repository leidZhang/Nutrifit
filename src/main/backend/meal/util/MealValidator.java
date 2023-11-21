package main.backend.meal.util;

import main.backend.meal.entity.Meal;
import main.backend.validator.Validator;
import main.backend.validator.impl.DateValidator;
import main.backend.validator.impl.FoodValidator;
import main.backend.validator.impl.IdValidator;
import main.backend.validator.impl.MealTypeValidator;

public class MealValidator {
    private final Validator[] VALIDATORS;

    public MealValidator(Meal meal) {
        VALIDATORS = new Validator[]{
            new DateValidator(meal.getDate()),
            new MealTypeValidator(meal.getType()),
            new FoodValidator(meal.getFoodMap()),
        };
    }

    public void validate() throws IllegalArgumentException {
        for (Validator validator : VALIDATORS) {
            validator.validate();
        }
    }
}
