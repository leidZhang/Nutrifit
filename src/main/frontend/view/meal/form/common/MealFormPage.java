package main.frontend.view.meal.form.common;

import main.backend.meal.IMealController;
import main.backend.meal.impl.MealController;
import main.frontend.common.Content;
import main.frontend.view.mainframe.IContent;
import main.frontend.custom.table.PaginationTable;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Map;

public abstract class MealFormPage extends Content {
    protected Map<String, JComponent> entries;
    protected Map<String, JButton> buttons;
    protected PaginationTable table;
    protected IMealController mealController = new MealController();

    protected ActionListener handleBack() {
        return e -> {
            Map<String, IContent> map = frontEnd.get().getPageMap();
            frontEnd.get().switchContentPanel(map.get("Meal Records"));
        };
    }

    @Override
    public abstract String showContent(JPanel content);
}
