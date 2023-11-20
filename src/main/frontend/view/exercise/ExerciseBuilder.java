package main.frontend.view.exercise;

import main.frontend.common.ContentBuilder;
import main.frontend.custom.dropdown.AutoComboBox;
import main.frontend.custom.entry.NfEntry;
import main.frontend.custom.table.PaginationModel;
import main.frontend.custom.table.PaginationTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

//未添加显示页数和页数跳转部分
//未添加visualization部分
//需要更改：将可视化放在界面中，点击details按钮浏览logtable表单
//展示tdee放在home page，可以和选择日期计算可减去多少脂肪的模块放一起
//需要精简代码和美化设计

public class ExerciseBuilder extends ContentBuilder {
    private final int PAGE_SIZE = 10; // you decide the page size
    // private GridBagConstraints constraints = new GridBagConstraints(); // parent class has an GridBagConstraint
    private PaginationTable exerciseLogTable;
    private JButton nextButton;
    private JButton prevButton;
    private JButton saveButton;
    private JPopupMenu popupMenu;
    private JMenuItem deleteItem;

    private Map<String, JComponent> entries = new HashMap<>();

    public ExerciseBuilder(JPanel page) {
        super(page);
        constraints = new GridBagConstraints();

        List<Object[]> rowList = new ArrayList<>();
        exerciseLogTable = new PaginationTable(new PaginationModel(rowList, PAGE_SIZE));
        prevButton = new JButton("Prev Page");
        nextButton = new JButton("Next Page");
        saveButton = new JButton("Save");
        popupMenu = new JPopupMenu();
        deleteItem = new JMenuItem("Delete");
        popupMenu.add(deleteItem);
    }

    @Override
    public void setUp() {
        page.setLayout(new GridBagLayout());
        constraints.gridx = 0;
        constraints.gridy = gridy++;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.NONE;
        constraints.insets = new Insets(5, 5, 5, 5);
        Dimension buttonSize = new Dimension(100, 30);
        nextButton.setPreferredSize(buttonSize);
        prevButton.setPreferredSize(buttonSize);
        saveButton.setPreferredSize(buttonSize);
    }

    @Override
    public void buildMainContent() {
        showExerciseLogTable();
        addSaveExerciseModule();
    }

    public void showExerciseLogTable() {
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

        exerciseLogTable.setModelTitle(new Object[]{"#", "Type", "Date", "Duration", "Intensity", "Calories"});
        // hide exercise id
        exerciseLogTable.getColumnModel().getColumn(0).setMinWidth(0);
        exerciseLogTable.getColumnModel().getColumn(0).setMaxWidth(0);
        exerciseLogTable.setComponentPopupMenu(popupMenu);

        constraints.gridx = 0;
        constraints.gridy = gridy++;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 3.0;
        constraints.insets = new Insets(5, 20, 5, 20);
        page.add(new JScrollPane(exerciseLogTable), constraints);

        addLogTableButtons();

    }

    public void addLogTableButtons() {
        //add previous page button
        constraints.gridx = 0;
        constraints.gridy = gridy;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.insets = new Insets(0, 20, 10, 0);
        page.add(prevButton, constraints);

        //create next button
        constraints.gridx = 1;
        constraints.gridy = gridy;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 1;
        constraints.insets = new Insets(0, 0, 10, 20);
        page.add(nextButton, constraints);

        gridy++;
    }

    public void addSaveExerciseModule() { // may need refactor in d3
        //create title
        JLabel saveModuleLabel = new JLabel("Save your new exercise here!");
        saveModuleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        constraints.gridx = 0;
        constraints.gridy = gridy++;
        constraints.gridwidth = GridBagConstraints.RELATIVE;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.weighty = 0.1;
        constraints.insets = new Insets(10, 10, 0, 10);
        page.add(saveModuleLabel, constraints);
        constraints.insets = new Insets(2, 10, 0, 10);

        // create exercise type entry
        AutoComboBox<String> exerciseTypeEntry = new AutoComboBox<>(20, 200);
        exerciseTypeEntry.setTitleField("Type");
        java.util.List<String> exerciseTypes = Arrays.asList("Walking", "Running", "Biking", "Swimming", "Others");
        exerciseTypeEntry.setItemList(exerciseTypes);
        addComponent(exerciseTypeEntry);
        entries.put("Type", exerciseTypeEntry);

        // create date entry
        NfEntry dateEntry = new NfEntry(20, 200);
        dateEntry.setTitle("Date");
        addComponent(dateEntry);
        entries.put("Date", dateEntry);

        // create duration entry
        NfEntry durationEntry = new NfEntry(20, 200);
        durationEntry.setTitle("Duration(min)");
        addComponent(durationEntry);
        entries.put("Duration(min)", durationEntry);

        // create intensity entry
        AutoComboBox<String> intensityEntry = new AutoComboBox<>(20, 200);
        intensityEntry.setTitleField("Intensity");
        java.util.List<String> intensities = Arrays.asList("Very High", "High", "Medium", "Low");
        intensityEntry.setItemList(intensities);
        addComponent(intensityEntry);
        entries.put("Intensity", intensityEntry);

        // add save button
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.fill = GridBagConstraints.RELATIVE;
        constraints.gridy = gridy;
        constraints.gridwidth = 1;
        constraints.weightx = 0.5;
        constraints.weighty = 0.1;
        page.add(saveButton, constraints);
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

    public PaginationTable getTable() {
        return exerciseLogTable;
    }

    public Map<String, JComponent> getEntries() {
        return entries;
    }

    public void setTableButton(ActionListener prevListener, ActionListener nextListener, ActionListener submitListener) {
        prevButton.addActionListener(prevListener);
        nextButton.addActionListener(nextListener);
        saveButton.addActionListener(submitListener);
    }

    public void setDeleteMenuItem(ActionListener deleteListener) {
        deleteItem.addActionListener(deleteListener);
    }
}

