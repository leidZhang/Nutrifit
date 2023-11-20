package main.backend.validator.impl;

import main.backend.validator.Validator;

import java.sql.Date;

public class FutureDateValidator extends Validator {
    public FutureDateValidator(Object obj) {
        super(obj);
    }

    @Override
    public void validate() throws IllegalArgumentException {
        if (obj instanceof Date input) {
            Date today = new Date(System.currentTimeMillis());
            if (!input.after(today)) {
                throw new IllegalStateException("Date must after today");
            }
        } else {
            throw new IllegalStateException("Invalid Date");
        }
    }
}
