package main.backend.validator.impl;

import main.backend.validator.Validator;

public class WeightValidator extends Validator {
    private final double MAX_WEIGHT = 2.80F;

    public WeightValidator(Object obj) {
        super(obj);
    }

    @Override
    public void validate() throws IllegalArgumentException {
        if (obj instanceof Double input) {
            if (input < 0 || input > MAX_WEIGHT) {
                throw new IllegalStateException("Weight out of boundary");
            }
        } else {
            throw new IllegalArgumentException("Input must be double");
        }
    }
}
