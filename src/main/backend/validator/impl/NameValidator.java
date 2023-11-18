package main.backend.validator.impl;

import main.backend.validator.Validator;

public class NameValidator extends Validator {
    private final String REGEX = "^[\\p{L}']+(\\s[\\p{L}']+)*$";

    public NameValidator(Object obj) {
        super(obj);
    }

    @Override
    public void validate() throws IllegalArgumentException {
        if (obj instanceof String input) {
            if (!input.matches(REGEX)) {
                throw new IllegalStateException("Invalid Name");
            }
        } else {
            throw new IllegalArgumentException("Input must be String");
        }
    }
}
