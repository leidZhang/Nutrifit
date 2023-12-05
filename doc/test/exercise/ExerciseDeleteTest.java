package test.exercise;

import main.backend.common.Result;
import main.backend.exercise.entity.Exercise;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ExerciseDeleteTest extends ExerciseBaseTest{
    private List<Exercise> exercises;

    @Test
    public void ExerciseDeleteTestCase1() {
        //Test general case
        Result res1 = exerciseController.getByUsername(user.getUsername());
        exercises = (List<Exercise>) res1.getData();

        Exercise exercise = exercises.get(0);
        Result res2 = exerciseController.delete(exercise.getId());
        assertEquals(SUCCESS_CODE, res2.getCode());
    }

    @Test
    public void ExerciseDeleteTestCase2() {
        //Test delete newest
        exerciseController.save(saveExercise(), user); // avoid empty
        Result res1 = exerciseController.getByUsername(user.getUsername());
        exercises = (List<Exercise>) res1.getData();

        Exercise exercise = exercises.get(0);
        Result res2 = exerciseController.delete(exercise.getId());
        assertEquals(SUCCESS_CODE, res2.getCode());
    }

    @Test
    public void exerciseDeleteTestCase3() {
        // Test delete oldest
        exerciseController.save(saveExercise(), user); // avoid empty
        Result res1 = exerciseController.getByUsername(user.getUsername());
        exercises = (List<Exercise>) res1.getData();

        Exercise exercise = exercises.get(exercises.size() - 1);
        Result res2 = exerciseController.delete(exercise.getId());
        assertEquals(SUCCESS_CODE, res2.getCode());
    }

    @Test
    public void exerciseDeleteTestCase4() {
        // Test illegal deletion
        Result res1 = exerciseController.getByUsername(user.getUsername());
        exercises = (List<Exercise>) res1.getData();

        Exercise exercise = exercises.get(0);
        Result res2 = exerciseController.delete(-1); // Assuming -1 is an invalid ID
        assertNotEquals(SUCCESS_CODE, res2.getCode());
    }

    private Exercise saveExercise() {
        Exercise exercise = new Exercise(Date.valueOf(LocalDate.now()), "Walking", "Low");
        exercise.setDuration(30);
        exerciseController.save(exercise, user);
        return exercise;
    }
}
