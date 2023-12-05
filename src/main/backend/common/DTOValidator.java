package main.backend.common;

import main.backend.validator.Validator;

public abstract class DTOValidator {
    protected Validator[] VALIDATORS;

    public void validate() throws IllegalArgumentException {
        for (Validator validator : VALIDATORS) {
            validator.validate();
        }
    }
}
