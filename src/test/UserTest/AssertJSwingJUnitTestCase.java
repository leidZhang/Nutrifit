package test.UserTest;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.testing.AssertJSwingTestCaseTemplate;
import org.junit.*;

public abstract class AssertJSwingJUnitTestCase extends AssertJSwingTestCaseTemplate {

    @BeforeClass
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }


    protected abstract void onSetUp() throws Exception;


    @AfterClass
    public static void tearDownOnce() {
        FailOnThreadViolationRepaintManager.uninstall();
    }

    @After
    public void tearDown() throws Exception {
        try {
            onTearDown();
        } finally {
            cleanUp();
        }
    }

    protected void onTearDown() {
    }
}
