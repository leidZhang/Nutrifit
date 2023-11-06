package main.backend.user.impl;

import main.backend.user.entity.User;
import main.backend.user.IUserMapper;
import main.backend.user.IUserService;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class UserService implements IUserService {
    private IUserMapper userMapper = new UserMapper();

    @Override
    public void save(User user) throws SQLException {
        userMapper.save(user);
    }

    @Override
    public User getByUsername(String username) throws SQLException, NullPointerException {
        User user = userMapper.getUser(username);
        if (user == null) throw new NullPointerException("User does not exist");

        Date today = new Date(System.currentTimeMillis());
        Date dateOfBirth = user.getDateOfBirth();
        LocalDate todayLocal = today.toLocalDate();
        LocalDate birthdayLocal = dateOfBirth.toLocalDate();

        int age = (int) ChronoUnit.YEARS.between(birthdayLocal, todayLocal);
        user.setAge(age);

        return user;
    }

    @Override
    public void updateUser(User user) throws SQLException {
        userMapper.updateUser(user);
    }
}
