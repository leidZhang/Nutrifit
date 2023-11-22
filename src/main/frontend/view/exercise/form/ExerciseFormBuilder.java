package main.frontend.view.exercise.form;

import main.frontend.common.ContentBuilder;
import main.frontend.custom.dropdown.AutoComboBox;
import main.frontend.custom.entry.NfEntry;
import main.frontend.custom.table.PaginationModel;
import main.frontend.custom.table.PaginationTable;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;


public class ExerciseFormBuilder extends ContentBuilder {
    private final int PAGE_SIZE = 10;
    private final int[] ENTRY_DIMENSION = {20, 150};
    private Map<String, JButton> buttons;
    private Map<String, JComponent> entries;
    private PaginationTable table;
    private JPopupMenu popupMenu;
    private JMenuItem deleteItem;

    public ExerciseFormBuilder(JPanel page) {
        super(page);

        List<Object[]> rowList = new ArrayList<>();
        table = new PaginationTable(new PaginationModel(rowList, PAGE_SIZE));

        String[] buttonNames = {"Prev Page", "Next Page", "Save", "Delete", "Back"};
        buttons = new LinkedHashMap<>();
        for (String name : buttonNames) {
            buttons.put(name, new JButton(name));
        }

        String[] entryNames = {"Date", "Duration(min)"};
        entries = new LinkedHashMap<>();
        for (String name : entryNames) {
            NfEntry entry = new NfEntry(ENTRY_DIMENSION[0], ENTRY_DIMENSION[1]);
            entry.setTitle(name);
            entries.put(name, entry);
        }

        AutoComboBox<String> typeBox = new AutoComboBox<>(20, 200);
        typeBox.setTitleField("Type");
        typeBox.setItemList(List.of(new String[]{"Walking", "Running", "Biking", "Swimming", "Others"}));
        entries.put("Type", typeBox);

        AutoComboBox<String> intensityBox = new AutoComboBox<>(20, 200);
        intensityBox.setTitleField("Intensity");
        intensityBox.setItemList(List.of(new String[]{"Very High", "High", "Medium", "Low"}));
        entries.put("Intensity", intensityBox);

        popupMenu = new JPopupMenu();
        deleteItem = new JMenuItem("Delete");
        popupMenu.add(deleteItem);
    }

    @Override
    public void setUp() {
        constraints = new GridBagConstraints();
        page.setLayout(new GridBagLayout());
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.weightx = 1;
        constraints.weighty = 0;
        constraints.gridwidth = 0;
    }


    @Override
    public void buildMainContent() {
        buildExerciseTable();
        buildButtons();
        buildSaveNewRecordRow();
    }

    public void buildExerciseTable() {
        //create log table title
        JLabel logTableLabel = new JLabel("View your exercise log here!");
        logTableLabel.setFont(new Font("Arial", Font.BOLD, 18));
        //create delete exercise reminder for log table
        JLabel deleteLabel = new JLabel("Right-click to delete...");
        deleteLabel.setFont(new Font("Arial", Font.BOLD, 13));
        constraints.gridx = 0;
        constraints.gridy = gridy++;
        constraints.gridwidth = GridBagConstraints.RELATIVE;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.weighty = 0.1;
        constraints.insets = new Insets(0, 10, 0, 10);
        page.add(logTableLabel, constraints);
        constraints.gridy = gridy++;
        page.add(deleteLabel, constraints);

        table.setModelTitle(new Object[]{"#", "Type", "Date", "Duration", "Intensity", "Calories"});
        // hide exercise id
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.setComponentPopupMenu(popupMenu);

        constraints.gridx = 0;
        constraints.gridy = gridy++;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.insets = new Insets(5, 20, 5, 20);
        page.add(new JScrollPane(table), constraints);
    }

    public void buildButtons() {
        //add previous page button
        constraints.gridx = 0;
        constraints.gridy = gridy;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.insets = new Insets(0, 20, 10, 0);
        page.add(buttons.get("Prev Page"), constraints);

        //add back button
        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        page.add(buttons.get("Back"), constraints);

        //add next page button
        constraints.gridx = 2;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.weightx = 1;
        constraints.insets = new Insets(0, 0, 10, 20);
        page.add(buttons.get("Next Page"), constraints);

        gridy++;
    }

    public void buildSaveNewRecordRow() { // may need refactor in d3
        //create title
        JLabel saveModuleLabel = new JLabel("Add and save your new exercise record here!");
        saveModuleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        constraints.gridx = 0;
        constraints.gridy = gridy++;
        constraints.gridwidth = GridBagConstraints.RELATIVE;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.weighty = 0.1;
        constraints.insets = new Insets(10, 10, 0, 10);
        page.add(saveModuleLabel, constraints);

        addComponent(entries.get("Type"));
        addComponent(entries.get("Date"));
        addComponent(entries.get("Duration(min)"));
        addComponent(entries.get("Intensity"));

        // add save button
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.fill = GridBagConstraints.RELATIVE;
        constraints.gridy = gridy;
        constraints.gridwidth = 1;
        constraints.weightx = 0.5;
        constraints.weighty = 0.1;
        page.add(buttons.get("Save"), constraints);
    }

    private void addComponent(Component component) {
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.gridy = gridy;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.RELATIVE;
        constraints.weightx = 0.5;
        constraints.weighty = 0.1;
        page.add(component, constraints);
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

    public JMenuItem getDeleteItem() { return deleteItem; }
}

