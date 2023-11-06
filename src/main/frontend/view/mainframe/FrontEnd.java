package main.frontend.view.mainframe;

import main.backend.user.entity.User;
import main.frontend.common.IContent;
import main.frontend.common.ContentBuilder;
import main.frontend.session.UserSession;
import main.frontend.view.exercise.ExercisePage;
import main.frontend.view.home.Home;
import main.frontend.view.meal.MealPage;
import main.frontend.view.user.login.LoginPage;
import main.frontend.view.user.register.RegisterPage;
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
            "Register", new RegisterPage(),
            "Exercise", new ExercisePage(),
            "Meal", new MealPage()
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

            if (content == null) {
                content = createContent(0);
            }
            if (sideBar != null) {
                remove(sideBar);
                sideBar = null;
            }

            getContentPane().add(content, BorderLayout.CENTER);
            // switch to login
            switchContentPanel(pageMap.get("Login"));
        } else { // user login
            // set up gui
            setSize(1200, 800);
            if (sideBar == null) sideBar = createSideBar(250);
            content.setSize(new Dimension(950, 800));
            getContentPane().add(sideBar, BorderLayout.WEST);
            getContentPane().add(content, BorderLayout.CENTER);
            // switch to home
            switchContentPanel(pageMap.get("Home"));
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
        User user = instance.getUser();
        ContentBuilder builder = new SideBarBuilder(sideBar, this);
        SideBarDirector director = new SideBarDirector(builder);
        director.constructSideBar(user.getUsername());

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
