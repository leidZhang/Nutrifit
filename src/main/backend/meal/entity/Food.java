package main.backend.meal.entity;

import java.util.Map;

public class Food {
    private int id;
    private String name;

    private Map<Nutrient, Float> nutrientFloatMap;

    public Food(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<Nutrient, Float> getNutrientFloatMap() {
        return nutrientFloatMap;
    }

    public void setNutrientFloatMap(Map<Nutrient, Float> nutrientFloatMap) {
        this.nutrientFloatMap = nutrientFloatMap;
    }
}
