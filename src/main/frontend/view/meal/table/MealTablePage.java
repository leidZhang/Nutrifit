package main.frontend.view.meal.table;

import main.backend.common.Result;
import main.backend.meal.IMealController;
import main.backend.meal.entity.Meal;
import main.backend.meal.impl.MealController;
import main.backend.user.entity.User;
import main.frontend.common.Content;
import main.frontend.common.ContentBuilder;
import main.frontend.common.IContent;
import main.frontend.custom.entry.NfEntry;
import main.frontend.custom.table.PaginationTable;
import main.frontend.view.meal.form.add.MealAddForm;
import main.frontend.view.meal.form.detail.MealDetailForm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MealTablePage extends Content {
    private PaginationTable table;
    private Map<String, NfEntry> entries;
    private Map<String, JButton> buttons;
    private IMealController controller = new MealController();
    private IContent detailForm = new MealDetailForm();

    private ActionListener handleNextPage() {
        return e -> table.nextPage();
    }

    private ActionListener handlePrevPage() {
        return  e -> table.prevPage();
    }

    private ActionListener handleBack() {
        return e -> {
            Map<String, IContent> map = frontEnd.get().getPageMap();
            frontEnd.get().switchContentPanel(map.get("Meal"));
        };
    }

    private ActionListener handleAdd() {
        return e -> {
            Map<String, IContent> map = frontEnd.get().getPageMap();
            frontEnd.get().switchContentPanel(map.get("Meal Form"));
        };
    }

    private void setRegex() {
        String[] names = {"Start Date", "End Date"};
        for (String name : names) {
            entries.get(name).setRegex(
                    "^(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$",
                    "data input format should be yyyy-mm-dd");
        }
    }

    private boolean verify() {
        boolean flag = true;

        flag = flag & entries.get("Start Date").verifyInput();
        flag = flag & entries.get("End Date").verifyInput();

        return flag;
    }

    private ActionListener handleApply(JPanel content) {
        return e -> {
            if (!verify()) return;

            User user = instance.getUser();
            Date startDate = Date.valueOf(entries.get("Start Date").getInput());
            Date endDate = Date.valueOf(entries.get("End Date").getInput());

            Result res = controller.getByPeriod(user, startDate, endDate);
            showTableData(content, res);
        };
    }

    private ActionListener handleReset(JPanel content) {
        return e -> {
            entries.get("Start Date").setEntry("");
            entries.get("End Date").setEntry("");
            initTableData(content);
        };
    }

    private ActionListener handleDelete() {
        return e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                Object[] rowData = model.getDataVector().elementAt(row).toArray();
                int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this row?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.NO_OPTION) return; // break here if no is selected

                int id = (int) rowData[0]; // cast id to int
                Result res = controller.delete(id);
                if (res.getCode().equals("200")) {
                    JOptionPane.showMessageDialog(null, "Delete success", "Message", JOptionPane.INFORMATION_MESSAGE);
                    model.removeRow(row);
                } else {
                    JOptionPane.showMessageDialog(null, res.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
    }

    private ActionListener handleViewDetail() {
        return e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                Object[] rowData = model.getDataVector().elementAt(row).toArray();

                int id = (int) rowData[0]; // cast id to int
                Result res = controller.getById(id);
                if (res.getCode().equals("200")) {
                    Meal meal = ((Meal) res.getData());
                    ((MealDetailForm) detailForm).setMeal(meal);
                    frontEnd.get().switchContentPanel(detailForm);
                } else {
                    JOptionPane.showMessageDialog(null, res.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
    }

    private void setTablePopUp() {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem viewItem = new JMenuItem("View Details");
        JMenuItem deleteItem = new JMenuItem("Delete");

        popupMenu.add(viewItem);
        popupMenu.add(deleteItem);
        table.setComponentPopupMenu(popupMenu);

        viewItem.addActionListener(handleViewDetail());
        deleteItem.addActionListener(handleDelete());
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(table, e.getX(), e.getY());
                }
            }
        });
    }

    private void showTableData(JPanel content, Result res) {
        if (res.getCode().equals("200")) {
            List<Meal> meals = (List<Meal>) res.getData();
            loadTableData(meals);
        } else {
            JOptionPane.showMessageDialog(content, res.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initTableData(JPanel content) {
        User user = instance.getUser();
        Result res = controller.getByUser(user);
        showTableData(content, res);
    }

    private void loadTableData(List<Meal> meals) {
        List<Object[]> rowList = new ArrayList<>();
        for (Meal meal : meals) {
            int id = meal.getId();
            String type = meal.getType();
            Date date = meal.getDate();
            int calories = meal.getTotalCalories();

            rowList.add(new Object[]{id, type, date, calories + " kCal"});
            table.setModelData(rowList);
        }
    }

    private void mount(JPanel content) { // render data add apply listeners
        initTableData(content);
        setTablePopUp();
        setRegex();

        buttons.get("Prev Page").addActionListener(handlePrevPage());
        buttons.get("Next Page").addActionListener(handleNextPage());
        buttons.get("Apply").addActionListener(handleApply(content));
        buttons.get("Reset").addActionListener(handleReset(content));
        buttons.get("Back").addActionListener(handleBack());
        buttons.get("New Record").addActionListener(handleAdd());
    }

    @Override
    public String showContent(JPanel content) {
        ContentBuilder builder = new MealTableBuilder(content);
        MealTableDirector director = new MealTableDirector(builder);

        director.constructPage("Meal Records");
        table = ((MealTableBuilder) builder).getTable();
        entries = ((MealTableBuilder) builder).getEntries();
        buttons = ((MealTableBuilder) builder).getButtonMap();

        mount(content); // initial render

        return "Meal Table";
    }
}
