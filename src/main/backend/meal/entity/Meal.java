package main.backend.meal.entity;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class Meal {
    private int id;
    private Date date;
    private String type;
    private int totalCalories;

    private Map<Food, Float> foodMap; // unit nutrient

    public Meal(int id, Date date, String type, int totalCalories) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.totalCalories = totalCalories;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public int getTotalCalories() {
        return totalCalories;
    }

    public Map<Food, Float> getFoodMap() {
        return foodMap;
    }

    public void setFoodMap(Map<Food, Float> foodMap) {
        this.foodMap = foodMap;
    }
}
