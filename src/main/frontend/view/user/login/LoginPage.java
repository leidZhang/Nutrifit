package main.frontend.view.user.login;

import main.backend.common.Result;
import main.backend.user.IUserController;
import main.backend.user.entity.User;
import main.backend.user.impl.UserController;
import main.frontend.common.Content;
import main.frontend.common.ContentBuilder;
import main.frontend.common.Director;
import main.frontend.custom.entry.NfEntry;
import main.frontend.session.UserSession;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Map;

public class LoginPage extends Content {
    private String pageName = "login page";
    private UserSession instance = UserSession.getInstance();
    private IUserController controller = new UserController();
    private Map<String, NfEntry> entries;
    private Map<String, JButton> buttonMap;
    private Content registerPage;

    public LoginPage(Content registerPage) {
        this.registerPage = registerPage;
    }

    private ActionListener handleLogin(JPanel content) {
        ActionListener listener = e -> {
            System.out.println("Login Button clicked!");
            // get user input
            String username = entries.get("Username").getInput();
            String password = entries.get("Password").getInput();

            // get user
            Result res = controller.login(username, password);
            if (res.getCode().equals("200")) {
                User user = (User) res.getData();
                instance.setUser(user);

                // Remove the login page components from the content panel
                content.removeAll();

                // Revalidate and repaint to update the content panel
                content.revalidate();
                content.repaint();

                frontEnd.get().initialize();
            } else {
                JOptionPane.showMessageDialog(content, res.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        };

        return listener;
    }

    private ActionListener handleRegister(JPanel content) {
        ActionListener listener = e -> {
            System.out.println("Register button clicked!");

            // Remove the login page components from the content panel
            content.removeAll();

            // Revalidate and repaint to update the content panel
            content.revalidate();
            content.repaint();

            frontEnd.get().switchContentPanel(registerPage);
        };

        return listener;
    }

    protected void mount(JPanel content) {
        buttonMap.get("Register").addActionListener(handleRegister(content));
        buttonMap.get("Login").addActionListener(handleLogin(content));
    }

    @Override
    public String showContent(JPanel content) {
        // construct page
        LoginBuilder builder = new LoginBuilder(content);
        Director director = new Director(builder);
        director.constructPage("Login");

        // get entries and setup entries
        entries = builder.getFormData();
        buttonMap = builder.getButtonMap();

        mount(content);

        return "Switch to " + pageName;
    }
}
