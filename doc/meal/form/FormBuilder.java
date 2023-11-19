package main.frontend.view.zdump.form;

import main.backend.common.Result;
import main.backend.food.entity.Food;
import main.backend.meal.IMealController;
import main.backend.meal.entity.Meal;
import main.backend.meal.impl.MealController;
import main.backend.user.entity.User;
import main.frontend.common.ContentBuilder;
import main.frontend.custom.dropdown.AutoComboBox;
import main.frontend.custom.entry.NfEntry;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;

public class FormBuilder extends ContentBuilder {
    Form form;
    private Map<String, NfEntry> entries = new LinkedHashMap<>();
    private Map<Food, Float> foodMap;
    private JButton submitButton;
    private AutoComboBox foodBox;
    private Meal meal;
    private JTable foodTable;

    private JTable nutrientTable;
    private JButton pushButton;
    private List<Food> foodList;

    private Map<Food, Float> selectedFoodMap = new LinkedHashMap<>();
    private List<Object[]> foodRowList = new ArrayList<>();

    public FormBuilder(JPanel page) {
        super(page);
    }

    public Form getForm() {
        return form;
    }

    public Map<String, NfEntry> getEntries() {
        return entries;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public AutoComboBox getFoodBox() {
        return foodBox;
    }

    FormBuilder init(String title, int x, int y, int width, int height){
        form = new Form();
        form.setTitle(title);
        form.getContentPane().setBackground(Color.WHITE);
        form.setLocation(x,y);
        form.setSize(width,height);
        form.setLayout(new GridBagLayout());
        form.setVisible(true);
        form.setModal(false);
        constraints= new GridBagConstraints();
        constraints.gridwidth= GridBagConstraints.REMAINDER;
//        constraints.anchor = GridBagConstraints.CENTER;
//        constraints.weightx = 1;
//        constraints.weighty = 0;
        return this;
    }

    FormBuilder addEntry(String []names){
        entries.clear();
        for (int i=0; i<names.length; i++) {
            entries.put(names[i], new NfEntry(25, 400));
        }

        for (Map.Entry<String, NfEntry> entry : entries.entrySet()) {
            constraints.gridy = gridy++;
            entry.getValue().setTitle(entry.getKey());
            form.add(entry.getValue(), constraints);
        }
        return this;
    }
    FormBuilder setEditable(String str,boolean flag) {
        for (Map.Entry<String, NfEntry> entry : entries.entrySet()) {
            if (entry.getKey().equals(str)) {
                entry.getValue().setEditable(flag);
            }
        }
        return this;
    }

    FormBuilder setEntryValue(String str,String value) {
        for (Map.Entry<String, NfEntry> entry : entries.entrySet()) {
            if (entry.getKey().equals(str)) {
                entry.getValue().setEntry(value);
            }
        }
        return this;
    }

    FormBuilder addFoods(List<Food> foodList){
        this.foodList = foodList;
        //add combobox
        foodBox = new AutoComboBox(25, 150);
        foodBox.setTitleField("Food");
//        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridy = gridy++;
        form.add(foodBox, constraints);
        foodBox.setItemList(foodList);
        NfEntry entry = new NfEntry(25,150);
        entry.setTitle("quantity");
        entries.put("quantity",entry);
        constraints.gridy = gridy++;
        form.add(entry,constraints);
        return this;

    }

    FormBuilder addFoodPushButton() {
        pushButton = new JButton("add");
        pushButton.setPreferredSize(new Dimension(100, 30));
        constraints.gridy = gridy++;
        constraints.gridwidth = 1;
        form.add(pushButton, constraints);
        pushButton.addActionListener(e -> {
            if (entries.get("quantity").verifyInput()) {
                String foodName = (String) foodBox.getInput();
                float quantity = Float.parseFloat(entries.get("quantity").getInput());
                foodRowList.add(new Object[]{foodName,quantity});
                for(Food food : foodList){
                    if(food.getName().equals(foodName)){
                        selectedFoodMap.put(food,quantity);
                    }
                }
                String[] foodColumnNames = new String[]{"Food", "Quantity (g)"};
                foodTable.setModel(new DefaultTableModel(foodRowList.toArray(new Object[0][0]), foodColumnNames));

            }
        });
        return this;
    }
    FormBuilder addSubmitButton(User user) {
        submitButton = new JButton("submit");
        submitButton.setPreferredSize(new Dimension(100, 30));
        constraints.gridy = gridy+3;
        constraints.gridwidth = 1;
        form.add(submitButton, constraints);
        submitButton.addActionListener(e -> {
            if (entries.get("Date").verifyInput() && entries.get("Type").verifyInput()) {
                Meal meal = new Meal(0, Date.valueOf(entries.get("Date").getInput()), entries.get("Type").getInput());
                meal.setFoodMap(selectedFoodMap);
                IMealController controller = new MealController();
                Result res = controller.save(meal, user);
                if (res.getCode().equals("200")) {
                    JOptionPane.showMessageDialog(null, "Meal added successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, res.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return this;
    }

    FormBuilder addFoodsTable(Meal meal){
        if(meal == null){
            String[] columnNames = new String[]{"Food","Quantity (g)"};
            foodList = new ArrayList<>();
            foodTable = new JTable(foodList.toArray(new Object[0][0]), columnNames);
            JScrollPane foodSp = new JScrollPane(foodTable);
            constraints.gridy = gridy++;
            foodSp.setPreferredSize(new Dimension(400, 200));
            form.add(foodSp, constraints);
        } else {
            this.meal = meal;
            //foot table
            java.util.List<Object[]> rowList = new ArrayList<>();
            for (Map.Entry<Food, Float> entry : meal.getFoodMap().entrySet()) {
                rowList.add(new Object[]{entry.getKey().getName(), entry.getKey().getGroup(), entry.getValue()});
            }
            String[] columnNames = new String[]{"Food", "Group", "Quantity (g)"};
            String[] nutrientColumnNames = new String[]{"Nutrient","Amount"};
            foodTable = new JTable(rowList.toArray(new Object[0][0]), columnNames);
            JScrollPane foodSp = new JScrollPane(foodTable);
            constraints.gridy = gridy++;
            constraints.gridwidth = 1;
            constraints.gridheight = 1;
            constraints.weightx = 1;
            constraints.weighty = 1;
            constraints.fill = GridBagConstraints.BOTH;
            form.add(foodSp, constraints);

            // As requested in use case 2
            int calories = meal.getTotalCalories();
            float protein = meal.getTotalProtein();
            float carbs = meal.getTotalCarbs();
            float vitamin = meal.getTotalVitamins();
            float others = meal.getTotalOthers();

            Object[][] rows = new Object[][]{
                    {"Calories", calories + " kCal"},
                    {"Protein", String.format("%.4f", protein) + " g"},
                    {"Carbs", String.format("%.4f", carbs) + " g"},
                    {"Vitamin", String.format("%.6f", vitamin) + " g"},
                    {"Other", String.format("%.4f", others) + " g"},
            };
            nutrientTable = new JTable(rows, nutrientColumnNames);
            JScrollPane nutrientSp = new JScrollPane(nutrientTable);
            nutrientSp.setSize(new Dimension(300, 200));
            constraints.gridy = gridy++;
            constraints.gridwidth = 1;
            constraints.gridheight = 1;
            constraints.weightx = 1;
            constraints.weighty = 1;
            constraints.fill = GridBagConstraints.BOTH;
            form.add(nutrientSp, constraints);
        }
        return this;
    }


    @Override
    public void buildMainContent() {

    }

    @Override
    public void setUp() {

    }
}
