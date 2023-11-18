package main.backend.validator.impl;

import main.backend.validator.Validator;

public class SexValidator extends Validator {
    private final String REGEX = "^(male|female)$";

    public SexValidator(Object obj) {
        super(obj);
    }

    @Override
    public void validate() throws IllegalArgumentException {
        if (obj instanceof String input) {
            if (!input.matches(REGEX)) {
                throw new IllegalStateException("Sex must be male or female");
            }
        } else {
            throw new IllegalArgumentException("Input must be String");
        }
    }
}
