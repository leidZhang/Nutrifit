package main.backend.validator.impl;

import main.backend.validator.Validator;

import java.sql.Date;
import java.time.LocalDate;

public class FutureDateValidator extends Validator {
    public FutureDateValidator(Object obj) {
        super(obj);
    }

    @Override
    public void validate() throws IllegalArgumentException {
        if (obj instanceof Date input) {
            // check is a future date
            LocalDate today = LocalDate.now();
            LocalDate futureDate = input.toLocalDate();
            if (futureDate.isBefore(today)) {
                throw new IllegalArgumentException("Date must be in the future");
            }
            // check is in one year
            LocalDate oneYearLater = today.plusYears(1);
            if (futureDate.isAfter(oneYearLater)) {
                throw new IllegalArgumentException("Date must be within one year");
            }
        } else {
            throw new IllegalStateException("Invalid Date");
        }
    }
}
