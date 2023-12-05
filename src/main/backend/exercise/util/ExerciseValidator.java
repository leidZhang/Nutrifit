package main.backend.exercise.util;

import main.backend.common.DTOValidator;
import main.backend.exercise.entity.Exercise;
import main.backend.validator.Validator;
import main.backend.validator.impl.*;

public class ExerciseValidator extends DTOValidator {
    public ExerciseValidator(Exercise exercise) {
        VALIDATORS = new Validator[]{
                new ExerciseTypeValidator(exercise.getType()),
                new DateValidator(exercise.getDate()),
                new ExerciseDurationValidator(exercise.getDuration()),
                new ExerciseIntensityValidator(exercise.getIntensity())
        };
    }
}
