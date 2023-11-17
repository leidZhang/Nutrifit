package main.backend.food.entity;

import java.util.Map;

public class Food {
    private int id;
    private String name;
    private String group;

    private Map<Nutrient, Float> nutrientFloatMap;

    public Food(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Food(int id, String name, String group) {
        this(id, name);
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public Map<Nutrient, Float> getNutrientFloatMap() {
        return nutrientFloatMap;
    }

    public void setNutrientFloatMap(Map<Nutrient, Float> nutrientFloatMap) {
        this.nutrientFloatMap = nutrientFloatMap;
    }

    @Override
    public String toString() {
        return name;
    }
}
