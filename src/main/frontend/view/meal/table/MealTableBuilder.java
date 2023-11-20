package main.frontend.view.meal.table;

import main.frontend.common.ContentBuilder;
import main.frontend.custom.entry.NfEntry;
import main.frontend.custom.table.PaginationModel;
import main.frontend.custom.table.PaginationTable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MealTableBuilder extends ContentBuilder {
    private final int PAGE_SIZE = 10;
    private final int DATE_ENTRY_HEIGHT = 20;
    private final int DATE_ENTRY_WIDTH = 200;
    private PaginationTable table;
    private Map<String, JButton> buttonMap;
    private Map<String, NfEntry> entries;

    public MealTableBuilder(JPanel page) {
        super(page);

        List<Object[]> rowList = new ArrayList<>();
        table = new PaginationTable(new PaginationModel(rowList, PAGE_SIZE));

        String[] buttonNames = {"Prev Page", "Next Page", "Back", "Apply", "Reset", "New Record"};
        buttonMap = new LinkedHashMap<>();
        for (String name : buttonNames) {
            buttonMap.put(name, new JButton(name));
        }

        String[] entryNames = {"Start Date", "End Date"};
        entries = Map.of(
                entryNames[0], new NfEntry(DATE_ENTRY_HEIGHT, DATE_ENTRY_WIDTH),
                entryNames[1], new NfEntry(DATE_ENTRY_HEIGHT, DATE_ENTRY_WIDTH)
        );
    }

    @Override
    public void setUp() {
        constraints = new GridBagConstraints();
        page.setLayout(new GridBagLayout());
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 1;
        constraints.weighty = 0;
        constraints.gridwidth = 0; // one component per row
    }

    private void buildDateRow() {
        constraints.gridwidth = 1; // one component per row

        int i = 0;
        for (Map.Entry<String, NfEntry> entry : entries.entrySet()) {
            entry.getValue().setTitle(entry.getKey());
            constraints.gridx = i++;
            page.add(entry.getValue(), constraints);
        }

        constraints.gridx = i++;
        page.add(buttonMap.get("Apply"), constraints);
        constraints.gridx = i++;
        page.add(buttonMap.get("Reset"), constraints);

        constraints.gridy = gridy++;
    }

    private void buildTable() {
        constraints.gridwidth = 0; // one component per row
        constraints.gridx = 0;
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 500));
        page.add(scrollPane, constraints);
        constraints.gridy = gridy++;
        page.add(Box.createVerticalStrut(20), constraints);
        constraints.gridy = gridy++;

        table.setModelTitle(new Object[]{"#", "Type", "Date", "Total Calories"});
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);

        Font tableFont = new Font("Arial", Font.PLAIN, 16);
        table.setFont(tableFont);
        table.getTableHeader().setFont(tableFont);
    }

    private void buildTableButtons() {
        // Add some empty space after the title
        constraints.gridwidth = 1; // one component per row

        int i = 0;
        constraints.gridx = i++;
        page.add(buttonMap.get("Prev Page"), constraints);
        constraints.gridx = i++;
        page.add(buttonMap.get("Back"), constraints);
        constraints.gridx = i++;
        page.add(buttonMap.get("New Record"), constraints);
        constraints.gridx = i++;
        page.add(buttonMap.get("Next Page"), constraints);

        constraints.gridy = gridy++;
    }

    @Override
    public void buildMainContent() {
        buildDateRow();
        buildTable();
        buildTableButtons();
    }

    public PaginationTable getTable() {
        return table;
    }

    public Map<String, NfEntry> getEntries() {
        return entries;
    }

    public Map<String, JButton> getButtonMap() {
        return buttonMap;
    }
}
