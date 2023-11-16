package main.frontend.view.meal;

import main.backend.common.Result;
import main.backend.food.IFoodController;
import main.backend.food.entity.Food;
import main.backend.food.impl.FoodController;
import main.frontend.custom.dropdown.AutoComboBox;
import main.frontend.custom.entry.NfEntry;
import main.frontend.view.mainframe.impl.FrontEnd;
import main.frontend.common.Content;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;


public class MealPage extends Content {
    IFoodController foodController = new FoodController();
    GridBagConstraints gridBagConstraints = new GridBagConstraints();

    private void setUpTemp(JPanel content) {
        // set up button attributes
        JLabel label = new JLabel("Welcome to Meal Page"); // get login user info
        label.setForeground(Color.BLACK);
        label.setPreferredSize(new Dimension(200, 20));

        // set up GridBagLayout
        content.setLayout(new GridBagLayout());

        // set up common constraints for buttons
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST; // align: top left
        gridBagConstraints.weightx = 1.0; // allocate horizontal space
        gridBagConstraints.weighty = 1.0; // allocate vertical space
        gridBagConstraints.insets = new Insets(5, 5, 5, 5); // component border

        // add button to the first row and first column
        gridBagConstraints.gridx = 0; // column 0
        gridBagConstraints.gridy = 0; // row 0
        content.add(label, gridBagConstraints);
    }

    @Override
    public String showContent(JPanel content) {
        content.removeAll();

        setUpTemp(content);
        AutoComboBox autoComboBox = new AutoComboBox(20, 150);
        autoComboBox.setTitleField("Food");

        gridBagConstraints.gridy = 1; // row 0
        content.add(autoComboBox, gridBagConstraints);

        Result res = foodController.getList();
        if (res.getCode().equals("200")) {
            List<Food> foodList = (List<Food>) res.getData();
            autoComboBox.setItemList(foodList);
        }

        NfEntry entry = new NfEntry(20, 100);
        entry.setTitle("Test Title");
        gridBagConstraints.gridy = 2; // row 0
        content.add(entry, gridBagConstraints);

        return "Switch to Meal Page";
    }
}
