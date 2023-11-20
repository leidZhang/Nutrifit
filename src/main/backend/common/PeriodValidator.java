package main.backend.common;

import main.backend.validator.Validator;
import main.backend.validator.impl.DateValidator;

import java.sql.Date;

public class PeriodValidator {
    private Date startDate;
    private Date endDate;
    private Validator[] validators;

    public PeriodValidator(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        validators = new Validator[]{new DateValidator(startDate), new DateValidator(endDate)};
    }

    public void validate() throws IllegalArgumentException {
        for (Validator validator : validators) {
            validator.validate();
        }

        if (startDate.after(endDate)) {
            throw new IllegalArgumentException("The start Date must before the end date");
        }
    }
}
