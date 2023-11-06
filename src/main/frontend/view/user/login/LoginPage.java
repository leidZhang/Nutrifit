package main.frontend.view.user.login;

import main.backend.common.Result;
import main.backend.user.IUserController;
import main.backend.user.entity.User;
import main.backend.user.impl.UserController;
import main.frontend.view.mainframe.FrontEnd;
import main.frontend.common.Content;
import main.frontend.common.PageBuilder;
import main.frontend.component.NfEntry;
import main.frontend.session.UserSession;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class LoginPage extends Content {
    private String pageName = "login page";
    private UserSession instance = UserSession.getInstance();
    private IUserController controller = new UserController();
    private Map<String, NfEntry> entries;

    @Override
    public String showContent(JPanel content, FrontEnd frontEnd) {
        // add listener
        ActionListener loginListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

                    frontEnd.initialize();
                } else {
                    JOptionPane.showMessageDialog(content, res.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };

        ActionListener registerListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Register Button clicked!");

                // Remove the login page components from the content panel
                content.removeAll();

                // Revalidate and repaint to update the content panel
                content.revalidate();
                content.repaint();

                frontEnd.setSize(600, 800);
                frontEnd.switchContentPanel(frontEnd.getPageMap().get("Register"));
            }
        };

        // construct page
        PageBuilder builder = new LoginPageBuilder(content);
        LoginPageDirector director = new LoginPageDirector(builder);
        director.constructPage("Login", registerListener, loginListener);

        // get entries and setup entries
        entries = ((LoginPageBuilder) builder).getFormData();

        return "Switch to " + pageName;
    }
}
