package main.frontend.view.zdump.records;

import main.backend.common.Result;
import main.backend.food.IFoodController;
import main.backend.food.impl.FoodController;
import main.backend.meal.IMealController;
import main.backend.meal.entity.Meal;
import main.backend.meal.impl.MealController;
import main.frontend.custom.table.PaginationTable;
import main.frontend.common.Content;
import main.frontend.view.zdump.form.FormBuilder;
import main.frontend.view.zdump.form.FormDirector;
import main.frontend.view.zdump.visualization.VisualBuilder;
import main.frontend.view.zdump.visualization.VisualDirector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MealPage extends Content {
    IFoodController foodController = new FoodController();
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    IMealController mealController = new MealController();
    int selectedMealId;
    java.util.List<Meal> mealList;
    private MealBuilder mealBuilder;
    private MealDirector mealDirector;
    private FormBuilder addFormBuilder;
    private FormDirector addFormDirector;
    private FormBuilder infoFormBuilder;
    private FormDirector infoFormDirector;

    @Override
    public String showContent(JPanel content) {
        Locale.setDefault(new Locale("en","US"));
        content.removeAll();

        // construct page
        ActionListener addListener = handleAdd(content);
        ActionListener deleteListener = handleDelete(content);
        ActionListener infoListener = handleInfo(content);
        ActionListener searchListener = handleSearch(content);

        mealBuilder = new MealBuilder(content);
        mealDirector = new MealDirector(mealBuilder);
        mealDirector.constructPage("Meal", searchListener, addListener, deleteListener, infoListener);

        MouseListener mealTableListener = handleMealTable(((MealBuilder) mealBuilder).getDeleteButton(),((MealBuilder) mealBuilder).getInfoButton(),((MealBuilder) mealBuilder).getTable());
        ActionListener previousListener = handlePrevious(((MealBuilder) mealBuilder).getTable());
        ActionListener nextListener = handleNext(((MealBuilder) mealBuilder).getTable());
        ((MealBuilder) mealBuilder).getTable().addMouseListener(mealTableListener);
        ((MealBuilder) mealBuilder).getPrevButton().addActionListener(previousListener);
        ((MealBuilder) mealBuilder).getNextButton().addActionListener(nextListener);

        //add Form
        addFormBuilder = new FormBuilder(content);
        addFormDirector = new FormDirector(addFormBuilder);
        //view Form
        infoFormBuilder = new FormBuilder(content);
        infoFormDirector = new FormDirector(infoFormBuilder);

        //get mealTableData
        getMealTableDataBuUser();

        return "Switch to Meal Page";
    }

    public void getMealTableDataBuUser(){
        // request controller
        Result mealRes = new MealController().getByUser(instance.getUser());
        java.util.List<Object[]> rowList = new ArrayList<>();
        if (mealRes.getCode().equals("200")) {
            mealList = (List<Meal>) mealRes.getData();
            for (int i = 0; i < mealList.size(); i++) {
                Meal meal = mealList.get(i);
                rowList.add(new Object[]{meal.getId(),meal.getDate(), meal.getType(), meal.getTotalCalories()});
            }
        }
        mealDirector.setTableData(rowList);
    }

    public List<Meal> getMealTableDataBuPeriod(Date startDate, Date endDate){
        // request controller
        Result mealRes = new MealController().getByPeriod(instance.getUser(), startDate,endDate);
        java.util.List<Object[]> rowList = new ArrayList<>();
        if (mealRes.getCode().equals("200")) {
            mealList = (List<Meal>) mealRes.getData();
            for (int i = 0; i < mealList.size(); i++) {
                Meal meal = mealList.get(i);
                rowList.add(new Object[]{meal.getId(),meal.getDate(), meal.getType(), meal.getTotalCalories()});
            }
        }
//        mealDirector.setTableData(rowList);
        return (List<Meal>) mealRes.getData();
    }
    private ActionListener handleAddController(JPanel content) {
        ActionListener listener =  e -> {
            Result mealRes = new MealController().save(addFormDirector.getAddFormInfo(),instance.getUser());
            if (mealRes.getCode().equals("200")) {
                JOptionPane.showMessageDialog(content,"delete success!","",JOptionPane.PLAIN_MESSAGE);
            }
        };
        return listener;
    }

    private ActionListener handleAdd(JPanel content) {
        ActionListener listener =  e -> {
            addFormDirector.createAddForm(instance.getUser());
            addFormBuilder.getForm().addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    getMealTableDataBuUser();
                }
            });
            System.out.println("go to addForm");
        };
        return listener;
    }
    private ActionListener handleDelete(JPanel content) {
       ActionListener listener =  e -> {
           int opt = JOptionPane.showConfirmDialog(content,
                   "Do you want delete this record?", "",
                   JOptionPane.YES_NO_OPTION);
           if (opt == JOptionPane.YES_OPTION) {
               // request controller
               Result mealRes = mealController.delete(selectedMealId);
               if (mealRes.getCode().equals("200")) {
                   JOptionPane.showMessageDialog(content,"delete success!","",JOptionPane.PLAIN_MESSAGE);
                   getMealTableDataBuUser();
               }
           }

       };
       return listener;
    }
    private ActionListener handleSearch(JPanel content) {
        ActionListener listener =  e -> {
//            List<Meal> meals = getMealTableDataBuPeriod(Date.valueOf(mealBuilder.getStartTime().getText()), Date.valueOf(mealBuilder.getEndTime().getText()));
            List<Meal> meals = getMealTableDataBuPeriod(Date.valueOf("2000-01-01"), Date.valueOf("2024-01-02"));
            VisualBuilder visualBuilder = new VisualBuilder(content);
            VisualDirector visualDirector = new VisualDirector(visualBuilder);
            visualDirector.createPeriodDashBoard(meals);
            System.out.println("go to Visualization");
        };
        return listener;
    }

    private ActionListener handleInfo(JPanel content) {
        ActionListener listener =  e -> {
            for(Meal meal:mealList){
                if(meal.getId()==selectedMealId){
                    infoFormDirector.createInfoForm(meal);
                    break;
                }
            }
            System.out.println("go to infoForm");
        };
        return listener;
    }

    private MouseListener handleMealTable(JButton deleteButton, JButton infoButton, PaginationTable table) {
        MouseListener listener = new MouseListener() {
                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    deleteButton.setEnabled(true);
                    infoButton.setEnabled(true);
                    int count= table.getSelectedRow();
                    selectedMealId = Integer.valueOf(table.getValueAt(count, 0).toString());//
                }

                @Override
                public void mouseExited(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseClicked(MouseEvent e){
                    deleteButton.setEnabled(true);
                    infoButton.setEnabled(true);
                    int count= table.getSelectedRow();
                    selectedMealId = Integer.valueOf(table.getValueAt(count, 0).toString());//
                }
            };
        return listener;
    }

    private ActionListener handlePrevious(PaginationTable table) {
        ActionListener prevListener = e -> table.prevPage();
        return prevListener;
    }

    private ActionListener handleNext(PaginationTable table) {
        ActionListener nextListener = e -> table.nextPage();
        return nextListener;
    }
}
