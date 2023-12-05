package main.frontend.view.mainframe.component;

import main.frontend.session.UserSession;
import main.frontend.view.mainframe.IMainframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AccountPanel extends JPanel {
    private JLabel usernameLabel;
    private JButton logoutButton;

    public AccountPanel() {
        usernameLabel = new JLabel();
        logoutButton = new JButton("Logout");

        initialize();
    }

    private void initialize() {
        setLayout(new GridLayout(2, 1));

        Dimension labelDimension = new Dimension(200, 30);
        Dimension buttonDimension = new Dimension(200, 8);
        usernameLabel.setPreferredSize(labelDimension);
        logoutButton.setPreferredSize(buttonDimension);

        Font usernameFont = new Font("Arial", Font.BOLD, 16);
        Font buttonFont = new Font("Arial", Font.PLAIN, 12);
        usernameLabel.setFont(usernameFont);
        logoutButton.setFont(buttonFont);

        add(usernameLabel);
        add(logoutButton);

        setBackground(Color.white);
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }

    public JLabel getUsernameLabel() {
        return usernameLabel;
    }
}
