package main.frontend.view.home;

import main.backend.common.Result;
import main.backend.exercise.IExerciseController;
import main.backend.exercise.impl.ExerciseController;
import main.backend.meal.IMealController;
import main.backend.meal.impl.MealController;
import main.backend.predict.IPredictController;
import main.backend.predict.impl.PredictController;
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
    private final String UNIT_KG = "kg";
    private LocalDate today;
    private LocalDate defaultStartDate;
    private Map<String, NfEntry> entries;
    private Map<String, JButton> buttons;
    private DefaultCategoryDataset dataSet;
    private IMealController mealController = new MealController();
    private IExerciseController exerciseController = new ExerciseController();
    private IPredictController predictController = new PredictController();

    public Home() {
        today = LocalDate.now();
        defaultStartDate = today.minusDays(14);
    }

    private boolean verifyPeriod() {
        boolean flag = true;
        flag = flag & entries.get("Start Date").verifyInput();
        flag = flag & entries.get("End Date").verifyInput();

        return flag;
    }

    private ActionListener handleApply() {
        return e -> {
            if (!verifyPeriod()) return;

            Date startDate = Date.valueOf(entries.get("Start Date").getInput());
            Date endDate = Date.valueOf(entries.get("End Date").getInput());
            renderLineChart(startDate, endDate);
        };
    }

    private ActionListener handleCalculate() {
        return e -> {
           entries.get("Date").setMessage(""); // clear error message
           if (!entries.get("Date").verifyInput()) return;

           User user = instance.getUser();
           Date date = Date.valueOf(entries.get("Date").getInput());
           Result res = predictController.getPredictionByDate(user, date);
           if (res.getCode().equals("200")) {
               float fatLose = (float) res.getData();
               entries.get("Fat Lose").setEntry(String.format("%.2f", fatLose) + UNIT_KG);
           } else {
               JOptionPane.showMessageDialog(null, res.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
           }
        };
    }

    private ActionListener handleReset() {
        return e -> {
            entries.get("Start Date").setEntry("");
            entries.get("Start Date").setMessage("");
            entries.get("End Date").setEntry("");
            entries.get("End Date").setMessage("");

            renderLineChart(Date.valueOf(defaultStartDate), Date.valueOf(today));
        };
    }

    private void setRegex() {
        for (Map.Entry<String, NfEntry> entry : entries.entrySet()) {
            if (entry.getKey().equals("Fat Lose")) continue;
            entry.getValue().setRegex(
                "^(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$",
                "data input format should be yyyy-mm-dd"
            );
        }
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

    private void renderLineChart(Date startDate, Date endDate) {
        User user = instance.getUser();

        Result mealRes = mealController.getCaloriesByDate(user, startDate, endDate);
        Result exerciseRes = exerciseController.getCaloriesByDate(user, startDate, endDate);
        plotLineChart(mealRes, "Calorie Intake");
        plotLineChart(exerciseRes, "Calorie Expenditure");
    }

    private void mount() {
        buttons.get("Apply").addActionListener(handleApply());
        buttons.get("Reset").addActionListener(handleReset());
        buttons.get("Calculate").addActionListener(handleCalculate());


        renderLineChart(Date.valueOf(defaultStartDate), Date.valueOf(today));

        setRegex();
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
