package backend.mapper;

import backend.entity.User;

import java.sql.SQLException;

public interface IUserMapper {
    void save(User user) throws SQLException;
    User getUser(String username) throws SQLException;
    void updateUser(String username) throws SQLException;
}
