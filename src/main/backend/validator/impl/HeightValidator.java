package main.backend.validator.impl;

import main.backend.validator.Validator;

public class HeightValidator extends Validator {
    private final double MAX_HEIGHT = 280.0F;

    public HeightValidator(Object obj) {
        super(obj);
    }

    @Override
    public void validate() throws IllegalArgumentException {
        if (obj instanceof Double input) {
            if (input < 0 || input > MAX_HEIGHT) {
                throw new IllegalStateException("Height out of boundary");
            }
        } else {
            throw new IllegalArgumentException("Input must be double");
        }
    }
}
