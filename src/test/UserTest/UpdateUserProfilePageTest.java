package test.UserTest;

import main.backend.common.Result;
import main.backend.user.IUserController;
import main.backend.user.entity.User;
import main.backend.user.impl.UserController;
import main.frontend.FrontEnd;
import main.frontend.session.UserSession;
import main.frontend.view.IContent;
import main.frontend.view.user.UpdateUserProfile;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JTextComponentFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class UpdateUserProfilePageTest {

    private FrameFixture window;

    @Before
    public void setUp() {
        FrontEnd frame = GuiActionRunner.execute(FrontEnd::new);
        window = new FrameFixture(frame);
        window.show();
        window.maximize();
        IContent updateUserProfile = new UpdateUserProfile();
        frame.switchContentPanel(updateUserProfile);

        IUserController userController = new UserController();
        UserSession instance = UserSession.getInstance();
        Result res=userController.getUser("username");
        User userNow = (User) res.getData();
        instance.setUser(userNow);
    }

    @After
    public void tearDown() {
        window.cleanUp();
    }

    @Test
    public void testUpdate() {
        int id=19;
        LocalDate localDate = LocalDate.now();
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
        User testUser = new User("name", "username", "male", sqlDate, 180, 60, 18);

        // Find the text input box and enter text
        JTextComponentFixture idField = window.textBox("idField");
        idField.enterText(String.valueOf(id));

        JTextComponentFixture nameField = window.textBox("nameField");
        nameField.enterText(testUser.getName());

        JTextComponentFixture usernameField = window.textBox("usernameField");
        usernameField.enterText(testUser.getUsername());

        JTextComponentFixture sexField = window.textBox("sexField");
        sexField.enterText(testUser.getSex());

        JTextComponentFixture dateOfBirthField = window.textBox("dateOfBirthField");
        dateOfBirthField.enterText(String.valueOf(testUser.getDateOfBirth()));

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

