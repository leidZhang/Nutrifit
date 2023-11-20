package main.backend.user.util;

import main.backend.user.entity.User;
import main.backend.validator.Validator;
import main.backend.validator.impl.*;

public class UserValidator {
    private final Validator[] VALIDATORS;

    public UserValidator(User user) {
        VALIDATORS = new Validator[]{
                new DateValidator(user.getDateOfBirth()),
                new HeightValidator(user.getHeight()),
                new WeightValidator(user.getWeight()),
                new NameValidator(user.getName()),
                new UsernameValidator(user.getUsername()),
                new SexValidator(user.getSex()),
                new PasswordValidator(user.getPassword())
        };
    }

    public void validate() throws IllegalArgumentException {
        for (Validator validator : VALIDATORS) {
            validator.validate();
        }
    }
}
