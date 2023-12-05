package main.frontend.view.exercise.visualization;

import main.backend.common.Result;
import main.backend.exercise.IExerciseController;
import main.backend.exercise.impl.ExerciseController;
import main.backend.user.entity.User;
import main.frontend.common.ContentBuilder;
import main.frontend.common.Director;
import main.frontend.custom.entry.NfEntry;
import main.frontend.common.Content;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Map;

public class ExerciseVisualPage extends Content {
    private Content recordPage;
    private LocalDate today;
    private LocalDate defaultStartDate;
    private Map<String, NfEntry> entries;
    private Map<String, JButton> buttons;
    private IExerciseController exerciseController = new ExerciseController();
    private DefaultCategoryDataset dataSet;

    public ExerciseVisualPage(Content recordPage) {
        this.recordPage = recordPage;
        today = LocalDate.now();
        defaultStartDate = today.minusDays(14);
    }

    private boolean verifyPeriod() {
        boolean flag = true;
        flag = flag & entries.get("Start Date").verifyInput();
        flag = flag & entries.get("End Date").verifyInput();

        return flag;
    }

    private ActionListener handleViewRecords() {
        return e -> {
            frontEnd.get().switchContentPanel(recordPage);
        };
    }
    private ActionListener handleApply() {
        return e -> {
            if(!verifyPeriod()) return;
            dataSet.clear(); // clear dataset

            Date startDate = Date.valueOf(entries.get("Start Date").getInput());
            Date endDate = Date.valueOf(entries.get("End Date").getInput());
            renderBarChart(startDate, endDate);
        };
    }

    private ActionListener handleReset() {
        return e -> {
            entries.get("Start Date").setEntry("");
            entries.get("Start Date").setMessage("");
            entries.get("End Date").setEntry("");
            entries.get("End Date").setMessage("");

            renderBarChart(Date.valueOf(defaultStartDate), Date.valueOf(today));
        };
    }

    private void setRegex() {
        for (Map.Entry<String, NfEntry> entry : entries.entrySet()) {
            entry.getValue().setRegex(
                    "^(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$",
                    "data input format should be yyyy-mm-dd"
            );
        }
    }

    private void renderBarChart(Date startDate, Date endDate) {
        dataSet.clear();
        User user = instance.getUser();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Result res = exerciseController.getDailyExerciseMinutesByDate(user, startDate, endDate);

        if(res.getCode().equals("200")) {
            Map<Date, Integer> totalMinutes = (Map<Date, Integer>) res.getData();
            for(Map.Entry<Date, Integer> entry : totalMinutes.entrySet()) {
                dataSet.addValue(entry.getValue(), "Total exercise minutes", sdf.format(entry.getKey()));
            }
        } else {
            JOptionPane.showMessageDialog(null, res.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected void mount(JPanel content) {
        buttons.get("Apply").addActionListener(handleApply());
        buttons.get("Reset").addActionListener(handleReset());
        buttons.get("View Records").addActionListener(handleViewRecords());

        renderBarChart(Date.valueOf(defaultStartDate), Date.valueOf(today));

        setRegex();
    }

    @Override
    public String showContent(JPanel content) {
        ExerciseVisualBuilder builder = new ExerciseVisualBuilder(content);
        Director director = new Director(builder);

        director.constructPage("Exercise Visualization");
        entries = builder.getEntries();
        buttons = builder.getButtons();
        dataSet = builder.getDataSet();

        mount(content);
        return "Exercise Visualization";
    }
}
