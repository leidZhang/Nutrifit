package main.backend.validator.impl;

import main.backend.validator.Validator;

import java.util.Arrays;
import java.util.List;

public class ExerciseIntensityValidator extends Validator {
    private final List<String> INTENSITIES = Arrays.asList("Very High", "High", "Medium", "Low");
    public ExerciseIntensityValidator(Object obj) {
        super(obj);
    }

    @Override
    public void validate() throws IllegalArgumentException, IllegalStateException {
        if (obj instanceof String input) {
            if (!INTENSITIES.contains(input)) {
                throw new IllegalArgumentException("Exercise intensity must be Very High, High, Medium or Low");
            }
        } else {
            throw new IllegalStateException("Type must be String");
        }
    }
}



