package main.backend.validator.impl;

import main.backend.validator.Validator;

public class PasswordValidator extends Validator {
    private final String REGEX = "^.{7,}$";

    public PasswordValidator(Object obj) {
        super(obj);
    }

    @Override
    public void validate() throws IllegalArgumentException {
        if (obj instanceof String input) {
            if (!input.matches(REGEX)) {
                throw new IllegalStateException("Password is too short");
            }
        } else {
            throw new IllegalArgumentException("Input must be String");
        }
    }
}
