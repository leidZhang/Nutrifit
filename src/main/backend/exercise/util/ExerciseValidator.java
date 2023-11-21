package main.backend.exercise.util;

import main.backend.exercise.entity.Exercise;
import main.backend.validator.Validator;
import main.backend.validator.impl.*;

public class ExerciseValidator {
    private final Validator[] VALIDATORS;

    public ExerciseValidator(Exercise exercise) {
        VALIDATORS = new Validator[]{
                new ExerciseTypeValidator(exercise.getType()),
                new DateValidator(exercise.getDate()),
                new ExerciseDurationValidator(exercise.getDuration()),
                new ExerciseIntensityValidator(exercise.getIntensity())
        };
    }

    public void validate() throws IllegalArgumentException {
        for (Validator validator : VALIDATORS) {
            validator.validate();
        }
    }

}
