package main.frontend.view.meal.form.detail;

import main.frontend.custom.table.PaginationModel;
import main.frontend.custom.table.PaginationTable;
import main.frontend.view.meal.form.common.MealFormBuilder;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MealDetailBuilder extends MealFormBuilder {
    private PaginationTable nutrientTable;

    public MealDetailBuilder(JPanel page) {
        super(page);

        List<Object[]> rowList = new ArrayList<>();
        nutrientTable = new PaginationTable(new PaginationModel(rowList, 10));
    }

    public void buildNutritionTable() {
        constraints.gridy = gridy++;
        constraints.gridwidth = 0; // one component per row
        constraints.gridx = 0;

        nutrientTable.setModelTitle(new Object[]{"Nutrition", "Amount (g)"});
        JScrollPane scrollPane2 = new JScrollPane(nutrientTable);
        scrollPane2.setPreferredSize(new Dimension(800, 250));

        page.add(scrollPane2, constraints);
        constraints.gridy = gridy++;
        page.add(Box.createVerticalStrut(20), constraints);
        constraints.gridy = gridy++;

        Font tableFont = new Font("Arial", Font.PLAIN, 16);
        nutrientTable.setFont(tableFont);
        nutrientTable.getTableHeader().setFont(tableFont);
    }

    private void buildBottomRow() {
        int i = 0;
        constraints.gridwidth = 1; // one component per row

        constraints.gridx = i++;
        page.add(buttons.get("Back"), constraints);

        constraints.gridy = gridy++;
    }

    @Override
    public void buildMainContent() {
        buildTopRow();
        buildTable();
        buildNutritionTable();
        buildBottomRow();
    }

    public PaginationTable getNutrientTable() {
        return nutrientTable;
    }
}
