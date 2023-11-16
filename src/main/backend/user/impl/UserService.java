package main.backend.user.impl;

import main.backend.user.entity.User;
import main.backend.user.IUserMapper;
import main.backend.user.IUserService;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class UserService implements IUserService {
    private IUserMapper userMapper = new UserMapper();

    @Override
    public User login(String username, String password) throws SQLException, NullPointerException {
        User user = userMapper.login(username, password);
        if (user == null) throw new NullPointerException("Wrong username or password");

        return user;
    }

    @Override
    public void save(User user) throws SQLException, RuntimeException {
        invalidUserJudge(user);
        String username = user.getUsername();
        if (userMapper.getUser(username) != null) throw new RuntimeException("Duplicate username!");

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
    public void updateUser(User user) throws IllegalArgumentException, SQLException {
        validateUser(user);
        userMapper.updateUser(user);
    }
    private void validateUser(User user) throws IllegalArgumentException { // change to validateUser in refactor stage 
        if (user.getAge() < 0) throw new IllegalArgumentException("Invalid age");
        if (user.getWeight() < 0) throw new IllegalArgumentException("Invalid Weight");
        if (user.getHeight() < 0) throw new IllegalArgumentException("Invalid Height");
        if (user.getPassword().isEmpty()) throw new IllegalArgumentException("Invalid Password");
        if (user.getUsername().isEmpty()) throw new IllegalArgumentException("Invalid Username");
        if (user.getName().isEmpty()) throw new IllegalArgumentException("Invalid Name");
        if (!Objects.equals(user.getSex(), "male") && !Objects.equals(user.getSex(), "female"))
            throw new IllegalArgumentException("Invalid Sex");
    }
}
