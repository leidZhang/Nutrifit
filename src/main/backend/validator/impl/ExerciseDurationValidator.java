package main.backend.validator.impl;

import main.backend.validator.Validator;

public class ExerciseDurationValidator extends Validator{
    private final int MAX_DURATION = 720; //max duration is 12 hours, that is 720 minutes in total

    public ExerciseDurationValidator(Object obj) {
        super(obj);
    }

    @Override
    public void validate() throws IllegalArgumentException, IllegalStateException{
        if (obj instanceof Integer input) {
            if (input <= 0 || input > MAX_DURATION) {
                throw new IllegalStateException("Duration out of boundary");
            }
        } else {
            throw new IllegalArgumentException("Input must be integer");
        }
    }
}
