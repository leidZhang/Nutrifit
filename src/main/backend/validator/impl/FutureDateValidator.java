package main.backend.validator.impl;

import main.backend.validator.Validator;

import java.sql.Date;
import java.time.LocalDate;

public class FutureDateValidator extends Validator {
    private LocalDate today;

    public FutureDateValidator(Object obj) {
        super(obj);
        today = LocalDate.now();
    }

    private void checkIsFutureDate(Date input) throws IllegalArgumentException {
        LocalDate futureDate = input.toLocalDate();
        if (futureDate.isBefore(today)) {
            throw new IllegalArgumentException("Date must be in the future");
        }
    }

    private void checkIsInOneYear(Date input) throws IllegalArgumentException {
        LocalDate futureDate = input.toLocalDate();
        LocalDate oneYearLater = today.plusYears(1);
        if (futureDate.isAfter(oneYearLater)) {
            throw new IllegalArgumentException("Date must be within one year");
        }
    }

    @Override
    public void validate() throws IllegalArgumentException {
        if (obj instanceof Date input) {
            // check is a future date
            checkIsFutureDate(input);
            // check is in one year
            checkIsInOneYear(input);
        } else {
            throw new IllegalStateException("Invalid Date");
        }
    }
}
