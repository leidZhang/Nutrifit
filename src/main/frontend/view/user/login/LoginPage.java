package main.frontend.view.user.login;

import main.backend.common.Result;
import main.backend.user.IUserController;
import main.backend.user.entity.User;
import main.backend.user.impl.UserController;
import main.frontend.view.mainframe.impl.FrontEnd;
import main.frontend.common.Content;
import main.frontend.common.ContentBuilder;
import main.frontend.custom.NfEntry;
import main.frontend.session.UserSession;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Map;

public class LoginPage extends Content {
    private String pageName = "login page";
    private UserSession instance = UserSession.getInstance();
    private IUserController controller = new UserController();
    private Map<String, NfEntry> entries;
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
            Result res = controller.getUser(username);
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

    @Override
    public String showContent(JPanel content) {
        // add listener
        ActionListener loginListener = handleLogin(content);
        ActionListener registerListener = handleRegister(content);

        // construct page
        ContentBuilder builder = new LoginBuilder(content);
        LoginDirector director = new LoginDirector(builder);
        director.constructPage("Login", registerListener, loginListener);

        // get entries and setup entries
        entries = ((LoginBuilder) builder).getFormData();

        return "Switch to " + pageName;
    }
}
