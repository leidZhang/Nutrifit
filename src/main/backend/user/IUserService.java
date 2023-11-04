package main.backend.user;

import main.backend.user.entity.User;

import java.sql.SQLException;

public interface IUserService {
    void save(User user) throws SQLException;
    User getByUsername(String username) throws SQLException, NullPointerException;
    void updateUser(User user) throws SQLException;


}
