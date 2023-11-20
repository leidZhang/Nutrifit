package main.backend.food.entity;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nutrient nutrient = (Nutrient) o;
        return Objects.equals(name, nutrient.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
