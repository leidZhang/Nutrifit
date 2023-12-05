package main.backend.meal.util;

import main.backend.common.DTOValidator;
import main.backend.meal.entity.Meal;
import main.backend.validator.Validator;
import main.backend.validator.impl.DateValidator;
import main.backend.validator.impl.FoodValidator;
import main.backend.validator.impl.IdValidator;
import main.backend.validator.impl.MealTypeValidator;

public class MealValidator extends DTOValidator {
    public MealValidator(Meal meal) {
        VALIDATORS = new Validator[]{
            new DateValidator(meal.getDate()),
            new MealTypeValidator(meal.getType()),
            new FoodValidator(meal.getFoodMap()),
        };
    }
}
