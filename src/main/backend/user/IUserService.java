package main.backend.user;

import main.backend.user.entity.User;

import java.sql.SQLException;

public interface IUserService {
    User login(String username, String password) throws SQLException, NullPointerException;
    void save(User user) throws SQLException, RuntimeException;
    User getByUsername(String username) throws SQLException, NullPointerException;
    void updateUser(User user) throws SQLException;
}
