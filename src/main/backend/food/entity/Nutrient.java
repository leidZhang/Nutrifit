package main.backend.food.entity;

public class Nutrient {
    private String name;
    private String description;
    private String unit;

    public Nutrient(String name, String unit) {
        this.name = name;
        this.unit = unit;
    }

    public Nutrient(String name, String unit, String description) {
        this(name, unit);
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public String getDescription() {
        return description;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
