package backend.service;

import backend.entity.User;

import java.sql.SQLException;

public interface IUserService {
    void save(User user) throws SQLException;
    User getByUsername(String username) throws SQLException, NullPointerException;
    void updateUser(String username) throws SQLException;
}
