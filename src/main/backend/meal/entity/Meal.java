package main.backend.meal.entity;

import main.backend.food.entity.Food;

import java.sql.Date;
import java.util.Map;

public class Meal {
    private int id;
    private Date date;
    private String type;
    private int totalCalories;
    private float totalProtein;
    private float totalVitamins;
    private float totalCarbs;
    private float totalOthers;

    private Map<Food, Float> foodMap; // unit nutrient

    public Meal(int id, Date date, String type) {
        this.id = id;
        this.date = date;
        this.type = type;
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

    public float getTotalProtein() {
        return totalProtein;
    }

    public void setTotalProtein(float totalProtein) {
        this.totalProtein = totalProtein;
    }

    public float getTotalVitamins() {
        return totalVitamins;
    }

    public void setTotalVitamins(float totalVitamins) {
        this.totalVitamins = totalVitamins;
    }

    public float getTotalCarbs() {
        return totalCarbs;
    }

    public void setTotalCarbs(float totalCarbs) {
        this.totalCarbs = totalCarbs;
    }

    public float getTotalOthers() {
        return totalOthers;
    }

    public void setTotalOthers(float totalOthers) {
        this.totalOthers = totalOthers;
    }

    public void setTotalCalories(int totalCalories) {
        this.totalCalories = totalCalories;
    }
}
