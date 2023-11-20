package main.frontend.view.home;

import main.backend.common.Result;
import main.backend.exercise.IExerciseController;
import main.backend.exercise.impl.ExerciseController;
import main.backend.meal.IMealController;
import main.backend.meal.impl.MealController;
import main.backend.user.entity.User;
import main.frontend.common.Content;
import main.frontend.common.ContentBuilder;
import main.frontend.custom.entry.NfEntry;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Map;


public class Home extends Content {
    private Map<String, NfEntry> entries;
    private Map<String, JButton> buttons;
    private DefaultCategoryDataset dataSet;
    private IMealController mealController = new MealController();
    private IExerciseController exerciseController = new ExerciseController();

    private ActionListener handleCalculate() {
        return e -> {
           entries.get("Fat Lose").setEntry("Under construction!");
        };
    }

    private ActionListener handleReset() {
        return e -> {
            entries.get("Start Date").setEntry("");
            entries.get("End Date").setEntry("");
        };
    }

    private void plotLineChart(Result res, String category) {
        if (res.getCode().equals("200")) {
            Map<Date, Float> calorie = (Map<Date, Float>) res.getData();
            System.out.println(calorie.size());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (Map.Entry<Date, Float> entry : calorie.entrySet()) {
                dataSet.addValue(entry.getValue(), category, sdf.format(entry.getKey()));
            }
        } else {
            JOptionPane.showMessageDialog(null, res.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void renderLineChart() {
        User user = instance.getUser();
        LocalDate today = LocalDate.now();
        LocalDate startDate = today.minusDays(14);

        Result mealRes = mealController.getCaloriesByDate(user, Date.valueOf(startDate), Date.valueOf(today));
        Result exerciseRes = exerciseController.getCaloriesByDate(user, Date.valueOf(startDate), Date.valueOf(today));
        plotLineChart(mealRes, "Calorie Intake");
        plotLineChart(exerciseRes, "Calorie Expenditure");
    }

    private void mount() {
        buttons.get("Reset").addActionListener(handleReset());
        buttons.get("Calculate").addActionListener(handleCalculate());

        renderLineChart();
    }

    @Override
    public String showContent(JPanel content) {
        ContentBuilder builder = new HomeBuilder(content);
        HomeDirector director = new HomeDirector(builder);
        director.constructPage("Welcome to Nutrifit");

        entries = ((HomeBuilder) builder).getEntries();
        buttons = ((HomeBuilder) builder).getButtons();
        dataSet = ((HomeBuilder) builder).getDataSet();

        mount();

        return "Switch to Home Page";
    }
}
