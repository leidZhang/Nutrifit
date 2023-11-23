package test.exercise;

import org.junit.After;
import org.junit.Test;
import main.backend.exercise.entity.Exercise;
import main.backend.common.Result;
import main.backend.user.entity.User;

import java.sql.Date;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class ExerciseGetExerciseMinutesTest extends ExerciseBaseTest{
    private Map<Date, Integer> exerciseMinutes;
    private int exercise1ID;
    private int exercise2ID;
    private int exercise3ID;
    @Test
    public void ExerciseGetExerciseMinutesTestCase1() {
        prepareTestData(user);
        Result res = exerciseController.getDailyExerciseMinutesByDate(user, Date.valueOf("2023-04-01"), Date.valueOf("2023-04-31"));
        assertEquals(SUCCESS_CODE, res.getCode());
        assertTrue(res.getData() instanceof Map);
        exerciseMinutes = (Map<Date, Integer>) res.getData();
        assertFalse(exerciseMinutes.isEmpty());

        //Test total exercise minutes for 2023-10-11
        assertTrue(exerciseMinutes.containsKey(Date.valueOf("2023-04-11")));
        assertEquals(Integer.valueOf(90), exerciseMinutes.get(Date.valueOf("2023-04-11")));
    }

    private void prepareTestData(User user) {
        Exercise exercise1 = new Exercise(Date.valueOf("2023-04-10"), "Running", "High", 45);
        exerciseController.save(exercise1, user);
        exercise1ID = exercise1.getId();
        System.out.println("successfully saved new record");

        Exercise exercise2 = new Exercise(Date.valueOf("2023-04-11"), "Walking", "Low", 30);
        exerciseController.save(exercise2, user);
        exercise2ID = exercise2.getId();
        System.out.println("successfully saved new record");

        Exercise exercise3 = new Exercise(Date.valueOf("2023-04-11"), "Running", "Medium", 60);
        exerciseController.save(exercise3, user);
        exercise3ID = exercise3.getId();
        System.out.println("successfully saved new record");
    }

    @After
    public void clearTestData() {
        exerciseController.delete(exercise1ID);
        System.out.println("successfully deleted new record");
        exerciseController.delete(exercise2ID);
        System.out.println("successfully deleted new record");
        exerciseController.delete(exercise3ID);
        System.out.println("successfully deleted new record");
    }

}
