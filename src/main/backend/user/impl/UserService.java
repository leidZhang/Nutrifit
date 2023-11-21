package main.backend.user.impl;

import main.backend.user.entity.User;
import main.backend.user.IUserMapper;
import main.backend.user.IUserService;
import main.backend.user.util.UserValidator;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class UserService implements IUserService {
    private IUserMapper userMapper = new UserMapper();

    @Override
    public User login(String username, String password) throws SQLException, NullPointerException {
        User user = userMapper.login(username, password);
        if (user == null) throw new NullPointerException("Wrong username or password");

        Date dateOfBirth = user.getDateOfBirth();
        user.setAge(calAge(dateOfBirth));

        return user;
    }

    @Override
    public void save(User user) throws SQLException, RuntimeException {
        validateUser(user);
        String username = user.getUsername();
        if (userMapper.getUser(username) != null) throw new RuntimeException("Duplicate username!");

        userMapper.save(user);
    }

    private int calAge(Date dateOfBirth) {
        Date today = new Date(System.currentTimeMillis());
        LocalDate todayLocal = today.toLocalDate();
        LocalDate birthdayLocal = dateOfBirth.toLocalDate();

        return (int) ChronoUnit.YEARS.between(birthdayLocal, todayLocal);
    }

    @Override
    public User getByUsername(String username) throws SQLException, NullPointerException {
        User user = userMapper.getUser(username);
        if (user == null) throw new NullPointerException("User does not exist");


        Date dateOfBirth = user.getDateOfBirth();
        user.setAge(calAge(dateOfBirth));

        return user;
    }

    @Override
    public void updateUser(User user) throws IllegalArgumentException, SQLException {
        validateUser(user);
        userMapper.updateUser(user);
    }

    private void validateUser(User user) throws IllegalArgumentException { // change to validateUser in refactor stage
        UserValidator validator = new UserValidator(user);
        validator.validate();
    }
}
