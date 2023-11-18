package main.backend.validator.impl;

import main.backend.validator.Validator;

public class UsernameValidator extends Validator {
    private final String REGEX = "^[a-zA-Z0-9]+$";

    public UsernameValidator(Object obj) {
        super(obj);
    }

    @Override
    public void validate() throws IllegalArgumentException {
        if (obj instanceof String input) {
            if (!input.matches(REGEX)) {
                throw new IllegalStateException("Invalid Username");
            }
        } else {
            throw new IllegalArgumentException("Input must be String");
        }
    }
}
