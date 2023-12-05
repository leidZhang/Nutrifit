package test.exercise;

import main.backend.exercise.entity.Exercise;
import main.backend.common.Result;
import static org.junit.Assert.*;
import org.junit.Test;
import java.sql.Date;
import java.time.LocalDate;

public class ExerciseSaveTest extends ExerciseBaseTest {

    private final String TYPE_WALKING = "Walking";
    private final String INTENSITY_HIGH = "High";
    private final String INTENSITY_LOW = "Low";
    private final Date VALID_DATE = Date.valueOf("2023-11-15");
    private final Date BOUNDARY_DATE = Date.valueOf(LocalDate.now());
    private final int DURATION_30_MIN = 30;
    private final int DURATION_60_MIN = 60;

    @Test
    public void ExerciseSaveTestCase1() {
        //Test general case
        Exercise exercise = new Exercise(VALID_DATE, TYPE_WALKING, INTENSITY_HIGH, DURATION_30_MIN);
        Result result = exerciseController.save(exercise, user);
        assertEquals(SUCCESS_CODE, result.getCode());
    }

    @Test
    public void ExerciseSaveTestCase2() {
        //Test save wrong exercise type
        Exercise exercise = new Exercise(VALID_DATE, "InvalidType", INTENSITY_HIGH, DURATION_30_MIN);
        Result result = exerciseController.save(exercise, user);
        assertNotEquals(SUCCESS_CODE, result.getCode());
    }

    @Test
    public void ExerciseSaveTestCase3() {
        //Test save wrong exercise intensity
        Exercise exercise = new Exercise(VALID_DATE, TYPE_WALKING, "InvalidIntensity", DURATION_30_MIN);
        Result result = exerciseController.save(exercise, user);
        assertNotEquals(SUCCESS_CODE, result.getCode());
    }

    @Test
    public void ExerciseSaveTestCase4() {
        //Test save with max duration
        Exercise exercise = new Exercise(VALID_DATE, TYPE_WALKING, INTENSITY_HIGH, 720);
        Result result = exerciseController.save(exercise, user);
        assertEquals(SUCCESS_CODE, result.getCode());
    }

    @Test
    public void ExerciseSaveTestCase5() {
        //Test save with min duration
        Exercise exercise = new Exercise(VALID_DATE, TYPE_WALKING, INTENSITY_HIGH, 1);
        Result result = exerciseController.save(exercise, user);
        assertEquals(SUCCESS_CODE, result.getCode());
    }

    @Test
    public void ExerciseSaveTestCase6() {
        //Test save with duration out of boundary
        Exercise exercise = new Exercise(VALID_DATE, TYPE_WALKING, INTENSITY_HIGH, 721);
        Result result = exerciseController.save(exercise, user);
        assertNotEquals(SUCCESS_CODE, result.getCode());
    }

    @Test
    public void ExerciseSaveTestCase7() {
        //Test save with duration out of boundary
        Exercise exercise = new Exercise(VALID_DATE, TYPE_WALKING, INTENSITY_HIGH, 0);
        Result result = exerciseController.save(exercise, user);
        assertNotEquals(SUCCESS_CODE, result.getCode());
    }

    @Test
    public void ExerciseSaveTestCase8() {
        //Test save record with future date
        Date futureDate = Date.valueOf("2077-10-13");
        Exercise exercise = new Exercise(futureDate, TYPE_WALKING, INTENSITY_LOW, DURATION_60_MIN);
        Result result = exerciseController.save(exercise, user);
        assertNotEquals(SUCCESS_CODE, result.getCode());
    }

    @Test
    public void ExerciseSaveTestCase9() {
        //Test save record with today
        Exercise exercise = new Exercise(BOUNDARY_DATE, TYPE_WALKING, INTENSITY_LOW, DURATION_60_MIN);
        Result result = exerciseController.save(exercise, user);
        assertEquals(SUCCESS_CODE, result.getCode());
    }

    @Test
    public void ExerciseSaveTestCase10() {
        //Test save record with null date
        Exercise exercise = new Exercise(null, TYPE_WALKING, INTENSITY_LOW, DURATION_60_MIN);
        Result result = exerciseController.save(exercise, user);
        assertNotEquals(SUCCESS_CODE, result.getCode());
    }
}

