package main.frontend.view.meal.form.add;

import main.frontend.custom.table.PaginationTable;
import main.frontend.view.meal.form.common.MealFormBuilder;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MealAddBuilder extends MealFormBuilder {
    public MealAddBuilder(JPanel page) {
        super(page);
    }

    private void buildBottomRow() {
        int i = 0;
        constraints.gridwidth = 1; // one component per row

        constraints.gridx = i++;
        page.add(buttons.get("Back"), constraints);
        constraints.gridx = i++;
        page.add(buttons.get("Submit"), constraints);

        constraints.gridy = gridy++;
    }

    private void buildDropDownRow() {
        int i = 0;
        constraints.gridwidth = 1; // one component per row
        constraints.anchor = GridBagConstraints.CENTER;

        constraints.gridx = i++;
        page.add(entries.get("Food"), constraints);
        constraints.gridx = i++;
        page.add(entries.get("Quantity"), constraints);
        constraints.gridx = i++;
        page.add(buttons.get("Add"), constraints);
        constraints.gridx = i++;

        constraints.gridy = gridy++;
    }

    @Override
    public void buildMainContent() {
        buildTopRow();
        buildDropDownRow();
        buildTable();
        buildBottomRow();
    }


}
