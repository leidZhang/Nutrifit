package main.backend.meal.util;

import main.backend.food.entity.Food;
import main.backend.food.entity.Nutrient;

import java.util.*;

public class MealProcessor {
    private final String UNIT_MG = "mg";
    private final String UNIT_UG = "μg";
    private final String UNIT_G = "g";

    public Map<Food, Float> mergeFoodMap(Map<Food, Float> target, Map<Food, Float> source) {
        for (Map.Entry<Food, Float> entry : source.entrySet()) {
            Food food = entry.getKey();
            float value = entry.getValue();

            target.put(food, value + target.getOrDefault(food, 0f));
        }

        return target;
    }

    private void calNutrient(Food food, Float foodWeight, Map<Nutrient, Float> totalNutrientMap) {
        Map<Nutrient, Float> nutrientMap = food.getNutrientFloatMap();
        for (Map.Entry<Nutrient, Float> entry : nutrientMap.entrySet()) {
            Nutrient nutrient = entry.getKey();
            Float value = entry.getValue();

            totalNutrientMap.put(nutrient,  value * foodWeight / 100 + totalNutrientMap.getOrDefault(nutrient, 0f));
        }
    }

    public Map<Nutrient, Float> getSortedDailyNutrient(Map<Food, Float> foodMap, int days) {
        Map<Nutrient, Float> rawMap = getTotalNutrient(foodMap); // get raw data

        Map<Nutrient, Float> dailyMap = getDailyNutrient(rawMap, days); // get daily value
        List<Map.Entry<Nutrient, Float>> processedList = getNutrientList(dailyMap); // convert unit to g
        List<Map.Entry<Nutrient, Float>> sortedList = sortNutrientList(processedList, 10); // assume we need top 10
        Map<Nutrient, Float> sortedMap = entryListToMap(sortedList);

        return sortedMap;
    }

    public Map<Nutrient, Float> getNutrientMap(Map<Food, Float> foodMap) {
        Map<Nutrient, Float> rawMap = getTotalNutrient(foodMap); // get raw data

        List<Map.Entry<Nutrient, Float>> processedList = getNutrientList(rawMap); // convert unit to g
        Map<Nutrient, Float> nutrientMap = entryListToMap(processedList);

        return nutrientMap;
    }

    public <T> Map<T, Float> toPercentageMap(Map<? extends T, Float> weightMap) {
        float total = 0; // cal total nutrient weight
        for (Map.Entry<? extends T, Float> entry : weightMap.entrySet()) {
            total += entry.getValue();
        }

        Map<T, Float> percentageMap = new LinkedHashMap<>();
        for (Map.Entry<? extends T, Float> entry : weightMap.entrySet()) {
            T key = entry.getKey();
            float val = entry.getValue();
            percentageMap.put(key, (val / total) * 100);
        }

        return percentageMap;
    }

    private Map<Nutrient, Float> getTotalNutrient(Map<Food, Float> foodMap) {
        Map<Nutrient, Float> totalNutrientMap = new HashMap<>();

        for (Map.Entry<Food, Float> foodEntry : foodMap.entrySet()) {
            Food food = foodEntry.getKey();
            Float foodWeight = foodEntry.getValue();

            calNutrient(food, foodWeight, totalNutrientMap);
        }

        return totalNutrientMap;
    }

    private Map<Nutrient, Float> getDailyNutrient(Map<Nutrient, Float> totalNutrientMap, int days) {
        Map<Nutrient, Float> res = new HashMap<>();

        for (Map.Entry<Nutrient, Float> entry : totalNutrientMap.entrySet()) {
            Nutrient nutrient = entry.getKey();
            Float dailyValue = entry.getValue() / days;

            res.put(nutrient, dailyValue);
        }

        return res;
    }

    private List<Map.Entry<Nutrient, Float>> getNutrientList(Map<Nutrient, Float> totalNutrientMap) {
        List<Map.Entry<Nutrient, Float>> nutrients = new ArrayList<>();

        for (Map.Entry<Nutrient, Float> entry : totalNutrientMap.entrySet()) {
            Nutrient nutrient = entry.getKey();
            String unit = nutrient.getUnit();
            double nutrientValue = entry.getValue();

            if (unit.equals(UNIT_MG)) {
                nutrient.setUnit(UNIT_G);
                nutrientValue = nutrientValue * Math.pow(10, -3);
                totalNutrientMap.put(nutrient, (float) nutrientValue);
            } else if (unit.equals(UNIT_UG)) {
                nutrient.setUnit(UNIT_G);
                nutrientValue = nutrientValue * Math.pow(10, -6);
                totalNutrientMap.put(nutrient, (float) nutrientValue);
            }

            nutrients.add(entry);
        }

        return nutrients;
    }

    private Map<Nutrient, Float> entryListToMap(List<Map.Entry<Nutrient, Float>> list) {
        Map<Nutrient, Float> processedMap = new LinkedHashMap<>();
        for (Map.Entry<Nutrient, Float> entry : list) {
            processedMap.put(entry.getKey(), entry.getValue());
        }

        return processedMap;
    }

    private PriorityQueue<Map.Entry<Nutrient, Float>> getMaxHeap(List<Map.Entry<Nutrient, Float>> nutrients) {
        // define a max heap and push items into it
        PriorityQueue<Map.Entry<Nutrient, Float>> pq = new PriorityQueue<>((o1, o2) -> Float.compare(o2.getValue(), o1.getValue()));
        for (Map.Entry<Nutrient, Float> nutrient : nutrients) {
            pq.offer(nutrient);
        }

        return pq;
    }

    private List<Map.Entry<Nutrient, Float>> sortNutrientList(List<Map.Entry<Nutrient, Float>> nutrients, int topNum) {
        List<Map.Entry<Nutrient, Float>> res = new ArrayList<>();
        PriorityQueue<Map.Entry<Nutrient, Float>> pq = getMaxHeap( nutrients);

        // top ? items
        for (int i=0; i<topNum; i++) {
            if (pq.isEmpty()) break;
            res.add(pq.poll());
        }
        // rest items
        float otherValue = 0;
        Nutrient otherNutrient = new Nutrient("Other nutritions", UNIT_G);
        while (!pq.isEmpty()) {
            otherValue += pq.poll().getValue();
        }
        res.add(new AbstractMap.SimpleEntry<>(otherNutrient, otherValue));

        return res;
    }
}
