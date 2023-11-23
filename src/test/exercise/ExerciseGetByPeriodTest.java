package test.exercise;

import org.junit.Test;
import main.backend.exercise.entity.Exercise;
import main.backend.common.Result;
import main.backend.user.entity.User;
import org.junit.Test;
import java.sql.Date;
import java.util.List;
import static org.junit.Assert.*;

public class ExerciseGetByPeriodTest extends ExerciseBaseTest {
    @Test
    public void ExerciseGetByPeriodTestCase1() {
        //Test user get exercise records by period
        User user = new User(1, "a", "a", "1111111111", "female", null, 0, 0);
        Result res = exerciseController.getByPeriod(user.getUsername(), Date.valueOf("2023-11-09"), Date.valueOf("2023-11-15"));
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
    public void exerciseGetByPeriodCase2() {
        //Test user get exercise records without start date
        User user = new User(1, "a", "a", "1111111111", "female", null, 0, 0);
        Result res = exerciseController.getByPeriod(user.getUsername(), null, Date.valueOf("2023-10-30"));
        assertNotEquals(SUCCESS_CODE, res.getCode());
    }

    @Test
    public void exerciseGetByPeriodCase3() {
        //Test user get exercise records without end date
        User user = new User(1, "a", "a", "1111111111", "female", null, 0, 0);
        Result res = exerciseController.getByPeriod(user.getUsername(), Date.valueOf("2023-10-25"), null);
        assertNotEquals(SUCCESS_CODE, res.getCode());
    }

    @Test
    public void exerciseGetByPeriodCase4() {
        //Test user get exercise records without start date and end date
        User user = new User(1, "a", "a", "1111111111", "female", null, 0, 0);
        Result res = exerciseController.getByPeriod(user.getUsername(), null, null);
        assertNotEquals(SUCCESS_CODE, res.getCode());
    }

    @Test
    public void exerciseGetByPeriodCase5() {
        //Test user get exercise records with start date after end date
        User user = new User(1, "a", "a", "1111111111", "female", null, 0, 0);
        Result res = exerciseController.getByPeriod(user.getUsername(), Date.valueOf("2023-10-30"), Date.valueOf("2023-10-25"));
        assertNotEquals(SUCCESS_CODE, res.getCode());
    }

    @Test
    public void exerciseGetByPeriodCase6() {
        //Test user get exercise records with end date after current date
        User user = new User(1, "a", "a", "1111111111", "female", null, 0, 0);
        Result res = exerciseController.getByPeriod(user.getUsername(), Date.valueOf("2023-10-25"), Date.valueOf("2050-11-23"));
        assertNotEquals(SUCCESS_CODE, res.getCode());
    }
}

