package main.backend.meal.impl;

import main.backend.common.ConnectionUtil;
import main.backend.meal.IFoodMapper;
import main.backend.meal.entity.Food;
import main.backend.meal.entity.Nutrient;
import main.backend.user.entity.User;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class FoodMapper implements IFoodMapper {
    @Override
    public Map<Nutrient, Float> getUnitNutrientValue(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet res = null;
        Map<Nutrient, Float> nutrientFloatMap = new HashMap<>();

        try {
            connection = ConnectionUtil.getConnection();

            // get nutrient values from Nutrient amount
            String query = "select nt.NutrientName, nt.NutrientValue, nt.NutrientUnit ";
            query += "from `food name` as fn inner join (";
            query += "select na.FoodID, nn.NutrientID, nn.NutrientName, na.NutrientValue, nn.NutrientUnit ";
            query += "from `nutrient name` as nn inner join `nutrient amount` as na ";
            query += "on nn.NutrientID = na.NutrientNameID) as nt ";
            query += "on fn.FoodID = nt.FoodID and fn.FoodID = ?";
            ps = connection.prepareStatement(query);
            // set parameters with corresponding methods
            ps.setInt(1, id);
            // execute the query
            res = ps.executeQuery();
            while (res.next()) {
                String nutrientName = res.getString("NutrientName");
                float unitValue = res.getFloat("NutrientValue");
                String nutrientUnit = res.getString("NutrientUnit");
                Nutrient nutrient = new Nutrient(nutrientName, nutrientUnit);
                nutrientFloatMap.put(nutrient, unitValue);
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            ConnectionUtil.close(connection, ps, res);
        }

        return nutrientFloatMap;
    }
}


