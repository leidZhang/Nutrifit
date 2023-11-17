package main.backend.food;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodTempClass {
    private Map<String, List<String>> foodCategories = new HashMap<>();

    public FoodTempClass() {
        initVegetablesAndFruits();
        initProtein();
        initWholeGrain();
    }

    private void initVegetablesAndFruits() {
        List<String> vegetablesAndFruits = new ArrayList<>();
        vegetablesAndFruits.add("Vegetables and Vegetable Products");
        vegetablesAndFruits.add("Fruits and Fruit Juices");
        foodCategories.put("Vegetables and Fruits", vegetablesAndFruits);
    }

    private void initProtein() {
        List<String> protein = new ArrayList<>();
        protein.add("Dairy and Egg Products");
        protein.add("Poultry Products");
        protein.add("Sausages and Luncheon Meats");
        protein.add("Pork Products");
        protein.add("Beef Products");
        protein.add("Finfish and Shellfish Products");
        protein.add("Legumes and Legume Products");
        protein.add("Lamb, Veal and Game");
        foodCategories.put("Protein", protein);
    }

    private void initWholeGrain() {
        List<String> wholeGrain = new ArrayList<>();
        wholeGrain.add("Breakfast Cereals");
        wholeGrain.add("Cereals, Grains, and Pasta");
        foodCategories.put("Whole Grain", wholeGrain);
    }

    public String toCFGFoodGroup(String cnfFoodGroup) {
        for (Map.Entry<String, List<String>> entry : foodCategories.entrySet()) {
            String cfgGroup = entry.getKey();
            List<String> cnfGroupList = entry.getValue();

            if (cnfGroupList.contains(cnfFoodGroup)) {
                return cfgGroup;
            }
        }

        return "Other";
    }
}
