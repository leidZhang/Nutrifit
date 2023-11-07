package main.frontend.view.user.register;

import main.backend.common.Result;
import main.backend.user.IUserController;
import main.backend.user.entity.User;
import main.backend.user.impl.UserController;
import main.frontend.view.mainframe.FrontEnd;
import main.frontend.common.ContentBuilder;
import main.frontend.component.NfEntry;
import main.frontend.view.user.userprofile.UserProfileBuilder;
import main.frontend.view.user.userprofile.UserProfilePage;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Map;

public class RegisterPage extends UserProfilePage {
    private String pageName = "Register Page";
    private IUserController controller = new UserController();
    private Map<String, NfEntry> entries;

    private ActionListener handleSubmit(JPanel content, FrontEnd frontEnd) {
        ActionListener listener = e -> {
            // verify input
            System.out.println(verifyInput(entries));
            if (!verifyInput(entries)) return;

            User newUser = getNewUser(entries);
            Result res = controller.save(newUser);

            // submit result
            if (res.getCode().equals("200")) {
                JOptionPane.showMessageDialog(content, "Registration success!", "Message", JOptionPane.INFORMATION_MESSAGE);

                // Remove the login page components from the content panel
                content.removeAll();

                // Revalidate and repaint to update the content panel
                content.revalidate();
                content.repaint();

                frontEnd.initialize();
            } else {
                JOptionPane.showMessageDialog(content, res.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        };

        return listener;
    }

    private ActionListener handleBack(JPanel content, FrontEnd frontEnd) {
        ActionListener listener = e -> {
            System.out.println("Back button clicked!");

            // Remove the login page components from the content panel
            content.removeAll();

            // Revalidate and repaint to update the content panel
            content.revalidate();
            content.repaint();

            frontEnd.initialize();
        };

        return listener;
    }

    @Override
    public String showContent(JPanel content, FrontEnd frontEnd) {
        // add listener
        ActionListener submitListener = handleSubmit(content, frontEnd);
        ActionListener backListener = handleBack(content, frontEnd);

        // construct page
        ContentBuilder builder = new RegisterBuilder(content);
        RegisterDirector director = new RegisterDirector(builder);
        director.constructPage("Register", submitListener, backListener);

        // get entries and setup entries
        entries = ((UserProfileBuilder) builder).getFormData();
        setEntryRegex(entries);

        return "Switch to " + pageName;
    }
}
