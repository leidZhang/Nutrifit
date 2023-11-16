package main.backend.food.entity;

public class Nutrient {
    private String name;
    private String unit;

    public Nutrient(String name, String unit) {
        this.name = name;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }
}
