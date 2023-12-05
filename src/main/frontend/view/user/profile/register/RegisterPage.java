package main.frontend.view.user.profile.register;

import main.backend.common.Result;
import main.backend.user.IUserController;
import main.backend.user.entity.User;
import main.backend.user.impl.UserController;
import main.frontend.common.ContentBuilder;
import main.frontend.common.Director;
import main.frontend.custom.entry.NfEntry;
import main.frontend.view.user.profile.common.UserFormPage;
import main.frontend.view.user.profile.userprofile.UserProfileBuilder;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Map;

public class RegisterPage extends UserFormPage {
    private String pageName = "Register Page";
    private IUserController controller = new UserController();

    private ActionListener handleSubmit(JPanel content) {
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

                frontEnd.get().initialize();
            } else {
                JOptionPane.showMessageDialog(content, res.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        };

        return listener;
    }

    private ActionListener handleBack(JPanel content) {
        ActionListener listener = e -> {
            System.out.println("Back button clicked!");

            // Remove the login page components from the content panel
            content.removeAll();

            // Revalidate and repaint to update the content panel
            content.revalidate();
            content.repaint();

            frontEnd.get().initialize();
        };

        return listener;
    }

    protected void mount(JPanel content) {
        setEntryRegex(entries);
        buttonMap.get("Submit").addActionListener(handleSubmit(content));
        buttonMap.get("Back").addActionListener(handleBack(content));
    }

    @Override
    public String showContent(JPanel content) {
        // construct page
        RegisterBuilder builder = new RegisterBuilder(content);
        Director director = new Director(builder);
        director.constructPage("Register");

        // get entries and setup entries
        entries = builder.getFormData();
        buttonMap = builder.getButtonMap();

        mount(content);

        return "Switch to " + pageName;
    }
}
