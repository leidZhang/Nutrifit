package main.frontend.view.zdump.form;

import main.backend.common.Result;
import main.backend.food.entity.Food;
import main.backend.food.impl.FoodController;
import main.backend.meal.entity.Meal;
import main.backend.user.entity.User;
import main.frontend.common.ContentBuilder;
import main.frontend.custom.entry.NfEntry;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class FormDirector {
    FormBuilder builder;
    public FormDirector(ContentBuilder builder) {
        this.builder = (FormBuilder) builder;
    }
    public void createAddForm(User user){
        List<Food> foodList = null;
        Result res = new FoodController().getList();
        if (res.getCode().equals("200")) {
            foodList = (List<Food>) res.getData();
        }
          builder.init("add meal",500,300,600,750)
                  .addEntry(new String[]{"Type", "Date"})
                  .addFoodsTable(null)
                  .addFoods(foodList)
                  .addFoodPushButton()
                  .addSubmitButton(user);
          builder.getForm().revalidate();
          builder.getForm().repaint();
    }
    public Meal getAddFormInfo(){
        Map<String, NfEntry> entries = builder.getEntries();
        String type = entries.get("Type").getInput();
        Date date = Date.valueOf(entries.get("Date").getInput());
        int totalCalories = Integer.valueOf(entries.get("TotalCalories").getInput());

        // create new Meal
        Meal meal = new Meal(0,date,type);
        meal.setTotalCalories(totalCalories);
        return meal;


    }

    public void createInfoForm(Meal meal){

        builder.init("info",500,300,800,750)
                .addEntry(new String[]{"Type", "Date","TotalCalories"})
                .setEditable("Type",false)
                .setEditable("Date",false)
                .setEditable("TotalCalories",false)
                .setEntryValue("Type",meal.getType())
                .setEntryValue("Date",meal.getDate().toString())
                .setEntryValue("TotalCalories",String.valueOf(meal.getTotalCalories()))
                .addFoodsTable(meal);
        builder.getForm().revalidate();
        builder.getForm().repaint();
    }

}
