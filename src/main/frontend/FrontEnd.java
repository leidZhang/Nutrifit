package main.frontend;

import main.backend.user.entity.User;
import main.frontend.common.IContent;
import main.frontend.session.UserSession;
import main.frontend.view.home.Home;
import main.frontend.view.user.login.LoginPage;
import main.frontend.view.user.userprofile.RegisterPage;
import main.frontend.view.user.userprofile.UserProfilePage;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class FrontEnd extends JFrame {
    private JPanel sideBar;
    private JPanel content;
    private Map<String, IContent> pageMap = Map.of( // add more if you need
            "Home", new Home(),
            "Login", new LoginPage(),
            "UserProfile", new UserProfilePage(),
            "Register", new RegisterPage()
    );
    private UserSession instance = UserSession.getInstance();

    public FrontEnd() {
        initialize(); // initialize GUI
    }

    public void initialize() {
        User user = instance.getUser();

        // initialize components
        if (user == null) { // user did not login
            // set up gui
            setSize(600, 400);
            if (content == null) content = createContent(0);
            getContentPane().add(content, BorderLayout.CENTER);
            // switch to login
            switchContentPanel(pageMap.get("Login"));
        } else { // user login
            // set up gui
            setSize(1200, 800);
            sideBar = createSideBar(250);
            content.setSize(new Dimension(950, 800));
            getContentPane().add(sideBar, BorderLayout.WEST);
            getContentPane().add(content, BorderLayout.CENTER);
            // switch to home
            switchContentPanel(pageMap.get("UserProfile"));
        }

        // set window attributes
        setTitle("Nutrifit");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private JPanel createContent(int width) {
        JPanel content = new JPanel();

        // set content initial attributes
        content.setPreferredSize(new Dimension(getWidth() - width, getHeight()));
        content.setBackground(Color.white);

        return content;
    }

    private JPanel createSideBar(int width) {
        JPanel sideBar = new JPanel();

        // set sideBar attributes
        sideBar.setPreferredSize(new Dimension(width, getHeight()));
        sideBar.setBorder(BorderFactory.createMatteBorder(0,0,0,2, Color.GRAY));
        sideBar.setBackground(Color.white);
        // set up layout
        sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));

        // implement sideBar menu...
        JLabel profile = new JLabel("Profile");
        profile.setFont(profile.getFont().deriveFont(18.0f));
        profile.setAlignmentX(Component.CENTER_ALIGNMENT);
        sideBar.add(profile);

        JLabel exerciseLog = new JLabel("Exercise Log");
        exerciseLog.setFont(exerciseLog.getFont().deriveFont(18.0f));
        exerciseLog.setAlignmentX(Component.CENTER_ALIGNMENT);
        sideBar.add(exerciseLog);

        return sideBar;
    }

    public void switchContentPanel(IContent IContent) {
        String message = IContent.showContent(content, this);
        content.revalidate();
        content.repaint();
        System.out.println(message);
    }

    public JPanel getSideBar() {
        return sideBar;
    }

    public JPanel getContent() {
        return content;
    }

    public Map<String, IContent> getPageMap() {
        return pageMap;
    }
}
