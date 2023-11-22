package main.frontend.view.mainframe.impl;

import main.backend.user.entity.User;
import main.frontend.common.Content;
import main.frontend.common.IContent;
import main.frontend.common.ContentBuilder;
import main.frontend.session.UserSession;
import main.frontend.view.exercise.form.ExerciseFormPage;
import main.frontend.view.exercise.visualization.ExerciseVisualPage;
import main.frontend.view.home.Home;
import main.frontend.view.mainframe.IMainframe;
import main.frontend.view.mainframe.component.SideBarBuilder;
import main.frontend.view.mainframe.component.SideBarDirector;
import main.frontend.view.meal.form.add.MealAddForm;
import main.frontend.view.meal.table.MealTablePage;
import main.frontend.view.meal.visualization.MealVisualPage;
import main.frontend.view.user.login.LoginPage;
import main.frontend.view.user.profile.register.RegisterPage;
import main.frontend.view.user.profile.userprofile.UserProfilePage;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class FrontEnd extends JFrame implements IMainframe {
    private JPanel sideBar;
    private JPanel content;
    private Map<String, IContent> pageMap = new HashMap<>();

    private UserSession instance = UserSession.getInstance();

    public FrontEnd() {
        pageMap.put("Home", new Home());
        pageMap.put("UserProfile", new UserProfilePage());
        pageMap.put("Register", new RegisterPage());
        pageMap.put("Login", new LoginPage((Content) pageMap.get("Register")));
        pageMap.put("Exercise Records", new ExerciseFormPage());
        pageMap.put("Exercise", new ExerciseVisualPage((Content) pageMap.get("Exercise Records")));
        pageMap.put("Meal Records", new MealTablePage());
        pageMap.put("Meal", new MealVisualPage((Content) pageMap.get("Meal Records")));
        pageMap.put("Meal Form", new MealAddForm());

        initialize(); // initialize GUI
    }

    @Override
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
            // switch to log in
            switchContentPanel((Content) pageMap.get("Login"));
        } else { // user login
            // set up gui
            setSize(1200, 800);
            if (sideBar == null) sideBar = createSideBar(250);
            content.setSize(new Dimension(950, 800));
            getContentPane().add(sideBar, BorderLayout.WEST);
            getContentPane().add(content, BorderLayout.CENTER);
            // switch to home
            switchContentPanel((Content) pageMap.get("Home"));
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

    public void switchContentPanel(IContent external) {
        ((Content) external).setMainframe(this);
        if (pageMap.get("Register").equals(external)) {
            setSize(600, 800); // in case not login
        }

        String message = external.showContent(content);
        content.revalidate();
        content.repaint();
        System.out.println(message);
    }

    public JPanel getContent() {
        return content;
    }

    public Map<String, IContent> getPageMap() {
        return pageMap;
    }
}
