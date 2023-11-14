package main.frontend.view.user.profile.userprofile;

import main.frontend.custom.NfEntry;
import main.frontend.view.user.profile.common.UserFormBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

public class UserProfileBuilder extends UserFormBuilder {
    public UserProfileBuilder(JPanel page) {
        super(page);
    }

    public void buildModifyButton(ActionListener listener) {
        JButton submitButton = new JButton("Modify");
        submitButton.setMinimumSize(new Dimension(120, 30));
        submitButton.addActionListener(listener);
        constraints.gridx = 1;
        constraints.gridy = gridy;
        constraints.gridwidth = 1;
        page.add(submitButton, constraints);
    }

    public void buildSubmitButton(ActionListener listener) {
        JButton submitButton = new JButton("Submit");
        submitButton.setMinimumSize(new Dimension(120, 30));
        submitButton.addActionListener(listener);
        constraints.gridx = 2;
        constraints.gridy = gridy;
        constraints.gridwidth = 1;
        page.add(submitButton, constraints);
    }
}
