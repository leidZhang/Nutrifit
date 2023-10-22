package main.backend.user;

import main.backend.user.entity.User;

import java.sql.SQLException;

public interface IUserMapper {
    void save(User user) throws SQLException;
    User getUser(String username) throws SQLException;
    void updateUser(String username) throws SQLException;
}
