package main.frontend.view.meal.form.add;

import main.backend.common.Result;
import main.backend.food.IFoodController;
import main.backend.food.entity.Food;
import main.backend.food.impl.FoodController;
import main.backend.meal.entity.Meal;
import main.backend.user.entity.User;
import main.frontend.common.Director;
import main.frontend.custom.dropdown.AutoComboBox;
import main.frontend.custom.entry.NfEntry;
import main.frontend.view.meal.form.common.MealFormPage;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MealAddForm extends MealFormPage {
    private IFoodController foodController = new FoodController();

    private void setRegex() {
        ((NfEntry) entries.get("Date")).setRegex(
                "^(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$",
                "data input format should be yyyy-mm-dd");
        ((NfEntry) entries.get("Quantity")).setRegex(
                "^\\d+(\\.\\d+)?$",
                "Quantity value must be positives number");
    }

    private boolean verifyAddFood() {
        boolean flag = true;

        flag = flag & ((NfEntry) entries.get("Quantity")).verifyInput();

        return flag;
    }

    private boolean verifySubmit() {
        boolean flag = true;

        flag = flag & ((NfEntry) entries.get("Date")).verifyInput();

        return flag;
    }

    private ActionListener handleSubmit() {
        User user = instance.getUser();

        return e -> {
            if (!verifySubmit()) return;

            Map<Food, Float> foodMap = new HashMap<>();
            for (int i=0; i<table.getRowCount(); i++) {
                Food food = (Food) table.getValueAt(i, 0);
                float quantity = (float) table.getValueAt(i, 1);

                foodMap.put(food, quantity);
            }

            NfEntry dateEntry = (NfEntry) entries.get("Date");
            AutoComboBox typeEntry = (AutoComboBox) entries.get("Type");
            Date date = Date.valueOf(dateEntry.getInput());
            String type = (String) typeEntry.getInput();
            Meal meal = new Meal(1, date, type);
            meal.setFoodMap(foodMap);

            Result res = mealController.save(meal, user);
            if (res.getCode().equals("200")) {
                JOptionPane.showMessageDialog(null, "Submission success", "Message", JOptionPane.INFORMATION_MESSAGE);
                table.clearTable();
            } else {
                JOptionPane.showMessageDialog(null, res.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        };
    }

    private ActionListener handleAddFood() {
        return e -> {
            if (!verifyAddFood()) return;

            AutoComboBox foodBox = (AutoComboBox) entries.get("Food");
            NfEntry quantityEntry = (NfEntry) entries.get("Quantity");
            Object item = foodBox.getInput();
            float quantity = Float.valueOf(quantityEntry.getInput());

            table.addData(new Object[]{item, quantity});
        };
    }

    private void getFoodList() {
        Result res = foodController.getList();
        if (res.getCode().equals("200")) {
            List<Food> foodList = (List<Food>) res.getData();
            AutoComboBox autoComboBox = (AutoComboBox) entries.get("Food");
            autoComboBox.setItemList(foodList);
        } else {
            JOptionPane.showMessageDialog(null, res.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected void mount(JPanel content) {
        getFoodList();
        setRegex();

        buttons.get("Back").addActionListener(handleBack());
        buttons.get("Submit").addActionListener(handleSubmit());
        buttons.get("Add").addActionListener(handleAddFood());
    }

    @Override
    public String showContent(JPanel content) {
        MealAddBuilder builder = new MealAddBuilder(content);
        Director director = new Director(builder);

        director.constructPage("Meal Form");

        buttons = builder.getButtons();
        entries = builder.getEntries();
        table = builder.getTable();

        mount(content);

        return "Meal Detail Form";
    }
}
