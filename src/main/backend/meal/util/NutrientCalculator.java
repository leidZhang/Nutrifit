package main.backend.meal.util;

import main.backend.food.entity.Food;
import main.backend.food.entity.Nutrient;

import java.util.Map;

public class NutrientCalculator { // use this util after processed by MealUtil
    public static final String VITAMIN_REGEX = "^VITAMIN.*";
    public static final String CARBOHYDRATE_REGEX = "^CARBOHYDRATE.*";
    public static final String PROTEIN_REGEX = "^PROTEIN.*";

    private float getMatchedNutrient(Map<Nutrient, Float> nutrientMap, String regex) {
        float res = 0;
        for (Map.Entry<Nutrient, Float> entry : nutrientMap.entrySet()) {
            String description = entry.getKey().getDescription();

            if (description.matches(regex)) {
                res += entry.getValue();
            }
        }

        return res;
    }

    public float getTotalOthers(Map<Nutrient, Float> nutrientMap) {
        float res = 0;
        for (Map.Entry<Nutrient, Float> entry : nutrientMap.entrySet()) {
            String description = entry.getKey().getDescription();

            if (!description.matches(VITAMIN_REGEX) && !description.matches(CARBOHYDRATE_REGEX) && !description.matches(PROTEIN_REGEX)) {
                res += entry.getValue();
            }
        }

        return res;
    }

    public float getTotalVitamin(Map<Nutrient, Float> nutrientMap) {
        return getMatchedNutrient(nutrientMap, VITAMIN_REGEX);
    }

    public float getTotalCarbs(Map<Nutrient, Float> nutrientMap) {
        return getMatchedNutrient(nutrientMap, CARBOHYDRATE_REGEX);
    }

    public float getTotalProtein(Map<Nutrient, Float> nutrientMap) {
        return getMatchedNutrient(nutrientMap, PROTEIN_REGEX);
    }
}
