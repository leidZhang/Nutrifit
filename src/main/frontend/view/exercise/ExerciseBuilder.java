package main.frontend.view.exercise;

import main.frontend.common.ContentBuilder;
import main.frontend.custom.dropdown.AutoComboBox;
import main.frontend.custom.entry.NfEntry;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;

//未添加显示页数和页数跳转部分
//未设置添加新记录后刷新表格功能
//未添加visualization部分
//需要更改：将可视化放在界面中，点击details按钮浏览logtable表单
//展示tdee放在home page，可以和选择日期计算可减去多少脂肪的模块放一起
//需要精简代码和美化设计

public class ExerciseBuilder extends ContentBuilder {
    private GridBagConstraints constraints = new GridBagConstraints();
    private JTable exerciseLogTable;
    private JButton nextButton;
    private JButton prevButton;
    private JButton saveButton;

    public ExerciseBuilder(JPanel page) {
        super(page);
        page.setLayout(new GridBagLayout());
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.insets = new Insets(5, 5, 5, 5);
    }

    @Override
    public void setUp() {
        //需要增添setup以精简之后的method
        prevButton = new JButton("Prev Page");
        nextButton = new JButton("Next Page");
        Dimension buttonSize = new Dimension(100, 30);
        nextButton.setPreferredSize(buttonSize);
        prevButton.setPreferredSize(buttonSize);

        saveButton = new JButton("Save");
        saveButton.setPreferredSize(buttonSize);
    }

    @Override
    public void buildMainContent() {
        setTitle();
        showExerciseLogTable();
        addSaveExerciseModule();
    }

    public void setTitle() {
        JLabel titleLabel = new JLabel("Welcome to exercise page!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // 可以设置字体和大小
        constraints.gridx = 0;
        constraints.gridy = gridy++;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.NONE;
        constraints.insets = new Insets(5, 0, 5, 5);
        page.add(titleLabel, constraints);
    }
    public void showExerciseLogTable() {
        //create log table title
        JLabel logTableLabel = new JLabel("Check your exercise log here!");
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

        //create log table
        DefaultTableModel basicModel = new DefaultTableModel();
        basicModel.setColumnIdentifiers(new Object[]{"Type", "Date", "Duration", "Intensity", "Calories"});
        exerciseLogTable = new JTable(basicModel);
        constraints.gridx = 0;
        constraints.gridy = gridy++;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 3.0;
        constraints.insets = new Insets(5, 20, 5, 20);
        page.add(new JScrollPane(exerciseLogTable), constraints);

        //add prev and next buttons
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

    public JTable getLogTable() {
        return exerciseLogTable;
    }
    public JButton getNextButton() {
        return nextButton;
    }

    public JButton getPrevButton() {
        return prevButton;
    }

    public void addSaveExerciseModule() {
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

        // create date entry
        NfEntry dateEntry = new NfEntry(20, 200);
        dateEntry.setTitle("Date");
        addComponent(dateEntry);

        // create duration entry
        NfEntry durationEntry = new NfEntry(20, 200);
        durationEntry.setTitle("Duration(min)");
        addComponent(durationEntry);

        // create intensity entry
        AutoComboBox<String> intensityEntry = new AutoComboBox<>(20, 200);
        intensityEntry.setTitleField("Intensity");
        java.util.List<String> intensities = Arrays.asList("Very High", "High", "Medium", "Low");
        intensityEntry.setItemList(intensities);
        addComponent(intensityEntry);

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
}

