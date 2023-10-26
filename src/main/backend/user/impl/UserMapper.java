package main.backend.user.impl;

import main.backend.common.ConnectionUtil;
import main.backend.user.entity.User;
import main.backend.user.IUserMapper;

import java.sql.*;

public class UserMapper implements IUserMapper {
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
            int age = user.getAge();

            // use PreparedStatement with placeholders
            String query = "insert into user(name, username, sex, date_of_birth, height, weight, age) ";
            query += "values (?, ?, ?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(query);
            // set parameters with corresponding methods
            ps.setString(1, name);
            ps.setString(2, username);
            ps.setString(3, sex);
            ps.setDate(4, dateOfBirth);
            ps.setDouble(5, height);
            ps.setDouble(6, weight);
            ps.setInt(7, age);
            // update row
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            ConnectionUtil.close(connection, ps, null);
        }
    }

    @Override
    public User getUser(String username) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet res = null;
        User user = null;

        try {
            connection = ConnectionUtil.getConnection();

            // use PreparedStatement with placeholders
            String query = "select user_id, name, username, sex, date_of_birth, height, weight, age from user ";
            query += "where username = ?";
            ps = connection.prepareStatement(query);
            // set parameters with corresponding methods
            ps.setString(1, username);
            // execute the query and get the result set
            res = ps.executeQuery();
            if (res.next()) { // get data from the result set
                int id = res.getInt("user_id");
                String name = res.getString("name");
                String sex = res.getString("sex");
                Date dateOfBirth = res.getDate("date_of_birth");
                double height = res.getDouble("height");
                double weight = res.getDouble("weight");
                int age = res.getInt("age");

                user = new User(id, name, username, sex, dateOfBirth, height, weight, age);
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            ConnectionUtil.close(connection, ps, res);
        }

        return user;
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
            int age = user.getAge();

            // use PreparedStatement with placeholders
            String query = "update user";
            query += " set name = ?, username = ?, sex = ?, date_of_birth = ?, height = ?, weight = ?, age = ?";
            query += " where user_id = ?";
            ps = connection.prepareStatement(query);
            // set parameters with corresponding methods
            ps.setString(1, name);
            ps.setString(2, username);
            ps.setString(3, sex);
            ps.setDate(4, dateOfBirth);
            ps.setDouble(5, height);
            ps.setDouble(6, weight);
            ps.setInt(7, age);
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
