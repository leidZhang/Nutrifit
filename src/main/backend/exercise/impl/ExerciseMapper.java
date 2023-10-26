package main.backend.exercise.impl;

import main.backend.common.ConnectionUtil;
import main.backend.exercise.IExerciseMapper;
import main.backend.exercise.entity.Exercise;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExerciseMapper implements IExerciseMapper {
    @Override
    public void save(Exercise exercise, String username) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionUtil.getConnection();
            // get exercise attributes
            Date date = exercise.getDate();
            String type = exercise.getType();
            String intensity = exercise.getIntensity();
            int duration = exercise.getDuration();
            int burnCalories = exercise.getBurnCalories();

            // use PreparedStatement with placeholders
            String query = "insert into exercise(date, type, intensity, duration, burn_calories, user_id) ";
            query += "values (?, ?, ?, ?, ?, (select user_id from user where username = ?))";
            ps = connection.prepareStatement(query);
            // set parameters with corresponding methods
            ps.setDate(1, date);
            ps.setString(2, type);
            ps.setString(3, intensity);
            ps.setInt(4, duration);
            ps.setInt(5, burnCalories);
            ps.setString(6, username);
            // insert row
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            ConnectionUtil.close(connection, ps, null);
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionUtil.getConnection();
            // use PreparedStatement with placeholders
            String query = "delete from exercise where exercise_id = ?";
            ps = connection.prepareStatement(query);
            // set parameters with corresponding methods
            ps.setInt(1, id);
            // delete row
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            ConnectionUtil.close(connection, ps, null);
        }
    }

    @Override
    public List<Exercise> getByUsername(String username) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet res = null;
        List<Exercise> exerciseList = new ArrayList<>();

        try {
            connection = ConnectionUtil.getConnection();
            // use PreparedStatement with placeholders
            String query = "select exercise_id, date, type, intensity, duration, burn_calories ";
            query += "from exercise where user_id = (select user_id from user where username = ?)";
            ps = connection.prepareStatement(query);
            // set parameters with corresponding methods
            ps.setString(1, username);
            // execute the query
            res = ps.executeQuery();
            while (res.next()) {
                // get the data from each column
                int id = res.getInt("exercise_id");
                Date date = res.getDate("date");
                String type = res.getString("type");
                String intensity = res.getString("intensity");
                int duration = res.getInt("duration");
                int burnCalories = res.getInt("burn_calories");
                // create a new Exercise object with the data
                Exercise exercise = new Exercise(id, date, type, intensity, duration, burnCalories);
                // add the object to the list
                exerciseList.add(exercise);
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            ConnectionUtil.close(connection, ps, res);
        }

        return exerciseList;
    }

    @Override
    public List<Exercise> getByPeriod(String username, Date startDate, Date endDate) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet res = null;
        List<Exercise> exerciseList = new ArrayList<>();

        try {
            connection = ConnectionUtil.getConnection();
            // use PreparedStatement with placeholders
            String query = "select exercise_id, date, type, intensity, duration, burn_calories ";
            query += "from exercise where user_id = (select user_id from user where username = ?) ";
            query += "and Date between ? and ?";
            ps = connection.prepareStatement(query);
            // set parameters with corresponding methods
            ps.setString(1, username);
            ps.setDate(2, startDate);
            ps.setDate(3, endDate);
            // execute the query
            res = ps.executeQuery();
            while (res.next()) {
                // get the data from each column
                int id = res.getInt("exercise_id");
                Date date = res.getDate("date");
                String type = res.getString("type");
                String intensity = res.getString("intensity");
                int duration = res.getInt("duration");
                int burnCalories = res.getInt("burn_calories");
                // create a new Exercise object with the data
                Exercise exercise = new Exercise(id, date, type, intensity, duration, burnCalories);
                // add the object to the list
                exerciseList.add(exercise);
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            ConnectionUtil.close(connection, ps, res);
        }

        return exerciseList;
    }
}
