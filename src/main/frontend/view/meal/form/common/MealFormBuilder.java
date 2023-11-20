package main.frontend.view.meal.form.common;

import main.frontend.common.ContentBuilder;
import main.frontend.custom.dropdown.AutoComboBox;
import main.frontend.custom.entry.NfEntry;
import main.frontend.custom.table.PaginationModel;
import main.frontend.custom.table.PaginationTable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class MealFormBuilder extends ContentBuilder {
    protected Map<String, JButton> buttons;
    protected Map<String, JComponent> entries;
    protected PaginationTable table;
    public MealFormBuilder(JPanel page) {
        super(page);

        List<Object[]> rowList = new ArrayList<>();
        table = new PaginationTable(new PaginationModel(rowList, 10));

        buttons = new LinkedHashMap<>();
        String[] buttonNames = {"Add", "Back", "Submit"};
        for (String name : buttonNames) {
            buttons.put(name, new JButton(name));
        }

        entries = new LinkedHashMap<>();
        String[] entryNames = {"Date", "Quantity"};
        for (String name : entryNames) {
            entries.put(name, new NfEntry(20, 200));
            ((NfEntry) entries.get(name)).setTitle(name);
        }

        AutoComboBox foodBox = new AutoComboBox<>(20, 200);
        foodBox.setTitleField("Food");
        entries.put("Food", foodBox);
        AutoComboBox typeBox = new AutoComboBox<>(20, 200);
        typeBox.setItemList(List.of(new String[]{"Breakfast", "Lunch", "Snack", "Dinner"}));
        typeBox.setTitleField("Food");
        entries.put("Type", typeBox);
    }

    @Override
    public void setUp() {
        constraints = new GridBagConstraints();
        page.setLayout(new GridBagLayout());
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 1;
        constraints.weighty = 0;
        constraints.gridwidth = 0; // one component per row

        for (Map.Entry<String, JButton> entry : buttons.entrySet()) {
            entry.getValue().setPreferredSize(new Dimension(150, 30));
        }
    }

    public void buildTopRow() {
        int i = 0;
        constraints.gridwidth = 1; // one component per row

        constraints.gridx = i++;
        page.add(entries.get("Date"), constraints);
        constraints.gridx = i++;
        page.add(entries.get("Type"), constraints);

        constraints.gridy = gridy++;
    }

    public void buildTable() {
        constraints.gridwidth = 0; // one component per row
        constraints.gridx = 0;
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 250));
        page.add(scrollPane, constraints);
        constraints.gridy = gridy++;
        page.add(Box.createVerticalStrut(20), constraints);
        constraints.gridy = gridy++;

        table.setModelTitle(new Object[]{"Food", "Quantity (g)"});

        Font tableFont = new Font("Arial", Font.PLAIN, 16);
        table.setFont(tableFont);
        table.getTableHeader().setFont(tableFont);
    }

    public Map<String, JButton> getButtons() {
        return buttons;
    }

    public Map<String, JComponent> getEntries() {
        return entries;
    }

    public PaginationTable getTable() {
        return table;
    }

    @Override
    public abstract void buildMainContent();
}
