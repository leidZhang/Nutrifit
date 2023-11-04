package test.UserTest;

import main.backend.user.entity.User;
import main.frontend.FrontEnd;
import main.frontend.view.IContent;
import main.frontend.view.user.Registration;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JTextComponentFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class RegistationPageTest {

    private FrameFixture window;

    @Before
    public void setUp() {
        FrontEnd frame = GuiActionRunner.execute(FrontEnd::new);
        window = new FrameFixture(frame);
        window.show();
        window.maximize();
        IContent registration = new Registration();
        frame.switchContentPanel(registration);
    }

    @After
    public void tearDown() {
        window.cleanUp();
    }

    @Test
    public void testRegister() {
        LocalDate localDate = LocalDate.now();
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
        User testUser=new User("name","username","male", sqlDate,180,60,18);

        // Find the text input box and enter text
        JTextComponentFixture nameField = window.textBox("nameField");
        nameField.enterText(testUser.getName());

        JTextComponentFixture usernameField = window.textBox("usernameField");
        usernameField.enterText(testUser.getUsername());

        JTextComponentFixture sexField = window.textBox("sexField");
        sexField.enterText(testUser.getSex());

        JTextComponentFixture heightField = window.textBox("heightField");
        heightField.enterText(String.valueOf(testUser.getHeight()));

        JTextComponentFixture weightField = window.textBox("weightField");
        weightField.enterText(String.valueOf(testUser.getWeight()));

        JTextComponentFixture ageField = window.textBox("ageField");
        ageField.enterText(String.valueOf(testUser.getAge()));

        //click submit button from register page
        JButtonFixture submitButton = window.button("submitButton");
        submitButton.click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignored) {
        }
    }
}

