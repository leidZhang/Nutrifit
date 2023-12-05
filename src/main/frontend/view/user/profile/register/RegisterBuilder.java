package main.frontend.view.user.profile.register;

import main.frontend.view.user.profile.userprofile.UserProfileBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RegisterBuilder extends UserProfileBuilder {
    public RegisterBuilder(JPanel page) {
        super(page);
    }

    public void buildBackButton() {
        JButton button = new JButton("Back");
        button.setMinimumSize(new Dimension(120, 30));
        constraints.gridx = 1;
        constraints.gridy = gridy;
        constraints.gridwidth = 1;
        page.add(button, constraints);
        buttonMap.put("Back", button);
    }

    @Override
    public void buildMainContent() {
        buildEntries();
        setHeadHorizontalSpace(150);
        buildBackButton();
        buildSubmitButton();
        setTailHorizontalSpace(400);
    }
}
