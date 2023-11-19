package main.frontend.view.zdump.visualization;

import main.backend.food.entity.Food;
import main.backend.food.entity.Nutrient;
import main.backend.meal.entity.Meal;
import main.frontend.common.ContentBuilder;

import java.util.*;

public class VisualDirector {
    VisualBuilder builder;

    public VisualDirector(ContentBuilder builder) {
        this.builder = (VisualBuilder) builder;
    }
    public void createPeriodDashBoard(List<Meal> meals){
        HashMap<String, Float> topNutrientMap = new HashMap<>();
        HashMap<String, Float> anotherNutrientMap = new HashMap<>();
        HashMap<String, Float> nutrientMap = new HashMap<>();
        HashMap<String, Float> typeMap = new HashMap<>();
        HashMap<String, Float> foodMap = new HashMap<>();
        for (Meal meal:meals){
            for (Map.Entry<Food, Float> entry :  meal.getFoodMap().entrySet()) {
                Food food = entry.getKey();
                Float foodQuantity = entry.getValue();
                for (Map.Entry<Nutrient, Float> nutrientEntry : food.getNutrientFloatMap().entrySet()) {
                    Nutrient nutrient = nutrientEntry.getKey();
                    Float nutrientQuantity = nutrientEntry.getValue();
                    if(!nutrientMap.containsKey(nutrient.getName())){
                        nutrientMap.put(nutrient.getName(),nutrientQuantity);
                    }else {
                        nutrientMap.put(nutrient.getName(),nutrientMap.get(nutrient.getName())+nutrientQuantity);
                    }
                }
                if(!foodMap.containsKey(food.getName())){
                    foodMap.put(food.getName(),foodQuantity);
                }else {
                    foodMap.put(food.getName(),foodMap.get(food.getName())+foodQuantity);
                }
            }
            if(!typeMap.containsKey(meal.getType())){
                typeMap.put(meal.getType(),Float.valueOf(1));
            }else {
                typeMap.put(meal.getType(),typeMap.get(meal.getType())+1);
            }
        }

        //value-sort
        List<Map.Entry<String, Float>> list = new ArrayList<Map.Entry<String, Float>>(nutrientMap.entrySet());
        //list.sort()
        list.sort(new Comparator<Map.Entry<String, Float>>() {
            @Override
            public int compare(Map.Entry<String,Float> o1, Map.Entry<String, Float> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        for(int i=0;i<list.size();i++){
            if(i < 5){
                topNutrientMap.put(list.get(i).getKey(),list.get(i).getValue());
                System.out.println(list.get(i).getKey()+":"+list.get(i).getValue());
            }else{
                anotherNutrientMap.put(list.get(i).getKey(),list.get(i).getValue());
                System.out.println(list.get(i).getKey()+":"+list.get(i).getValue());
            }
        }
        builder.initWidget("Visualization",500,300,1200,550)
                .createChart(typeMap,"Type")
                .createChart(foodMap,"Food")
                .createChart(topNutrientMap,"Top nutrient")
                .createChart(anotherNutrientMap,"Another nutrient")
                .showChart();
    }
}
