package main.backend.meal.util;

import main.backend.food.IFoodService;
import main.backend.food.entity.Food;
import main.backend.food.entity.Nutrient;
import main.backend.food.impl.FoodService;

import java.util.*;

public class MealUtil {
    private final String UNIT_MG = "mg";
    private final String UNIT_UG = "μg";
    private final String UNIT_G = "g";

    private IFoodService foodService = new FoodService();

    public Map<Nutrient, Float> getTotalNutrient(Map<Food, Float> foodMap) {
        Map<Nutrient, Float> totalNutrientMap = new HashMap<>();

        for (Map.Entry<Food, Float> foodEntry : foodMap.entrySet()) {
            Food food = foodEntry.getKey();
            Float foodWeight = foodEntry.getValue();

            Map<Nutrient, Float> nutrientMap = food.getNutrientFloatMap();
            for (Map.Entry<Nutrient, Float> entry : nutrientMap.entrySet()) {
                Nutrient nutrient = entry.getKey();
                Float value = entry.getValue();

                if (!totalNutrientMap.containsKey(nutrient)) {
                    totalNutrientMap.put(nutrient, value * foodWeight / 100); // unit value is the value per 100g
                } else {
                    totalNutrientMap.put(nutrient, value * foodWeight / 100 + totalNutrientMap.get(nutrient));
                }
            }
        }

        return totalNutrientMap;
    }

    public Map<Nutrient, Float> getDailyNutrient(Map<Nutrient, Float> totalNutrientMap, int days) {
        Map<Nutrient, Float> res = new HashMap<>();

        for (Map.Entry<Nutrient, Float> entry : totalNutrientMap.entrySet()) {
            Nutrient nutrient = entry.getKey();
            Float dailyValue = entry.getValue() / days;

            res.put(nutrient, dailyValue);
        }

        return res;
    }

    public List<Map.Entry<Nutrient, Float>> getNutrientList(Map<Nutrient, Float> totalNutrientMap) {
        List<Map.Entry<Nutrient, Float>> nutrients = new ArrayList<>();

        for (Map.Entry<Nutrient, Float> entry : totalNutrientMap.entrySet()) {
            Nutrient nutrient = entry.getKey();
            String unit = nutrient.getUnit();
            double nutrientValue = entry.getValue();

            if (unit.equals(UNIT_MG)) {
                nutrientValue = nutrientValue * Math.pow(10, -3);
                totalNutrientMap.put(nutrient, (float) nutrientValue);
            } else if (unit.equals(UNIT_UG)) {
                nutrientValue = nutrientValue * Math.pow(10, -6);
                totalNutrientMap.put(nutrient, (float) nutrientValue);
            }

            nutrients.add(entry);
        }

        return nutrients;
    }

    public List<Map.Entry<Nutrient, Float>> sortNutrientList(List<Map.Entry<Nutrient, Float>> nutrients, int topNum) {
        List<Map.Entry<Nutrient, Float>> res = new ArrayList<>();

        // define a max heap and push items into it
        PriorityQueue<Map.Entry<Nutrient, Float>> pq = new PriorityQueue<>(new Comparator<Map.Entry<Nutrient, Float>>() {
            @Override
            public int compare(Map.Entry<Nutrient, Float> o1, Map.Entry<Nutrient, Float> o2) {
                return Float.compare(o2.getValue(), o1.getValue());
            }
        });
        for (Map.Entry<Nutrient, Float> nutrient : nutrients) {
            pq.offer(nutrient);
        }

        // top ? items
        for (int i=0; i<topNum; i++) {
            if (pq.isEmpty()) break;
            res.add(pq.poll());
        }
        // rest items
        float otherValue = 0;
        Nutrient otherNutrient = new Nutrient("Other", UNIT_G);
        while (!pq.isEmpty()) {
            otherValue += pq.poll().getValue();
        }
        res.add(new AbstractMap.SimpleEntry<>(otherNutrient, otherValue));

        return res;
    }
}
