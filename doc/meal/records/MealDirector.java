package main.frontend.view.zdump.records;

import main.frontend.common.ContentBuilder;
import main.frontend.custom.table.PaginationTable;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;

public class MealDirector {
    private MealBuilder builder;
    public MealDirector(ContentBuilder builder) {
        this.builder = (MealBuilder)builder;
    }

    public void constructPage(String title, ActionListener searchListener, ActionListener addActionListener, ActionListener deleteListener, ActionListener infoListener) {
        builder.setUp();
        builder.clearPage();
        builder.buildTitle(title);
        builder.buildMainContent();
        builder.createAddButton(addActionListener);
        builder.createDeleteButton(deleteListener);
        builder.createInfoButton(infoListener);
        builder.createSearchTextFieldAndButton(searchListener);
        builder.createPageButtons();
        builder.createMealTable();
    }
    public JButton getInfoButton() {
        return builder.getInfoButton();
    }

    public JButton getDeleteButton() {
        return builder.getDeleteButton();
    }

    public PaginationTable getTable() {
        return builder.getTable();
    }

    public void setTableData(List<Object[]> data) {
            builder.getTable().setModelData(data);
    }

}
