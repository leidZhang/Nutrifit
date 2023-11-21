package main.backend.validator.impl;

import main.backend.validator.Validator;

import java.util.Arrays;
import java.util.List;

public class ExerciseTypeValidator extends Validator {
    private final List<String> TYPES = Arrays.asList("Walking", "Running", "Biking", "Swimming", "Others");
    public ExerciseTypeValidator(Object obj) {
        super(obj);
    }

    @Override
    public void validate() throws IllegalArgumentException, IllegalStateException {
        if (obj instanceof String input) {
            if (!TYPES.contains(input)) {
                throw new IllegalArgumentException("Exercise type must be Walking, Running, Biking, Swimming or Others");
            }
        } else {
            throw new IllegalStateException("Type must be String");
        }
    }
}
