package main.frontend.view.mainframe.component;

import main.frontend.session.UserSession;
import main.frontend.view.mainframe.IMainframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AccountPanel extends JPanel {
    private JLabel usernameLabel;
    private JButton logoutButton;
    private IMainframe frontEnd;

    public AccountPanel(String username, IMainframe frontEnd) {
        setLayout(new GridLayout(2, 1));

        usernameLabel = new JLabel("User: " + username);
        logoutButton = new JButton("Logout");
        this.frontEnd = frontEnd;

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
        setLogoutButton();
    }

    public void setLogoutButton() {
        ActionListener actionListener = e -> {
            System.out.println("logout button is clicked!");
            UserSession instance = UserSession.getInstance();
            instance.setUser(null);

            JPanel content = (JPanel) frontEnd.getContent();
            // Remove the login page components from the content panel
            content.removeAll();

            // Revalidate and repaint to update the content panel
            content.revalidate();
            content.repaint();

            frontEnd.initialize();
        };
        logoutButton.addActionListener(actionListener);
    }
}
