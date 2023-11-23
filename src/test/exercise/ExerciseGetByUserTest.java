package test.exercise;

import main.backend.common.Result;
import main.backend.exercise.entity.Exercise;
import main.backend.user.entity.User;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class ExerciseGetByUserTest extends ExerciseBaseTest {
    private final String INVALID_USERNAME = "faker";
    @Test
    public void ExerciseGetByUserTestCase1() {
        //Test user get all exercise records
        User user = new User(1, "a", "a", "1111111111", "female", null, 0, 0);
        exerciseController.save(saveExercise(), user);
        Result res = exerciseController.getByUsername(user.getUsername());
        assertEquals(SUCCESS_CODE, res.getCode());
        if (res.getCode().equals(SUCCESS_CODE)) {
            for (Exercise exercise : (List<Exercise>) res.getData()) {
                assertNotNull("Exercise should not be null", exercise);
                assertNotNull("Date should not be null", exercise.getDate());
                assertNotNull("Type should not be null", exercise.getType());
                assertNotNull("Intensity should not be null", exercise.getIntensity());
                assertTrue("Duration should be legal", exercise.getDuration() > 0 && exercise.getDuration() <= 720);
            }
        }
    }

    @Test
    public void ExerciseGetByUserTestCase2() {
        //Test fake user get exercise records
        Result res = exerciseController.getByUsername(INVALID_USERNAME);
        assertEquals("Unexpected success code for non-existing user", SUCCESS_CODE, res.getCode());
    }

    @Test
    public void ExerciseGetByUserTestCase3() {
        //Test user get exercise records without any existing exercise record
        User user = new User(10, "noRecord", "noRecord", "1111111111", "female", null, 0, 0);
        Result res = exerciseController.getByUsername(user.getUsername());
        assertEquals(SUCCESS_CODE, res.getCode());
        assertTrue("The result should contain an empty list for a user with no exercise record", ((List<?>) res.getData()).isEmpty());
    }

    private Exercise saveExercise() {
        Exercise exercise = new Exercise(Date.valueOf(LocalDate.now()), "Walking", "Low", 30);
        exerciseController.save(exercise, user);
        return exercise;
    }

}
