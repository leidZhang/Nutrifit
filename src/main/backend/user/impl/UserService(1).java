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
        invalidUserJudge(user);
        userMapper.updateUser(user);
    }

    private void invalidUserJudge(User user) throws IllegalArgumentException { // change to validateUser in refactor stage
        //name judge
        String name = user.getName();
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid name");
        }
        for (char ch : name.toCharArray()) {
            if (!Character.isLetter(ch) && ch != ' ' && ch != '-') {
                throw new IllegalArgumentException("Invalid name");
            }
        }
        if (name.length() < 2 || name.length() > 50) {
            throw new IllegalArgumentException("Invalid name");
        }
        //username judge
        String username = user.getUsername();
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid username");
        }
        if (username.length() > 16) {
            throw new IllegalArgumentException("Invalid username");
        }
        String pattern = "^[a-zA-Z0-9_]+$";
        if (!username.matches(pattern)) {
            throw new IllegalArgumentException("Invalid username");
        }
        //judge password
        String password = user.getPassword();
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid Password");
        }
        if (password.length() < 6) {
            throw new IllegalArgumentException("Invalid Password");
        }
        //judge sex
        if (!Objects.equals(user.getSex(), "male") && !Objects.equals(user.getSex(), "female"))
            throw new IllegalArgumentException("Invalid Sex");
        //judge date of birth
        Date date = user.getDateOfBirth();
        Date startDate = Date.valueOf("1955-1-1");
        Date endDate = Date.valueOf("2055-12-31");
        if (date.before(startDate) || date.after(endDate)) {
            throw new IllegalArgumentException("Invalid date of birth");
        }
        if (user.getWeight() < 0) throw new IllegalArgumentException("Invalid Weight");
        if (user.getHeight() < 0) throw new IllegalArgumentException("Invalid Height");

    }
}
