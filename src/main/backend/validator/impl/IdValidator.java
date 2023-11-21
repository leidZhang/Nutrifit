package main.backend.validator.impl;

import main.backend.validator.Validator;

public class IdValidator extends Validator {

    public IdValidator(Object obj) {
        super(obj);
    }

    @Override
    public void validate() throws IllegalArgumentException {
        if (obj instanceof Integer input) {
            if (input < 0) {
                throw new IllegalStateException("Id out of boundary");
            }
        } else {
            throw new IllegalArgumentException("Input must be integer");
        }
    }
}
