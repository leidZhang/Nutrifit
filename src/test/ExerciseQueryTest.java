package test;

import main.backend.exercise.IExerciseMapper;
import main.backend.exercise.entity.Exercise;
import main.backend.exercise.impl.ExerciseMapper;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class ExerciseQueryTest {
    public static void main(String[] args) { // temporary test
        IExerciseMapper mapper = new ExerciseMapper();

        String type = "jogging";
        String intensity = "low";
        int duration = 30;
        int burnCalories = 1100;
        String username = "js288c";

        try {
            Date endDate = Date.valueOf("2023-10-26");
            Date startDate = Date.valueOf("2023-10-20");
            List<Exercise> res = mapper.getByPeriod("js288c", startDate, endDate);
            for (int i=0; i<res.size(); i++) {
                Exercise exercise = res.get(i);
                System.out.println(exercise.getType() + ", " + exercise.getIntensity() + ", " + exercise.getDate());
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

//        Exercise exercise = new Exercise(date, type, intensity, duration, burnCalories);

    }
}
