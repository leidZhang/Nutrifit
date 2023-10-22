package main.backend.user.impl;

import main.backend.user.entity.User;
import main.backend.user.IUserMapper;
import main.backend.user.IUserService;

import java.sql.SQLException;

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
        return user;
    }

    @Override
    public void updateUser(String username) throws SQLException {

    }
}
