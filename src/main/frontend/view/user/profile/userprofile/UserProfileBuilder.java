package main.frontend.view.user.profile.userprofile;

import main.frontend.view.user.profile.common.UserFormBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UserProfileBuilder extends UserFormBuilder {
    public UserProfileBuilder(JPanel page) {
        super(page);
    }

    @Override
    public void buildMainContent() {
        buildEntries();
        enableForm(false);
        setHeadHorizontalSpace(120);
        buildModifyButton();
        buildSubmitButton();
        setTailHorizontalSpace(500);
    }

    public void buildModifyButton() {
        JButton button = new JButton("Modify");
        button.setMinimumSize(new Dimension(120, 30));
        constraints.gridx = 1;
        constraints.gridy = gridy;
        constraints.gridwidth = 1;
        page.add(button, constraints);
        buttonMap.put("Modify", button);
    }

    public void buildSubmitButton() {
        JButton button = new JButton("Submit");
        button.setMinimumSize(new Dimension(120, 30));
        constraints.gridx = 2;
        constraints.gridy = gridy;
        constraints.gridwidth = 1;
        page.add(button, constraints);
        buttonMap.put("Submit", button);
    }
}
