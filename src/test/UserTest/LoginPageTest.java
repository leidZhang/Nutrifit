package test.UserTest;

import main.frontend.FrontEnd;
import main.frontend.view.IContent;
import main.frontend.view.user.Login;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JTextComponentFixture;
import org.junit.*;

public class LoginPageTest {
    private FrameFixture window;

    @Before
    public void setUp() {
        FrontEnd frame = GuiActionRunner.execute(FrontEnd::new);
        window = new FrameFixture(frame);
        window.show();
        window.maximize();
        IContent login = new Login();
        frame.switchContentPanel(login);
    }

    @After
    public void tearDown() {
        window.cleanUp();
    }

    @Test
    public void testLogin() {

        String testUserName = "jd123";

        // Find the text input box and enter text
        JTextComponentFixture textField = window.textBox("usernameField");
        textField.enterText(testUserName);

        //click login button from login page
        JButtonFixture loginButton = window.button("login");
        loginButton.click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignored) {
        }
    }
}

