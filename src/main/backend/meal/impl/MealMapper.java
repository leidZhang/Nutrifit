package main.backend.meal.impl;

import main.backend.common.ConnectionUtil;
import main.backend.meal.IMealMapper;
import main.backend.meal.entity.Food;
import main.backend.meal.entity.Meal;
import main.backend.user.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MealMapper implements IMealMapper { // not tested yet
    @Override
    public void save(Meal meal, User user) throws SQLException {
        ResultSet res = null;
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionUtil.getConnection();
            connection.setAutoCommit(false); // disable auto commit
            // get exercise attributes
            int userID = user.getId();
            Date date = meal.getDate();
            String type = meal.getType();
            int totalCalories = meal.getTotalCalories();
            Map<Food, Float> foodMap = meal.getFoodMap();

            // use PreparedStatement with placeholders
            String query = "insert into meal(date, type, total_calories, user_id) ";
            query += "values (?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(query);
            // set parameters with corresponding methods
            ps.setDate(1, date);
            ps.setString(2, type);
            ps.setInt(3, totalCalories);
            ps.setInt(4, userID);
            // insert row
            ps.executeUpdate();

            // get the new id
            int mealID = -1;
            res = ps.getGeneratedKeys();
            if (res.next()) mealID = res.getInt(1);
            for (Map.Entry<Food, Float> entry : foodMap.entrySet()) {
                Food food = entry.getKey();
                int foodID = food.getId();
                float quantity = entry.getValue();
                // use PreparedStatement with placeholders
                query = "insert into food used(meal_id, food_id, quantity) ";
                query += "values (?, ?, ?)";
                ps = connection.prepareStatement(query);
                // set parameters with corresponding methods
                ps.setInt(1, mealID);
                ps.setInt(2, foodID);
                ps.setFloat(3, quantity);
                // insert row
                ps.executeUpdate();
            }
            connection.commit(); // commit update
        } catch (SQLException e) {
            connection.rollback(); // roll back if any exception happened
            throw new SQLException(e.getMessage());
        } finally {
            ConnectionUtil.close(connection, ps, res);
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = ConnectionUtil.getConnection();
            // use PreparedStatement with placeholders
            String query = "delete from meal where meal_id = ?";
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
    public List<Meal> getByUser(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet res = null;

        List<Meal> mealList = new ArrayList<>();
        int userId = user.getId();

        try {
            connection = ConnectionUtil.getConnection();
            // use PreparedStatement with placeholders
            String query = "select meal_id, date, type, total_calories  ";
            query += "from meal where user_id = ?";
            ps = connection.prepareStatement(query);
            // set parameters with corresponding methods
            ps.setInt(1, userId);
            // execute the query
            res = ps.executeQuery();
            while (res.next()) {
                // get the data from each column
                int mealId = res.getInt("meal_id");
                Date date = res.getDate("date");
                String type = res.getString("type");
                int totalCalories = res.getInt("total_calories");
                // create a new Exercise object with the data
                Meal meal = new Meal(mealId, date, type, totalCalories);
                // add the object to the list
                mealList.add(meal);
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            ConnectionUtil.close(connection, ps, res);
        }

        return mealList;
    }

    @Override
    public List<Meal> getByPeriod(User user, Date startDate, Date endDate) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet res = null;

        List<Meal> mealList = new ArrayList<>();
        int userId = user.getId();

        try {
            connection = ConnectionUtil.getConnection();
            // use PreparedStatement with placeholders
            String query = "select meal_id, date, type, total_calories  ";
            query += "from meal where user_id = ? and Date between ? and ?";
            ps = connection.prepareStatement(query);
            // set parameters with corresponding methods
            ps.setInt(1, userId);
            ps.setDate(2, startDate);
            ps.setDate(3, endDate);
            // execute the query
            res = ps.executeQuery();
            while (res.next()) {
                // get the data from each column
                int mealId = res.getInt("meal_id");
                Date date = res.getDate("date");
                String type = res.getString("type");
                int totalCalories = res.getInt("total_calories");
                // create a new Exercise object with the data
                Meal meal = new Meal(mealId, date, type, totalCalories);
                // add the object to the list
                mealList.add(meal);
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            ConnectionUtil.close(connection, ps, res);
        }

        return mealList;
    }
}
