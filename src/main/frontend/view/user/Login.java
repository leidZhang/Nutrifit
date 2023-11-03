package main.frontend.view.user;

import main.backend.common.Result;
import main.backend.user.IUserController;
import main.backend.user.entity.User;
import main.backend.user.impl.UserController;
import main.frontend.session.UserSession;
import main.frontend.view.IContent;
import main.frontend.view.home.Home;

import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Login implements IContent {

    IUserController userController = new UserController();
    UserSession instance = UserSession.getInstance();
    private JTextField usernameField;
    private JPasswordField passwordField;

    @Override
    public String showContent(JPanel content) {
        // the code below is just a demonstration, implement ur user login components...

        // set up layout
        content.removeAll();
//        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));


        // Create text boxes and password boxes
        JLabel usernameLabel = new JLabel("user name:");
        usernameLabel.setFont(usernameLabel.getFont().deriveFont(16.0f));
        content.add(usernameLabel);
        usernameField = new JTextField(20);
        usernameField.setName("usernameField");
        usernameField.setFont(usernameField.getFont().deriveFont(16.0f));
        content.add(usernameField);

        // Create a login button
        JButton loginButton = new JButton("login");
        loginButton.setName("login");
        loginButton.setFont(loginButton.getFont().deriveFont(16.0f));
        content.add(loginButton);

        //back button
        JButton backButton = new JButton("back");
        backButton.setName("back");
        backButton.setFont(backButton.getFont().deriveFont(16.0f));
        content.add(new JLabel());
        content.add(backButton);

        // Add a click event listener for the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = usernameField.getText();
//                System.out.println("user name: " + username);

                // get data from main.backend
                Result res = userController.getUser(username);
                System.out.println(res.getCode());
                if (Objects.equals(res.getCode(), "200")) {
                    User userNow = (User) res.getData();
                    instance.setUser(userNow);
                    content.removeAll();
                    JOptionPane.showMessageDialog(null, "login success");
                    IContent up = new UserProfile();
                    up.showContent(content);
                    content.revalidate();
                    content.repaint();
                    // switch to home page
                }
                else {
                    // Clear password box
                    usernameField.setText("");
                    JOptionPane.showMessageDialog(null, "login fail");
                }

            }
        });

        // addActionListener
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                content.removeAll();
                IContent index = new Index();
                index.showContent(content);
                content.revalidate();
                content.repaint();
            }
        });
        return "Switch to Login";
    }
}
