package main.frontend.view.exercise;

import main.backend.common.Result;
import main.backend.exercise.IExerciseController;
import main.backend.exercise.entity.Exercise;
import main.backend.exercise.impl.ExerciseController;
import main.backend.user.entity.User;
import main.frontend.custom.dropdown.AutoComboBox;
import main.frontend.custom.entry.NfEntry;
import main.frontend.custom.table.PaginationTable;
import main.frontend.common.Content;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExercisePage extends Content {
    private PaginationTable table;
    private Map<String, JComponent> entries;
    private IExerciseController controller = new ExerciseController();

    @Override
    public String showContent(JPanel content) {
        User user = instance.getUser();

        //construct page
        ExerciseBuilder builder = new ExerciseBuilder(content);
        ExerciseDirector director = new ExerciseDirector(builder);
        director.constructPage("Exercise Record", handlePerv(), handleNext(), handleSubmit(user, content));


        table = builder.getTable();
        entries = builder.getEntries();
        loadExerciseLog(user);

        return "Switch to Exercise page";
    }

    private ActionListener handlePerv() {
        return e -> table.prevPage();
    }

    private ActionListener handleNext() {
        return e -> table.nextPage();
    }

    private ActionListener handleSubmit(User user, JPanel content) {
        return e -> {
            String type = (String) ((AutoComboBox) entries.get("Type")).getInput();
            Date date = Date.valueOf(((NfEntry) entries.get("Date")).getInput());
            int duration = Integer.parseInt(((NfEntry) entries.get("Duration(min)")).getInput());
            String intensity = (String) ((AutoComboBox) entries.get("Intensity")).getInput();

            Exercise exercise = new Exercise(date, type, intensity, duration);
            Result res = controller.save(exercise, user);
            if (res.getCode().equals("200")) {
                JOptionPane.showMessageDialog(content, "Information updated!", "Message", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(content, res.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        };
    }

    private void loadExerciseLog(User user) {
        Result result = controller.getByUsername(user.getUsername());
        String code = result.getCode();
        if (code.equals("200")) {
            List<Exercise> exercises = (List<Exercise>) result.getData();

            List<Object[]> rowList = new ArrayList<>();
            for (Exercise e : exercises) {
                int id = e.getId();
                Date date = e.getDate();
                String type = e.getType();
                String intensity = e.getIntensity();
                int duration = e.getDuration();
                long burnCalories = e.getBurnCalories();

                rowList.add(new Object[]{id, type, date, duration, intensity, burnCalories});
            }

            table.setModelData(rowList);
        } else {
            throw new RuntimeException();
        }
    }
}