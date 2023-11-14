package main.frontend.view.user.profile.register;

import main.frontend.custom.NfEntry;
import main.frontend.view.user.profile.userprofile.UserProfileBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Map;

public class RegisterBuilder extends UserProfileBuilder {
    public RegisterBuilder(JPanel page) {
        super(page);
    }

    public void buildBackButton(ActionListener listener) {
        JButton button = new JButton("Back");
        button.setMinimumSize(new Dimension(120, 30));
        button.addActionListener(listener);
        constraints.gridx = 1;
        constraints.gridy = gridy;
        constraints.gridwidth = 1;
        page.add(button, constraints);
    }
}
