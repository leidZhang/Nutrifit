package main.frontend.view.exercise;

import main.backend.common.Result;
import main.backend.exercise.IExerciseController;
import main.backend.exercise.IExerciseMapper;
import main.backend.exercise.entity.Exercise;
import main.backend.exercise.impl.ExerciseController;
import main.backend.exercise.impl.ExerciseMapper;
import main.backend.exercise.impl.ExerciseService;
import main.backend.user.entity.User;
import main.frontend.custom.table.PaginationModel;
import main.frontend.custom.table.PaginationTable;
import main.frontend.session.UserSession;
import main.frontend.common.Content;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExercisePage extends Content {
    @Override
    public String showContent(JPanel content) {
        content.removeAll();
        content.setLayout(new GridBagLayout());

        //construct page
        ExerciseBuilder builder = new ExerciseBuilder(content);
        ExerciseDirector director = new ExerciseDirector(builder);
        director.constructPage();
        // loadExerciseLog(builder);
        return "Switch to Exercise page";
    }
    private void loadExerciseLog(ExerciseBuilder builder) {
        User user = instance.getUser();
        IExerciseController controller = new ExerciseController(new ExerciseService());
        String[] header = {"Date", "Type", "Intensity", "Burn Calories"};
        Result result = controller.getByUsername(user.getUsername());
        if("200".equals(result.getCode())) {
            Object data = result.getData();
            if (data instanceof java.util.List<?>) {
                java.util.List<?> list = (java.util.List<?>) data;
                java.util.List<Exercise> exercises = new ArrayList<>();
                for (Object item : list) {
                    if (item instanceof Exercise) {
                        exercises.add((Exercise) item);
                    } else {
                        throw new ClassCastException("Exercise type expected, error: " + item.getClass().getName());
                    }
                }
                java.util.List<Object[]> rowList = new ArrayList<>();
                for (Exercise e : exercises) {
                    int id = e.getId();
                    Date date = e.getDate();
                    String type = e.getType();
                    int duration = e.getDuration();
                    long burnCalories = e.getBurnCalories();
                    rowList.add(new Object[]{id, date, type, duration, burnCalories});
                }
            } else {
                throw new IllegalArgumentException("Unexpected data structure");
            }
        } else {
            throw new RuntimeException();
        }
    }

}