package main.frontend.view.user;

import main.backend.common.Result;
import main.backend.user.entity.User;
import main.frontend.view.IContent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Index implements IContent {


    @Override
    public String showContent(JPanel content) {
        content.removeAll();
        content.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // welcome
        JLabel welcomeLabel = new JLabel("Welcome to our project!");
        welcomeLabel.setFont(welcomeLabel.getFont().deriveFont(20.0f));
        content.add(welcomeLabel);

        // login button and click
        JButton loginButton = new JButton("login");
        loginButton.setName("login");
        loginButton.setFont(loginButton.getFont().deriveFont(16.0f));
        content.add(loginButton);

        // loginAddActionListener
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                content.removeAll();
                IContent loginPage = new Login();
                System.out.println(loginPage.showContent(content));
                content.revalidate();
                content.repaint();
            }
        });

        // register button and click
        JButton registerButton = new JButton("registration");
        registerButton.setName("registration");
        registerButton.setFont(registerButton.getFont().deriveFont(16.0f));
        content.add(registerButton);

        // registerAddActionListener
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                content.removeAll();
                IContent registerPage = new Registration();
                System.out.println(registerPage.showContent(content));
                content.revalidate();
                content.repaint();
            }
        });
        return "Index page show";
    }
}
