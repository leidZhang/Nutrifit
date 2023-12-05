package main.backend.user.impl;

import main.backend.common.ConnectionUtil;
import main.backend.user.entity.User;
import main.backend.user.IUserMapper;

import java.sql.*;

public class UserMapper implements IUserMapper {
    private User setUser(String username, PreparedStatement ps) throws SQLException {
        User user = null;

        try (ResultSet res = ps.executeQuery()) {
            // get data from the result set
            if (res.next()) {
                int id = res.getInt("user_id");
                String name = res.getString("name");
                String sex = res.getString("sex");
                Date dateOfBirth = res.getDate("date_of_birth");
                double height = res.getDouble("height");
                double weight = res.getDouble("weight");
                String password = res.getString("password");

                user = new User(id, name, username, sex);
                user.setDateOfBirth(dateOfBirth);
                user.setWeight(weight);
                user.setHeight(height);
                user.setPassword(password);
            }
        }

        return user;
    }

    @Override
    public User getUser(String username) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        User user = null;

        try {
            connection = ConnectionUtil.getConnection();

            // use PreparedStatement with placeholders
            String query = "select user_id, name, username, sex, date_of_birth, height, weight, password from user ";
            query += "where username = ?";
            ps = connection.prepareStatement(query);
            // set parameters with corresponding methods
            ps.setString(1, username);
            // get user
            user = setUser(username, ps);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            ConnectionUtil.close(connection, ps, null);
        }

        return user;
    }

    @Override
    public User login(String username, String password) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet res = null;
        User user = null;

        try {
            connection = ConnectionUtil.getConnection();

            // use PreparedStatement with placeholders
            String query = "select user_id, name, username, sex, date_of_birth, height, weight, password from user ";
            query += "where username = ? and password = ?";
            ps = connection.prepareStatement(query);
            // set parameters with corresponding methods
            ps.setString(1, username);
            ps.setString(2, password);
            // get user
            user = setUser(username, ps);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            ConnectionUtil.close(connection, ps, res);
        }

        return user;
    }

    @Override
    public void save(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionUtil.getConnection();
            // get user attributes
            String name = user.getName();
            String username = user.getUsername();
            String sex = user.getSex();
            Date dateOfBirth = user.getDateOfBirth();
            double height = user.getHeight();
            double weight = user.getWeight();
            String password = user.getPassword();

            // use PreparedStatement with placeholders
            String query = "insert into user(name, username, sex, date_of_birth, height, weight, password) ";
            query += "values (?, ?, ?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(query);
            // set parameters with corresponding methods
            ps.setString(1, name);
            ps.setString(2, username);
            ps.setString(3, sex);
            ps.setDate(4, dateOfBirth);
            ps.setDouble(5, height);
            ps.setDouble(6, weight);
            ps.setString(7, password);
            // update row
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            ConnectionUtil.close(connection, ps, null);
        }
    }

    @Override
    public void updateUser(User user) throws SQLException  {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionUtil.getConnection();
            // get user attributes
            int id = user.getId();
            String name = user.getName();
            String username = user.getUsername();
            String sex = user.getSex();
            Date dateOfBirth = user.getDateOfBirth();
            double height = user.getHeight();
            double weight = user.getWeight();
            String password = user.getPassword();

            // use PreparedStatement with placeholders
            String query = "update user";
            query += " set name = ?, username = ?, sex = ?, date_of_birth = ?, height = ?, weight = ?, password = ?";
            query += " where user_id = ?";
            ps = connection.prepareStatement(query);
            // set parameters with corresponding methods
            ps.setString(1, name);
            ps.setString(2, username);
            ps.setString(3, sex);
            ps.setDate(4, dateOfBirth);
            ps.setDouble(5, height);
            ps.setDouble(6, weight);
            ps.setString(7, password);
            ps.setInt(8, id);
            // insert new row
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            ConnectionUtil.close(connection, ps, null);
        }
    }
}
