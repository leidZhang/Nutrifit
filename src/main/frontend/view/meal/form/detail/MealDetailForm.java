package main.frontend.view.meal.form.detail;

import main.backend.common.Result;
import main.backend.food.entity.Food;
import main.backend.meal.IMealController;
import main.backend.meal.entity.Meal;
import main.frontend.common.ContentBuilder;
import main.frontend.common.Director;
import main.frontend.custom.dropdown.AutoComboBox;
import main.frontend.custom.entry.NfEntry;
import main.frontend.custom.table.PaginationTable;
import main.frontend.view.meal.form.common.MealFormPage;

import javax.swing.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MealDetailForm extends MealFormPage {
    private Meal meal;
    private PaginationTable nutrientTable;

    private void renderEntries() {
        Date date = meal.getDate();
        String type = meal.getType();
        ((NfEntry) entries.get("Date")).setEntry(String.valueOf(date));
        ((AutoComboBox) entries.get("Type")).setItem(type);
        ((NfEntry) entries.get("Date")).setEditable(false);
        ((AutoComboBox) entries.get("Type")).setEditable(false);
    }

    private void renderNutrientTable() {
        int calories = meal.getTotalCalories();
        float protein = meal.getTotalProtein();
        float carbs = meal.getTotalCarbs();
        float vitamin = meal.getTotalVitamins();
        float others = meal.getTotalOthers();

        Object[][] objects = new Object[][]{
                {"Calories", calories + " kCal"},
                {"Protein", String.format("%.4f", protein) + " g"},
                {"Carbs", String.format("%.4f", carbs) + " g"},
                {"Vitamin", String.format("%.6f", vitamin) + " g"},
                {"Other", String.format("%.4f", others) + " g"},
        };

        nutrientTable.setModelData(Arrays.stream(objects).toList());
    }

    private void renderFoodTable() {
        Map<Food, Float> foodMap = meal.getFoodMap();

        List<Object[]> rowList = new ArrayList<>();
        for (Map.Entry<Food, Float> entry : foodMap.entrySet()) {
            Food food = entry.getKey();
            float value = entry.getValue();

            rowList.add(new Object[]{food, value});
        }

        table.setModelData(rowList);
    }

    private void getMealDetail() {
        int id = meal.getId();
        Result res = mealController.getById(id);
        if (res.getCode().equals("200")) {
            meal = (Meal) res.getData();
        } else {
            JOptionPane.showMessageDialog(null, res.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected void mount(JPanel content) {
        buttons.get("Back").addActionListener(handleBack());

        renderEntries();
        renderFoodTable();
        renderNutrientTable();
    }

    @Override
    public String showContent(JPanel content) {
        System.out.println(meal.getId());

        MealDetailBuilder builder = new MealDetailBuilder(content);
        Director director = new Director(builder);

        director.constructPage("Meal Detail");
        buttons = builder.getButtons();
        entries = builder.getEntries();
        table = builder.getTable();
        nutrientTable = builder.getNutrientTable();

        mount(content);

        return "Meal Detail";
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }
}
